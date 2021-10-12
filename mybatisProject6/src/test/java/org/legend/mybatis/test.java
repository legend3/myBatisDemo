package org.legend.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.legend.mybatis.entity.Address;
import org.legend.mybatis.entity.Student;
import org.legend.mybatis.mapper.StudentMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    @Test
    //基本类型+String类型的输出参数
    public void queryStudentCount() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射
        int count = studentMapper.queryStudentCount();
        System.out.println(count);

        session.close();

    }
    @Test
    //
    public void queryStuByStuno() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射
        Student student = studentMapper.queryStuByStuno(1);//接口中的方法->SQL语句
        System.out.println(student);

        session.close();
    }
    @Test
    //
    public void queryStudentByhomeaddress() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        Student student = new Student();
        Address address = new Address();
        address.setHomeAddress("beijing");
        student.setAddress(address);
        List<Student> students = studentMapper.queryStudentByhomeaddress(student);//接口中的方法->SQL语句
        System.out.println(students);

        session.close();

    }
    @Test
    public void queryStudentOutByHashMap() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射
        Map<String, Object> students = studentMapper.queryStudentOutByHashMap(1);//接口中的方法->SQL语句
        System.out.println(students);//{no=1, name=jack},Map类型！

        session.close();

    }
    @Test
    public void queryAllStudentsOutByHashMap() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射
        List<Map<String, Object>> students = studentMapper.queryAllStudentsOutByHashMap();//接口中的方法->SQL语句
        System.out.println(students);

        session.close();
    }
    @Test
    //输出参数:resultMap形式
    public void queryStudentById() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射
        Student student = studentMapper.queryStudentById(1);
        System.out.println(student);

        session.close();
    }
    @Test
    //输出参数:resultType形式
    public void queryStudentByIdWithHashMap() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射
        Student student = studentMapper.queryStudentByIdWithHashMap(1);
        System.out.println(student);

        session.close();
    }
}
