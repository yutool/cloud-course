package com.anko.coursems.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.*;

public class BooleanAndIntConverter implements TypeHandler<Boolean> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        if(aBoolean) {
            ps.setInt(i,1);
        } else {
            ps.setInt(i,0);
        }
    }

    @Override
    public Boolean getResult(ResultSet rs, String s) throws SQLException {
        return rs.getInt(s) == 1 ;
    }

    @Override
    public Boolean getResult(ResultSet rs, int i) throws SQLException {
        return rs.getInt(i) == 1 ;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int i) throws SQLException {
        return cs.getInt(i) == 1;
    }
}
