package org.legend.mybatis.entity;

//一对一，resultMap形式，即有学生信息，又有学生证信息
public class Student {
	private int stuNo ;
	private String stuName ;
	private int stuAge ;
	private String graName ;
	private boolean stuSex ;
	private StudentCard studentCard;//一对一，resultMap
	private int classId;

	public StudentCard getStudentCard() {
		return studentCard;
	}

	public void setStudentCard(StudentCard studentCard) {
		this.studentCard = studentCard;
	}

	private Address address;//家庭、学校
	
	public Student(int stuNo, String stuName, int stuAge, String graName) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuAge = stuAge;
		this.graName = graName;
	}
	
	public Student(int stuNo, String stuName, int stuAge, String graName, boolean stuSex) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuAge = stuAge;
		this.graName = graName;
		this.stuSex = stuSex;
	}

	public Student(int stuNo, String stuName, int stuAge, String graName, boolean stuSex, Address address) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuAge = stuAge;
		this.graName = graName;
		this.stuSex = stuSex;
		this.address = address;
	}

	public Student() {
	}

	public int getStuNo() {
		return stuNo;
	}

	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}

	public boolean isStuSex() {
		return stuSex;
	}

	public void setStuSex(boolean stuSex) {
		this.stuSex = stuSex;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return this.stuNo+"-"+this.stuName+"-"+this.stuAge+"-"+this.studentCard.getCardId()+"-"+this.studentCard.getCardInfo()+"-"+this.classId;
	}
}
