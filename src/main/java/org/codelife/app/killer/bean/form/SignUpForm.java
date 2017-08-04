package org.codelife.app.killer.bean.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

/**
 * @author csl
 * @create 07/26/2017 12:55
 **/
@Data
public class SignUpForm {

    @Size(max = 255,min=6)
    private String username;

    @JsonIgnore
    @Size(max=255,min=6)
    private String password;

    @JsonIgnore
    @Size(max = 11,min=11)
    private String phoneNumber;

    @JsonIgnore
    @Email
    private String email;

    @JsonIgnore
    private String remoteIP;

}
