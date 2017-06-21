package catchmindserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

// DB ���ᰴü(DAO)
public class DBProc {
	
	// �̱��� ��ü
	private static DBProc instance;
	
	// ����̹� ���� �� DB ��������
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@orcl.czvh0mifrkj5.ap-northeast-2.rds.amazonaws.com:1521:orcl";

	String user="scott";
	String password="catchmind";		
		
	Connection conn = null;	
	
	private DBProc() {
		
	}
	
	// db ����
	public void connect() {
		try {
			
			Class.forName(driver);			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn: "+conn);
		} 
		catch (SQLException e) {				
			System.out.println("DB ���� ����");
			e.printStackTrace();
			close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
			close();
		}		
	}
	
	// db���� ����
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
