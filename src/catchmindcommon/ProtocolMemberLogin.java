package catchmindcommon;

//로그인 패킷
public class ProtocolMemberLogin extends Protocol {
	int memberNo;		//유저 고유번호
	String id; 			//유저 아이디
	String pw;			//유저 패스워드
	public ProtocolMemberLogin() {
		super(Protocol.PROTOCOL_MEMBER_LOGIN);
	}
	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
}
