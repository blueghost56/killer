package org.codelife.app.killer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.codelife.app.killer.bean.Gender;
import org.codelife.app.killer.model.Pets;

import java.util.List;

@Mapper
public interface TestMapper {
    @Select("select * from pets")
    @Results(value = {
            @Result(column = "sex",property = "sex",jdbcType = JdbcType.VARCHAR,javaType = Gender.class)
    })
    List<Pets> findAllPets();
}
