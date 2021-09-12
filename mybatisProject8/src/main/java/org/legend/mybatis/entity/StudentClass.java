package org.legend.mybatis.entity;

import java.util.List;

public class StudentClass {
private int classId;
private String className;
private List<Student> students;//让Student类与StudentClass建立起关联

public int getClassId() {
        return classId;
        }

public void setClassId(int classId) {
        this.classId = classId;
        }

public String getClassName() {
        return className;
        }

public void setClassName(String className) {
        this.className = className;
        }

public List<Student> getStudents() {
        return students;
        }

public void setStudents(List<Student> students) {
        this.students = students;
        }

        @Override
        public String toString() {
                return "Class:{" +
                        "classId=" + classId +
                        ", className='" + className + '\'' + '}'+
                        ", students=" + students;
        }
}
