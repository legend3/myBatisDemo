package org.legend.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.legend.mybatis.entity.Address;
import org.legend.mybatis.entity.Student;
import org.legend.mybatis.mapper.StudentMapper;
import org.testng.annotations.Test;

public class test {
    @Test
    //查询单个学生（使用了转换器）
    public static void queryStudentByStunoWithConverter() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession

        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class) ;
        Student student = studentMapper.queryStudentByStunoWithConverter(1) ;//接口中的方法->SQL语句

        System.out.println(student);
        session.close();
    }
    @Test
    //查询单个学生(#{}、\${}的区别:a)
    public void queryStudentByStuno() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession

        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class) ;
        Student student = studentMapper.queryStudentByStuno(2) ;//接口中的方法->SQL语句

        System.out.println(student);
        session.close();
    }
    @Test
    //查询单个学生byName(#{}、${}的区别:b)
    public void queryStudentByStuname() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession

        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class) ;
        Student student = studentMapper.queryStudentByStuname("jack") ;//接口中的方法->SQL语句

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
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;


//					List<Student> students = session.selectList(statement ) ;
        StudentMapper studentMapper = session.getMapper( StudentMapper.class) ;
        List<Student> students = studentMapper.queryAllStudents() ;//接口的方法->SQL

        System.out.println(students);
        session.close();
    }
    @Test
    //查询全部学生，并且根据姓名排序(${}适合动态排序案例！)
    public void queryStudentOrderByColumn() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;


        StudentMapper studentMapper = session.getMapper( StudentMapper.class) ;
        List<Student> students = studentMapper.queryStudentOrderByColumn("stuno") ;//接口的方法->SQL

        System.out.println(students);
        session.close();
    }
    @Test
    //根据姓名或年龄查询学生(输入参数类型为对象类型时，#$值的案例！)
    public void queryStudentBystuageOrstuName() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;


        StudentMapper studentMapper = session.getMapper( StudentMapper.class) ;
        Student student = new Student();
        student.setStuAge(24);
//        模糊查询，方式一：
        student.setStuName("%m%");
//        模糊查询，方式二：
//        student.setStuName("m");
        //[2-ls-24-null-性别:false, 3-mike-23-null-性别:false]
        List<Student> students = studentMapper.queryStudentBystuageOrstuName(student) ;//接口的方法->SQL

        System.out.println(students);
        session.close();
    }
    @Test
    //根据姓名或年龄查询学生(输入参数类型为对象类型时，#$值的案例！)
    public void queryStudentBystuageOrstuNameWithHashMap() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;


        StudentMapper studentMapper = session.getMapper( StudentMapper.class) ;
        Map<String, Object> studentMap = new HashMap<>();
        studentMap.put("stuAge", 23);//key:输入参数名，value:赋值输入参数
        studentMap.put("stuName", "mike");//key:输入参数名，value:赋值输入参数

        List<Student> students = studentMapper.queryStudentBystuageOrstuNameWithHashMap(studentMap) ;//接口的方法->SQL

        System.out.println(students);
        session.close();
    }
    @Test
    //根据地址查学生(嵌套类型对象案例！)
    public void queryStudentByaddress() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;


        StudentMapper studentMapper = session.getMapper( StudentMapper.class) ;
// 			Address address = new Address();
// 			address.setHomeAddress("xa");
// 			address.setSchoolAddress("x");

        Student student = new Student();
        Address address = new Address();
        address.setHomeAddress("beijing");
        address.setSchoolAddress("hangzhou");
        student.setAddress(address);

// 		List<Student> students = studentMapper.queryStudentByaddress(address);
        List<Student> students = studentMapper.queryStudentByaddress(student) ;

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

//					String statement = "org.lanqiao.entity.studentMapper."+"addStudent";
        Student student = new Student(3,"mike",23,"s3");


//					int count = session.insert(statement, student );//statement：指定执行的SQL    student：sql中需要的参数 （ ? ? ? ）
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.addStudent(student);

        session.commit(); //提交事务

        System.out.println("增加成功");
        session.close();
    }
    @Test
    //增加学生（带转换器）
    public void addStudentWithConverter() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

//					String statement = "org.lanqiao.entity.studentMapper."+"addStudent";
        Student student = new Student(4,"Lily",23,"s3");
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
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

//					String statement = "org.lanqiao.entity.studentMapper."+"deleteStudentByStuno";
//
//					int count = session.delete(statement,3) ;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.deleteStudentByStuno(4);

        session.commit(); //提交事务

        System.out.println("删除成功");
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

//					String statement = "org.lanqiao.entity.studentMapper."+"updateStudentByStuno";
        //修改的参数
        Student student = new Student();
        //修改哪个人，where stuno =2
        student.setStuNo(2);
        //修改成什么样子？
        student.setStuName("ls");
        student.setStuAge(24);
        student.setGraName("s1");
        //执行
//					int count = session.update(statement,student) ;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.updateStudentByStuno(student);

        session.commit(); //提交事务

        System.out.println("修改成功");
        session.close();
    }
    @Test
    //调用存储过程
    public void queryCountByGradeWithProcedure() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

//					String statement = "org.lanqiao.entity.studentMapper."+"updateStudentByStuno";

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gName", "s1");
        studentMapper.queryCountByGradeWithProcedure(params);

        //获取存储过程的输出参数
        Object count = params.get("scount");

        System.out.println(count);
        session.close();
    }
    @Test
    //删除存储过程
    public void deleteStuBynoWithProcedure() throws IOException {
        //Connection -  SqlSession操作MyBatis
        //conf.xml - > reader
        Reader reader = Resources.getResourceAsReader("conf.xml") ;
        //reader  ->SqlSession
        //可以通过build的第二参数 指定数据库环境
        SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
        SqlSession session = sessionFacotry.openSession() ;

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sno", 3);
        studentMapper.deleteStuBynoWithProcedure(params);

        session.commit(); //提交事务

        System.out.println("删除存储过程完毕！");
        session.close();
    }
//    public static void main(String[] args) throws IOException {
//		queryStudentByStunoWithConverter();
//		queryStudentByStuno();
//		queryStudentByStuname();
//		queryStudentOrderByColumn();
//		queryStudentBystuageOrstuName();
//        queryStudentByaddress();
//		addStudentWithConverter();
//		queryAllStudents();
//		addStudent();
//		delteStudentByStuno();
//		updateStudentByStuno();
//		queryAllStudents();
//    }
}
