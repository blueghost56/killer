package org.codelife.app.killer.bean.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Form of account
 *
 * @author csl
 * @create 07/24/2017 13:08
 **/
@Data
public class SignInForm {

    @Size(max = 255,min = 6)
    private String username;

    @JsonIgnore
    @Size(max = 20,min = 6)
    private String password;

    @JsonIgnore
    private String loginIp;

    @JsonIgnore
    private Timestamp signInTime;

    public SignInForm(){}

}
