package org.codelife.app.killer.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * json web token utils
 *
 * @author csl
 * @create 08/03/2017 17:49
 * iss: jwt签发者
 * sub: jwt所面向的用户
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 *
 **/
@Component
public class JWTUtils {

    @JsonIgnore
    @Value("${jwt.secret}")
    private String secret;

    @JsonIgnore
    @Value("${jwt.header}")
    private String header;

    @JsonIgnore
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @JsonIgnore
    @Value("${jwt.expiration}")
    private long expiration; //default 1 week

    @JsonIgnore
    @Value("${jwt.issuer}")
    private String issuer;

    /**
     * generate json web token
     * @param subject - username
     * @return base64code
     */
    public final String generateToken(final String subject){
        long nowMillis=System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Date exp=generateExpirationDate(nowMillis);

      JwtBuilder jwtBuilder= Jwts.builder()
                                .setIssuer(this.issuer)
                                .setIssuedAt(now)
                                .setSubject(subject)
                                .setExpiration(exp)
                                .signWith(SignatureAlgorithm.HS512,this.secret.getBytes());

        return jwtBuilder.compact();
    }

    /**
     *  get expiration date by validate token
     * @param token - json web token encrypt string
     * @return Date
     */
    public final Date getExpirationDateFromToken(final String token){
        Date expiration=null;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration=claims.getExpiration();
        }catch (Exception e){
            e.printStackTrace();
        }
        return expiration;
    }


    /**
     *  refresh token
     * @param token - validate token
     * @return now token
     */
    public final String refreshToken(final String token){
        String refreshToken=null;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshToken = generateToken(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
         return refreshToken;
    }

    /**
     * the validate token is expired?
     * @param token - validate token
     * @return boolean
     */
    public final boolean isTokenExpired(final String token){
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     *  get json web token body from the validate token
     * @param token - validate token
     * @return Claims
     */
    public final Claims getClaimsFromToken(final String token){
        Claims claims=null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }

    private final Date generateExpirationDate(final long currentMillis){
        return new Date(currentMillis+expiration*1000);
    }

}
