package mission01;
import java.sql.*;
import java.util.Scanner;

public class Mission {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int selected;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mission", "root", "sds1501");

			Scanner sc = new Scanner(System.in);
			System.out.println("1. 학생 정보 추가, 2. 학생 정보 삭제, 3. 학생 정보 업데이트");
			selected = sc.nextInt();

			switch (selected) {
			case 1:
				System.out.print("학번:");
				int student_id = sc.nextInt();
				sc.nextLine();
				System.out.print("이름:");
				String name = sc.nextLine();

				System.out.print("성적:");
				String grade = sc.nextLine();

				System.out.print("나이:");
				int age = sc.nextInt();

				System.out.print("졸업년도:");
				int year = sc.nextInt();

				String joinSql = "INSERT INTO STUDENT (STUDENT_ID, NAME, GRADE, AGE, GRADUATED_YEAR)"
						+ "VALUES(?,?,?,?,?)";

				pstmt = con.prepareStatement(joinSql);

				pstmt.setInt(1, student_id);
				pstmt.setString(2, name);
				pstmt.setString(3, grade);
				pstmt.setInt(4, age);
				pstmt.setInt(5, year);

				int result = pstmt.executeUpdate();

				System.out.println("쿼리 수행 결과:" + result);

				break;

			case 2:
				String deleteSql = "DELETE FROM STUDENT";
				pstmt = con.prepareStatement(deleteSql);

				result = pstmt.executeUpdate();

				System.out.println("쿼리 수행 결과:" + result);

				break;

			case 3:
				String updateSql = "UPDATE STUDENT SET GRADE='A' WHERE GRADE='B'";
				pstmt = con.prepareStatement(updateSql);

				result = pstmt.executeUpdate();

				System.out.println("쿼리 수행 결과:" + result);

				break;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 7. 사용한 자원 반납
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
