package org.codelife.app.killer.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.codelife.app.killer.model.Account;
import org.codelife.app.killer.typehandler.StringListTypeHandler;
import org.springframework.stereotype.Repository;

/**
 * admin account
 *
 * @author csl
 * @create 07/24/2017 13:03
 **/

@Mapper
public interface AccountMapper {

    @Select("select * from account where username = #{username}&& password = #{password}")
    Account findAccount(@Param("username") String username,@Param("password") String password);

    @Select("select * from account where username = #{username}")
    @Results(value={
        @Result(column = "roles",property = "roles",jdbcType = JdbcType.VARCHAR,typeHandler = StringListTypeHandler.class)
    })
    Account findAccountByUsername(@Param("username")String username);

    @Insert({"insert into account(username,password,login_ip) values(#{username},#{password},#{loginIp})"})
    @Options(useGeneratedKeys = true,keyProperty = "accountId")
     Integer save(Account account);

    @Update({"update account set login_ip = #{loginIp},last_login_time = #{lastLoginTime} where account_id = #{accountId}"})
    Integer update(Account account);
}
