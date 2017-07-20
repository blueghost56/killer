package org.codelife.app.killer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codelife.app.killer.bean.Gender;

import java.sql.Timestamp;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Pets {

    private String name;
    @JsonIgnore
    private int age;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Timestamp time;

    private Gender sex;

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
