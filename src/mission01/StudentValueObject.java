package mission01;

public class StudentValueObject {
	private int StudentID;
	private String name;
	private String grade;
	private int age;
	private int graduatedYear;
/////////////////////////////////////////////////////////////////////////////////////////////////////

	public StudentValueObject() {
	}

	public StudentValueObject(int studentID, String name, String grade, int age, int graduatedYear) {
		super();
		StudentID = studentID;
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.graduatedYear = graduatedYear;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGraduatedYear() {
		return graduatedYear;
	}

	public void setGraduatedYear(int graduatedYear) {
		this.graduatedYear = graduatedYear;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return "StudentVO [StudentID=" + StudentID + ", name=" + name + ", grade=" + grade + ", age=" + age
				+ ", graduatedYear=" + graduatedYear + "]";
	}
	
	

}
