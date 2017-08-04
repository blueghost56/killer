package org.codelife.app.killer.typehandler;

import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author csl
 * @create 07/25/2017 15:37
 **/
@MappedTypes({List.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
    private final static String COMMA=",";
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.toString());
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        List<String> resultList=new ArrayList<>();
        if(null != columnName){
          String content=rs.getString(columnName);
          String[] contentArray= content.split(COMMA);
           resultList.addAll(Arrays.asList(contentArray));
        }
        return resultList;
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
