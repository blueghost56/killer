package org.codelife.app.killer.mapper.console;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.codelife.app.killer.model.console.SuperAdmin;

/**
 * database interface
 *
 * @author csl
 * @create 08/07/2017 16:57
 **/
@Mapper
public interface SuperAdminMapper {
    @Select("select account_name from super_admin where account_name=#{accountName}")
    String findSuperAdminName(final String accountName);

    @Insert("insert into super_admin")
    int save(SuperAdmin superAdmin);
}
