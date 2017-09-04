package mission01;

import java.sql.*;

public class StudentDataAccessObject {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/java";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public StudentDataAccessObject() {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("jar파일 드라이버 로딩 오류");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public int insertStudent(StudentValueObject student) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "INSERT INTO STUDENT(STUDENT_ID, NAME, GRADE, AGE, GRADUATED_YEAR) VALUES(?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getStudentID());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getGrade());
			pstmt.setInt(4, student.getAge());
			pstmt.setInt(5, student.getGraduatedYear());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insertStudent 에러");
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}

		return result;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	public StudentValueObject selectStudent(int studentID) {
		StudentValueObject result = null;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closePstmt() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeRS() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
