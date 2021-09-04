package org.legend.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.legend.mybatis.entity.Person;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;

public class test {
    @Test
    public void TestMybatis(){
        try {
            //加载mybatis配置文件(为了访问数据库)
            Reader reader = Resources.getResourceAsReader("conf.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlsession = sessionFactory.openSession();

            String statement = "personMapper.queryPersonById";
            Person person = sqlsession.selectOne(statement, 1);

            System.out.println(person);

            //关闭会话
            sqlsession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
