package org.legend.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.legend.mybatis.entity.Student;
import org.legend.mybatis.mapper.StudentMapper;
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
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader, "mysql");//可以通过build的第二参数 指定数据库环境.与默认的conf.xml文件中default指定同等效果
        SqlSession session =  sessionFactory.openSession();

        //调Mapper接口,取代statement
        //        String statement = "studentMapper."+"queryAllStudents";
        //通过session对象获取接口（session.getMapper(接口.class);），再调用该接口中的方法，程序会自动执行该方法对应的SQL。
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);//获取接口，就是获取映射文件！
        //调用接口方法，从而定位对应的sql语句;(不需要实现类实现接口！直接调用接口方法找sql语句)
        Student student = studentMapper.queryStudentByStuno(1);
        System.out.println(student);
        session.close();
    }
    @Test
    //查询单个学生（使用了转换器）
    public void queryStudentByStunoWithConverter() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession

        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"mysql") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class) ;
        Student student = studentMapper.queryStudentByStunoWithConverter(1) ;//接口中的方法->SQL语句

        System.out.println(student);
        session.close();
    }
    @Test
    //查询全部学生
    public void queryAllStudents() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"mysql") ;
        SqlSession session = sessionFacotry.openSession() ;

//        String statement = "studentMapper."+"queryAllStudents";
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.queryAllStudents();
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
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"mysql") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = new Student(3,"ww",25,"s1");
        studentMapper.addStudent(student);
        session.commit(); //提交事务
        System.out.println("增加学生数据行成功！");
        session.close();
    }
    @Test
    //增加学生（带转换器）
    public static void addStudentWithConverter() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"mysql") ;
        SqlSession session = sessionFacotry.openSession() ;

//					String statement = "org.lanqiao.entity.studentMapper."+"addStudent";
        Student student = new Student(63,"ww53",23,"s3");
        student.setStuSex(true);//1


//					int count = session.insert(statement, student );//statement：指定执行的SQL    student：sql中需要的参数 （ ? ? ? ）
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.addStudentWithConverter(student);

        session.commit(); //提交事务

        System.out.println("增加成功");
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
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"mysql") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.deleteStudentByStuno(3);
        session.commit(); //提交事务
        System.out.println("删除学生");
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
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"mysql") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);        //修改的参数
        Student student = new Student();
        //修改哪个人，where stuno =2
        student.setStuNo(2);
        //修改成什么样子？
        student.setStuName("lxs");
        student.setStuAge(44);
        student.setGraName("s2");
        //执行
        studentMapper.updateStudentByStuno(student);
        session.commit(); //提交事务
        System.out.println("修改个学生");
        session.close();
    }
}
