package catchmindserver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

//맴버 VO객체
public class CharacterVO {
	private int cno;
	private String name;
	private int chartype;
	private int charlevel;
	private long charexp;
	private int mno;
	private Date regdate;		

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChartype() {
		return chartype;
	}

	public void setChartype(int chartype) {
		this.chartype = chartype;
	}

	public int getCharlevel() {
		return charlevel;
	}

	public void setCharlevel(int charlevel) {
		this.charlevel = charlevel;
	}

	public long getCharexp() {
		return charexp;
	}

	public void setCharexp(long charexp) {
		this.charexp = charexp;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public static CharacterVO selectOne(int characterno) {
		// sql문 작성
		CharacterVO result = null;
		PreparedStatement pstmt = null;
		ResultSet record = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select cno, name, chartype, charlevel, charexp, mno, regdate from character");
		sb.append(" where cno=?");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setInt(1, characterno);
			
			record = pstmt.executeQuery();		//select 전용
			record.next(); 
			
			result = new CharacterVO();
			result.setCno(record.getInt("cno"));
			result.setName(record.getString("name"));
			result.setChartype(record.getInt("chartype"));
			result.setCharlevel(record.getInt("charlevel"));
			result.setCharexp(record.getLong("charexp"));
			result.setMno(record.getInt("mno"));
			result.setRegdate(record.getDate("regdate"));
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		close(pstmt, record);
		return result;
	}
	
	public static CharacterVO joinOne(int memberno) {
		// sql문 작성
		CharacterVO result = null;
		PreparedStatement pstmt = null;
		ResultSet record = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select ch.cno, ch.name, ch.chartype, ch.charlevel, ch.charexp, ch.mno, ch.regdate from character ch, member mm");
		sb.append(" where ch.mno = mm.mno and mm.mno=?");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setInt(1, memberno);
			
			record = pstmt.executeQuery();		//select 전용
			record.next(); 
			
			result = new CharacterVO();
			result.setCno(record.getInt("ch.cno"));
			result.setName(record.getString("ch.name"));
			result.setChartype(record.getInt("ch.chartype"));
			result.setCharlevel(record.getInt("ch.charlevel"));
			result.setCharexp(record.getLong("ch.charexp"));
			result.setMno(record.getInt("ch.mno"));
			result.setRegdate(record.getDate("ch.regdate"));
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		close(pstmt, record);
		return result;
	}		
	
	public static CharacterVO joinOne(String id) {
		// sql문 작성
		CharacterVO result = null;
		PreparedStatement pstmt = null;
		ResultSet record = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select ch.cno, ch.name, ch.chartype, ch.charlevel, ch.charexp, ch.mno, ch.regdate from character ch, member mm");
		sb.append(" where ch.mno = mm.mno and mm.id=?");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setString(1, id);
			
			record = pstmt.executeQuery();		//select 전용
			record.next(); 
			
			result = new CharacterVO();
			result.setCno(record.getInt("cno"));
			result.setName(record.getString("name"));
			result.setChartype(record.getInt("chartype"));
			result.setCharlevel(record.getInt("charlevel"));
			result.setCharexp(record.getLong("charexp"));
			result.setMno(record.getInt("mno"));
			result.setRegdate(record.getDate("regdate"));
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		close(pstmt, record);
		return result;
	}	
			
	public static ArrayList<CharacterVO> selectAll() {
		// sql문 작성
		ArrayList<CharacterVO> result = new ArrayList<CharacterVO>();
		PreparedStatement pstmt = null;
		ResultSet record = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select cno, name, chartype, charlevel, charexp, mno, regdate from character");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			
			record = pstmt.executeQuery();		//select 전용			
			while(record.next()) { 			
				CharacterVO temp = new CharacterVO();
				temp.setCno(record.getInt("cno"));
				temp.setName(record.getString("name"));
				temp.setChartype(record.getInt("chartype"));
				temp.setCharlevel(record.getInt("charlevel"));
				temp.setCharexp(record.getLong("charexp"));
				temp.setMno(record.getInt("mno"));
				temp.setRegdate(record.getDate("regdate"));
				result.add(temp);
			}						
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		close(pstmt, record);
		return result;
	}
	
	public static CharacterVO insertOne(int memNo, String name, int chartype) {
		// sql문 작성		
		int cust_id = 0;
		CharacterVO vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT character_cno_sq.nextval FROM dual");

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
		sb.append(" insert into CHARACTER");
		sb.append(" values (?, ?, ?, ?, ?, ?, sysdate)");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setInt(1, cust_id);
			pstmt.setString(2, name);
			pstmt.setInt(3, chartype);
			pstmt.setInt(4, 1);
			pstmt.setLong(5, 0);
			pstmt.setInt(6, memNo);
			
			int ret = pstmt.executeUpdate();
			if (1 == ret) {				
				vo = new CharacterVO();
				vo.setCno(cust_id);
				vo.setMno(memNo);
				vo.setName(name);
				vo.setChartype(chartype);
				vo.setCharlevel(1);
				vo.setCharexp(0);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		close(pstmt);
		return vo;
	}
	
	public static int updateOne(CharacterVO vo) {
		// sql문 작성
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" update character");
		sb.append(" set chartype=?, charlevel=?, charexp=?");
		sb.append(" where cno=?");
		try {
			pstmt = DBProc.getInstance().getConnection().prepareStatement(sb.toString());
			pstmt.setInt(1, vo.getChartype());
			pstmt.setInt(2, vo.getCharlevel());
			pstmt.setLong(3, vo.getCharexp());
			pstmt.setInt(4, vo.getCno());
			
			result = pstmt.executeUpdate();		//select 전용
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		close(pstmt);
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
