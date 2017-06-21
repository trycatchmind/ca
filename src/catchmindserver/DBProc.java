package catchmindserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

// DB 연결객체(DAO)
public class DBProc {
	
	// 싱글톤 객체
	private static DBProc instance;
	
	// 드라이버 정보 및 DB 연결정보
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@orcl.czvh0mifrkj5.ap-northeast-2.rds.amazonaws.com:1521:orcl";

	String user="scott";
	String password="catchmind";		
		
	Connection conn = null;	
	
	private DBProc() {
		
	}
	
	// db 연결
	public void connect() {
		try {
			
			Class.forName(driver);			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn: "+conn);
		} 
		catch (SQLException e) {				
			System.out.println("DB 접속 실패");
			e.printStackTrace();
			close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			close();
		}		
	}
	
	// db연결 종료
	public void close() {
		try {		
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public Connection getConnection() {
		return conn;
	}
	
	public synchronized static DBProc getInstance(){
		if(instance == null){
			instance = new DBProc();
		}
		return instance;
	}
}
