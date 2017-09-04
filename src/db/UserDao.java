package db;
/*
 * by.jaein
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDao {
	PreparedStatement pstnt = null;
	Connection con = null;
	String sql = null;
	int result;
	ResultSet rs; 
	
	private static final String DB_Driver="com.mysql.jdbc.Driver";
	private static final String DB_URL="jdbc:mysql://127.0.0.1:3306/java";
	private static final String DB_ID="root";
	private static final String DB_PW ="sds1501";
	
	public UserDao() {
		try {
			Class.forName(DB_Driver);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void UserJoinInsert(UserVO user) {
		Date date= new Date();
		
		sql= "INSERT INTO USER(ID,PASSWORD,NAME,RESIDENT_NUMBER,PHONE,EMAIL_ADDRESS,ADDRESS,REGISTER_DATE) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			con = DriverManager.getConnection(DB_URL,DB_ID ,DB_PW);
			pstnt = con.prepareStatement(sql);
			
			pstnt.setString(1, user.getId());
			pstnt.setString(2, user.getPassword());
			pstnt.setString(3, user.getName());
			pstnt.setString(4, user.getResidentNumber());
			pstnt.setString(5, user.getPhone());
			pstnt.setString(6, user.getEmailAddress());
			pstnt.setString(7, user.getAddress());
			pstnt.setString(8, date.toString());
			
			result = pstnt.executeUpdate();
			System.out.println("insert : "+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
	}
	public int UserIdSelect(String Id) {
		int num=-1;
		sql = "SELECT USER_NUMBER FROM USER WHERE ID=?";
		
		try {
			con = DriverManager.getConnection(DB_URL,DB_ID,DB_PW);
			pstnt=con.prepareStatement(sql);
			
			pstnt.setString(1, Id);
			
			rs=pstnt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
			closeRS();
		}
		return num;
	
	}
	
	
	public void close() {
		if (pstnt != null) {
			try {
				pstnt.close();
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
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}
