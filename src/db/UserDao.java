package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	
	
	
	
}
