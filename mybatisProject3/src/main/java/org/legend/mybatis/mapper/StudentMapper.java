package org.legend.mybatis.mapper;

import org.legend.mybatis.entity.Student;

import java.util.List;

/**
 * 1.操作Mybatis的接口,接口的根路径(org.legend.mapper.StudentMapper)就是namespace
 * 1.2 加载顺序：conf.xml-->studentMapper.xml-->Mapper接口
 */
public interface StudentMapper {//代替sesion.statement==namespace.id
    /**
     * 2.
     * 2.1 方法名和mapper.xml文件中标签的id值相同
     * 2.2 方法的 输入参数 和mapper.xml文件中标签的 parameterType类型一致
     * 2.3 方法的返回值  和mapper.xml文件中标签的 resultType类型一致
     */
    //查询单个
    Student queryStudentByStuno(int stuNo);
    Student queryStudentByStunoWithConverter(int stuno);

    //查询全部
    List<Student> queryAllStudents();

    //增加
    void addStudent(Student student);
    void addStudentWithConverter(Student student);

    //删除
    void deleteStudentByStuno(int stuNo);

    //修改
    void updateStudentByStuno(Student student);

}
