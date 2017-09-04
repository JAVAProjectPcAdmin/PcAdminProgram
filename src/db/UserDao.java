package db;
/*
 * by.jaein
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		sql= "INSERT INTO USER(ID,PASSWORD,NAME,REGISTER_NUMBER,PHONE,EMAIL_ADDRESS) "
				+ " VALUES(?,?,?,?,?,?)";
		
		try {
			con = DriverManager.getConnection(DB_URL,DB_ID ,DB_PW);
			pstnt = con.prepareStatement(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
