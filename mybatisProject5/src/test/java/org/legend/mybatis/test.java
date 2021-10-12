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
        Map<String, Object> params = new HashMap<>();
        params.put("gName", "s1");//给存储过程输入参数赋值
        studentMapper.queryCountByGradeWithProcedure(params);

        //获取存储过程的输出参数
        Object count = params.get("scount");//获取存储过程输出参数值

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
        Map<String, Object> params = new HashMap<>();
        params.put("sno", 5);
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
