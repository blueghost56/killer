package org.codelife.app.killer.bean;

/**
 * sex
 *
 * @author csl
 * @create 07/20/2017 16:24
 **/
public enum  Gender {
    male,female,secret;

    public static Gender convertStringToGender(final String str){

        switch (str.toLowerCase()){
            case "m":
            case "male":
                return Gender.male;
            case "f":
            case "female":
                return Gender.female;
            case "s":
            case "secret":
                return Gender.secret;
            default:
                return Gender.secret;
        }
    }

}
