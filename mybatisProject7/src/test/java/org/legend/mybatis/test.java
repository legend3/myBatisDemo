package org.legend.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.legend.mybatis.entity.Address;
import org.legend.mybatis.entity.Grade;
import org.legend.mybatis.entity.Student;
import org.legend.mybatis.mapper.StudentMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test {
    @Test
    //<if><where>
    public void queryStuByNOrAWithSqlTag() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        Student stu = new Student();
        stu.setStuName("ls");
        stu.setStuAge(24);

        Student student = studentMapper.queryStuByNOrAWithSqlTag(stu);
        System.out.println(student);

        session.close();
    }
    @Test
    //<foreach>-类属性
    public void queryStudentsWithNosInGrade() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        Grade grade = new Grade();
        List<Integer> stuNos = new ArrayList<>();
        stuNos.add(1);
        stuNos.add(2);
        stuNos.add(3);
        grade.setStuNos(stuNos);
        List<Student> student = studentMapper.queryStudentsWithNosInGrade(grade);
        System.out.println(student);

        session.close();
    }
    @Test
    //<foreach>-数组
    public void queryStudentsWithArray() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        Grade grade = new Grade();
        int[] stuNos = {1,2,3};
        List<Student> student = studentMapper.queryStudentsWithArray(stuNos);
        System.out.println(student + "\n" + "迭代数组完毕！");

        session.close();
    }
    @Test
    //<foreach>-集合
    public void queryStudentsWithList() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        Grade grade = new Grade();
        List<Integer> stuNos = new ArrayList<>();
        stuNos.add(1);
        stuNos.add(2);
        stuNos.add(3);
        List<Student> student = studentMapper.queryStudentsWithList(stuNos);
        System.out.println(student + "\n" + "迭代集合完毕！");

        session.close();
    }
    @Test
    //<foreach>-对象数组
    public void queryStudentsWithObjectArray() throws IOException {
        Reader read = Resources.getResourceAsReader("conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(read);
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//接口关联mapper映射

        Student student1 = new Student();
        student1.setStuNo(1);
        Student student2 = new Student();
        student2.setStuNo(2);
        Student student3 = new Student();
        student3.setStuNo(3);
        Student[] stuNos = {student1,student2,student3};
        List<Student> student = studentMapper.queryStudentsWithObjectArray(stuNos);
        System.out.println(student + "\n" + "迭代对象数组完毕！");

        session.close();
    }
}
