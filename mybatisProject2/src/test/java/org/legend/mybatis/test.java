package org.legend.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.legend.mybatis.entity.Student;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class test {
    @Test
    //查询单个学生
    public void test_selectOne() throws IOException {
        //Connection(JDBC) -  SqlSession操作MyBatis
        Reader reader = Resources.getResourceAsReader("conf.xml");

        //reader  ->SqlSession
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader, "development");//可以通过build的第二参数 指定数据库环境.与默认的conf.xml文件中default指定同等效果
        SqlSession session =  sessionFactory.openSession();

        String statement = "studentMapper.queryStudentByStuno";//namespace.id
        Student student = session.selectOne(statement,1);
        System.out.println(student);
        session.close();
    }
    @Test
    //查询全部学生
    public void queryAllStudents() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml");
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        String statement = "studentMapper."+"queryAllStudents";
        List<Student> students = session.selectList(statement);
        System.out.println(students);
        session.close();
    }
    @Test
    //增加学生
    public void addStudent() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        String statement = "studentMapper." + "addStudent";
        Student student = new Student(9,"lilei",25,"s1");


        int count = session.insert(statement, student);//statement：指定执行的SQL    student：SQL中需要的参数 （ ? ? ? ）
        session.commit(); //提交事务

        System.out.println("增加"+count+"个学生");
        session.close();
    }

    @Test
    //删除学生
    public void deleteStudentByStuno() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        String statement = "studentMapper."+"deleteStudentByStuno";

        int count = session.delete(statement,3) ;

        session.commit(); //提交事务

        System.out.println("删除"+count+"个学生");
        session.close();
    }

    @Test
    //修改学生
    public void updateStudentByStuno() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        String statement = "studentMapper."+"updateStudentByStuno";
        //修改的参数
        Student student = new Student();
        //修改哪个人，where stuno =2
        student.setStuNo(2);
        //修改成什么样子？
        student.setStuName("lxs");
        student.setStuAge(44);
        student.setGraName("s2");
        //执行
        int count = session.update(statement,student) ;

        session.commit(); //提交事务

        System.out.println("修改"+count+"个学生");
        session.close();
    }
}
