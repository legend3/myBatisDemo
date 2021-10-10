package org.legend.mybatis.converter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义MyBatis类型处理器
 *
 * java -数据库(jdbc类型)
 *
 * 自定义类型转换器（boolean -number）步骤：
 * a.创建转换器：需要实现TypeHandler接口
 * 	通过阅读源码发现，此接口有一个实现类 BaseTypeHandler ，因此 要实现转换器有2种选择：
 * 	i.实现接口TypeHandler接口
 * 	ii.继承BaseTypeHandler
 * b.配置conf.xml
 *
 *
 * 需要注意的问题：  INTEGER
 */

public class BooleanAndIntConverter extends BaseTypeHandler<Boolean> {
    //在实例化一个接口或者类的对象的时候，指明了<E>是什么类型，则以后的该接口或方法中只要有<E>或E的就全变成了填入的类型
    //java(boolean)-DB(number)
    /*
     * ps:PreparedStatement对象
     * i：PreparedStatement对象操作参数的位置
     * parameter:java值
     * jdbcType：jdbc操作的数据库类型
     */

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        //根据java的Boolean值设置数据库的int值
        if(parameter) {//true
            ps.setInt(i, 1);
        }else {//parameterw为false
            ps.setInt(i, 0);
        }
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        //根据数据库的int值设置java的Boolean值
        int stuSex = rs.getInt(columnName);//rs.getInt("stuno")列名取数据库字段值
        return stuSex == 1?true:false;
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        //根据数据库的int值设置java的Boolean值
        int stuSex = rs.getInt(columnIndex);//rs.getInt(1)列下标取数据库字段值(从1开始...)
        return stuSex == 1?true:false;
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        //根据数据库的int值设置java的Boolean值
        int stuSex = cs.getInt(columnIndex);//通过存储过程形式，rs.getInt(1)列下标取数据库字段值(从1开始...)
        return stuSex == 1?true:false ;
    }
}
