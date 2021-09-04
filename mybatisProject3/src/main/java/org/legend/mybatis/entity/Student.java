package org.legend.mybatis.entity;

/**
 * 补充：idea 连接数据库 并自动生成实体对象
 * https://blog.csdn.net/zhaoyy0513/article/details/103234093
 * https://blog.csdn.net/qq_27435059/article/details/52494623
 */
public class Student {
    private int stuNo;
    private String stuName;
    private int stuAge;
    private String graName;
    private boolean stuSex;

    public Student(int stuNo, String stuName, int stuAge, String graName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.graName = graName;
    }

    public Student() {
    }

    public boolean isStuSex() {
        return stuSex;
    }

    public void setStuSex(boolean stuSex) {
        this.stuSex = stuSex;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    @Override
    public String toString() {
        return "\nStudent: " +
                " stuNo=" + this.stuNo +
                " stuName='" + this.stuName + '\'' +
                " stuAge=" + this.stuAge +
                " graName='" + this.graName + '\'' +
                " stuSex='" + this.stuSex + '\'' +
                "\n";
    }
}
