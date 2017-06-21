package catchmindserver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

//맴버 VO객체
public class MemberVO {
	private int mno;
	private String id;
	private String pw;
	private Date regdate;

	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public static MemberVO insertOne(String id, String pwd) {
		// sql문 작성		
		int cust_id = 0;
		MemberVO vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT member_mno_sq.nextval FROM dual");

		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());		
			rs = pstmt.executeQuery();
	
			if ( rs!=null && rs.next() ) {
				cust_id = rs.getInt(1);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(pstmt, rs);		
		if (0 == cust_id) {
			return vo;
		}
		
		sb.setLength(0);
		sb.append(" insert into member");
		sb.append(" values (?, ?, ?, sysdate)");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setInt(1, cust_id);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			
			int ret = pstmt.executeUpdate();
			if (1 == ret) {				
				vo = new MemberVO();
				vo.setMno(cust_id);
				vo.setId(id);
				vo.setPw(pwd);
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}

		close(pstmt);
		return vo;
	}
	
	// 맴버 pk값으로 select 결과
	public static MemberVO selectOne(int no) {
		// sql문 작성
		MemberVO result = null;
		PreparedStatement pstmt = null;
		ResultSet record = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select mno, id, pw, regdate from member");
		sb.append(" where mno=?");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setInt(1, no);
			
			record = pstmt.executeQuery();		//select 전용
			record.next(); 
			
			result = new MemberVO();
			result.setMno(record.getInt("mno"));
			result.setId(record.getString("id"));
			result.setPw(record.getString("pw"));
			result.setRegdate(record.getDate("regdate"));
			
		} catch (SQLException e) {			
			e.printStackTrace();			
		}
		
		close(pstmt, record);
		
		return result;
	}
	
	// 맴버 id값으로 select 결과
	public static MemberVO selectOne(String id) {
		// sql문 작성
		MemberVO result = null;
		PreparedStatement pstmt = null;
		ResultSet record = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select mno, id, pw, regdate from member");
		sb.append(" where id=?");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setString(1, id);
			
			record = pstmt.executeQuery();		//select 전용
			record.next(); 
			
			result = new MemberVO();
			result.setMno(record.getInt("mno"));
			result.setId(record.getString("id"));
			result.setPw(record.getString("pw"));
			result.setRegdate(record.getDate("regdate"));			
			
		} catch (SQLException e) {			
			e.printStackTrace();
			close(pstmt, record);
			return null;
		}

		close(pstmt, record);
		return result;
	}
	
	// 맴버 select 결과 리스트
	public static ArrayList<MemberVO> selectAll() {
		// sql문 작성
		ArrayList<MemberVO> result = new ArrayList<MemberVO>();
		PreparedStatement pstmt = null;
		ResultSet record = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select mno, id, pw, regdate from member");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			
			record = pstmt.executeQuery();		//select 전용			
			while(record.next()) { 			
				MemberVO temp = new MemberVO();
				temp.setMno(record.getInt("mno"));
				temp.setId(record.getString("id"));
				temp.setPw(record.getString("pw"));
				temp.setRegdate(record.getDate("regdate"));
				result.add(temp);
			}						
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		close(pstmt, record);
		return result;
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt, ResultSet record) {
		try {
			if (record != null) record.close();
			if (pstmt != null) pstmt.close();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
