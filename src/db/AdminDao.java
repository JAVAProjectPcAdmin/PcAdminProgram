package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
	PreparedStatement pstmt = null;
	Connection con = null;
	String sql = null;
	int result;
	ResultSet rs;

	private static final String DB_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/java";
	private static final String DB_ID = "root";
	private static final String DB_PW = "sds1501";

	public AdminDao() {
		try {
			Class.forName(DB_Driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void AdminJoinInsert(AdminVO admin) {

		sql = "INSERT INTO ADMIN(ID, PASSWORD) " + " VALUES(?,?)";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, admin.getId());
			pstmt.setString(2, admin.getPassword());

			result = pstmt.executeUpdate();
			System.out.println("insert : " + result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public String AdminIdSelect(String Id) {
		String id = null;
		sql = "SELECT ID FROM ADMIN WHERE ID=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, Id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return id;

	}
	
	public String AdminPWSelect(String Id) {
		String pw = null;
		sql = "SELECT PASSWORD FROM ADMIN WHERE ID=?";

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, Id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				pw = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
			closeRS();
		}
		return pw;
	}
	
	public void adminUpdate(AdminVO admin, String adminId) {
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "UPDATE admin SET PASSWORD = ? WHERE ID = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, admin.getPassword());
			pstmt.setString(2, adminId);
			

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	public void close() {
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
				this.close();
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
