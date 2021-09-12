package org.legend.mybatis.mapper;

import org.legend.mybatis.entity.Grade;
import org.legend.mybatis.entity.Student;

import java.util.List;
import java.util.Map;

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


}
