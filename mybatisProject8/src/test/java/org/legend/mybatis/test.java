package org.legend.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.legend.mybatis.entity.*;
import org.legend.mybatis.mapper.StudentMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test {
    @Test
    //一对一，业务扩展
    public void queryStudentByNoWithOO() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        StudentBusiness student = studentMapper.queryStudentByNoWithOO(2);
        System.out.println(student);

        session.close();
    }
    @Test
    //一对一，resultMap
    public void queryStudentByNoWithOOResultMap() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        Student student = studentMapper.queryStudentByNoWithOOResultMap(2);
        System.out.println(student);

        session.close();
    }
    @Test
    //一对多
    public void quearyClassAndStudents() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        StudentClass studentClass = studentMapper.quearyClassAndStudents(1);
        System.out.println(studentClass);
//        for(Student student:studentClass.getStudents()){
//            System.out.println(student.getStuNo() + "-"
//                                + student.getStuName() + "-"
//                                + student.getStuAge() + "-"
//                                + student.getStudentCard()
//            );
//        }
        session.close();
    }
}
