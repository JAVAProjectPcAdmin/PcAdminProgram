package db;

/*
 * by.jaein
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;

public class UserDao {
	PreparedStatement pstmt = null;
	Connection con = null;
	String sql = null;
	int result;
	ResultSet rs;

	private static final String DB_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/java";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";

	public UserDao() {
		try {
			Class.forName(DB_Driver);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void UserJoinInsert(UserVO user) {
		Date date = new Date();

		sql = "INSERT INTO USER(ID,PASSWORD,NAME,RESIDENT_NUMBER,PHONE,EMAIL_ADDRESS,ADDRESS,REGISTER_DATE) "
				+ " VALUES(?,?,?,?,?,?,?,?)";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getResidentNumber());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getEmailAddress());
			pstmt.setString(7, user.getAddress());
			pstmt.setString(8, date.toString());

			result = pstmt.executeUpdate();
			System.out.println("insert : " + result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public int UserIdSelect(String Id) {
		int num = -1;
		sql = "SELECT USER_NUMBER FROM USER WHERE ID=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, Id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return num;

	}

	public String UserNameSelect(String Id) {
		String name = null;
		sql = "SELECT NAME FROM USER WHERE ID=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, Id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			closeRS();

		}
		return name;
	}

	public int UserLoginCheck(String id, String password) {
		int num;
		String check = null;
		sql = "SELECT PASSWORD FROM USER WHERE ID=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = rs.getString(1);

				if (check.equals(password)) {
					result = 1;
				} else {
					result = 0;
				}

			} else {
				result = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return result;

	}

	public String IdSearch(String name, String email, String phoneNumber) {
		String id = null;

		sql = "SELECT ID FROM USER WHERE NAME=? AND EMAIL_ADDRESS=? AND PHONE=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, phoneNumber);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			closeRS();

		}
		return id;
	}

	public String PwSearch(String id, String resident_number) {
		String pw = null;

		sql = "SELECT PASSWORD FROM USER WHERE ID=? AND RESIDENT_NUMBER=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, resident_number);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				pw = rs.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			closeRS();

		}
		return id;
	}

	/////////////////////////////////// 비회원Dao
	public String nonMemberIdSelect(String non_Id) {
		String non_name = null;

		sql = "SELECT NAME FROM NONMEMBER WHERE NON_ID=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, non_Id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				non_name = rs.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			closeRS();

		}
		return non_name;

	}

	public int nonMemberLoginCheck(String non_Id) {
		int num;
		String check = null;
		sql = "SELECT NON_ID FROM NONMEMBER WHERE NON_ID=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, non_Id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = rs.getString(1);

				if (check.equals(non_Id)) {
					result = 1;
				} else {
					result = 0;
				}

			} else {
				result = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return result;

	}

	public void userUpdate(UserVO user) {
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "UPDATE USER SET PASSWORD=?, NAME=?, PHONE=?, EMAIL_ADDRESS=?, ADDRESS=?, MEMO=? ";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getEmailAddress());
			pstmt.setString(5, user.getAddress());
			pstmt.setString(6, user.getMemo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 회원 이름으로 검색 -> 회원번호, 이름, 아이디, 등록일자, 생년월일
	public List<UserVO> userNameSelectList(String name) {
<<<<<<< HEAD

=======
>>>>>>> c1159431e89ac994084f4e1bd2f38caa9ae792ab
		ArrayList<UserVO> userList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT * FROM USER WHERE NAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserVO result = new UserVO();

				result.setUserNumber(rs.getInt(1));
				result.setId(rs.getString(2));
				result.setPassword(rs.getString(3));
				result.setName(rs.getString(4));
				result.setResidentNumber(rs.getString(5).substring(0, 6));
				result.setPhone(rs.getString(6));
				result.setEmailAddress(rs.getString(7));
				result.setAddress(rs.getString(8));
				result.setRegisterDate(rs.getString(9));
				result.setMemo(rs.getString(10));
<<<<<<< HEAD
=======


>>>>>>> c1159431e89ac994084f4e1bd2f38caa9ae792ab
				userList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return userList;
	}

<<<<<<< HEAD
	// 회원번호로 검색(테이블 눌렀을 때 필요) -> 등록일자 빼고
	public List<UserVO> userNumSelectList(int userNum) {

		ArrayList<UserVO> userList = new ArrayList<>();
=======
	ArrayList<UserVO> userList = new ArrayList<>();

	// 회원번호로 검색(테이블 눌렀을 때 필요) -> 등록일자 빼고
	public UserVO userNumSelectList(int userNum) {
		UserVO user = new UserVO();
>>>>>>> c1159431e89ac994084f4e1bd2f38caa9ae792ab
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT * FROM USER WHERE USER_NUMBER = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserVO result = new UserVO();

				result.setUserNumber(rs.getInt(1));
				result.setId(rs.getString(2));
				result.setPassword(rs.getString(3));
				result.setName(rs.getString(4));

				result.setResidentNumber(rs.getString(5).substring(0, 6));
				result.setPhone(rs.getString(6));
				result.setEmailAddress(rs.getString(7));
				result.setAddress(rs.getString(8));
				result.setRegisterDate(rs.getString(9));
				result.setMemo(rs.getString(10));

				userList.add(result);
<<<<<<< HEAD

=======
			}
			if (rs.next()) {
				user.setUserNumber(rs.getInt(1));
				user.setId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setResidentNumber(rs.getString(5).substring(0, 6));
				user.setPhone(rs.getString(6));
				user.setEmailAddress(rs.getString(7));
				user.setAddress(rs.getString(8));
				user.setRegisterDate(rs.getString(9));
				user.setMemo(rs.getString(10));
>>>>>>> c1159431e89ac994084f4e1bd2f38caa9ae792ab
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return userList;
	}

	// 모든 정보
	public List<UserVO> userInfoList() {
<<<<<<< HEAD

=======
>>>>>>> c1159431e89ac994084f4e1bd2f38caa9ae792ab
		ArrayList<UserVO> userList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT * FROM USER";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserVO result = new UserVO();

				result.setUserNumber(rs.getInt(1));
				result.setId(rs.getString(2));
				result.setPassword(rs.getString(3));
				result.setName(rs.getString(4));
				result.setResidentNumber(rs.getString(5).substring(0, 6));
				result.setPhone(rs.getString(6));
				result.setEmailAddress(rs.getString(7));
				result.setAddress(rs.getString(8));
				result.setRegisterDate(rs.getString(9));
				result.setMemo(rs.getString(10));

				userList.add(result);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return userList;
	}

	public void close() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				this.close();
				e.printStackTrace();
			}
		}
	}

	public void closeRS() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
