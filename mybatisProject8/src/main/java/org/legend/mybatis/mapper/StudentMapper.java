package org.legend.mybatis.mapper;

import org.legend.mybatis.entity.Grade;
import org.legend.mybatis.entity.Student;
import org.legend.mybatis.entity.StudentBusiness;
import org.legend.mybatis.entity.StudentClass;

import java.util.List;

//操作Mybatis的接口
public interface StudentMapper {
		/*
		 * 1.方法名和mapper.xml文件中标签的id值相同
		 * 2.方法的 输入参数 和mapper.xml文件中标签的 parameterType类型一致
		 * 3.方法的返回值  和mapper.xml文件中标签的 resultType类型一致
		 * 
		 */
		Student queryStuByNOrAWithSqlTag(Student student);
		List<Student> queryStudentsWithNosInGrade(Grade grade);
		List<Student> queryStudentsWithArray(int[] stuNos);
		List<Student> queryStudentsWithList(List<Integer> stuNos);
		List<Student> queryStudentsWithObjectArray(Student[] stuNos);
		StudentBusiness queryStudentByNoWithOO(int stuno);
		Student queryStudentByNoWithOOResultMap(int stuno);
		StudentClass quearyClassAndStudents(int stuno);

}
