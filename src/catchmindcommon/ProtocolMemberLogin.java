package catchmindcommon;

//�α��� ��Ŷ
public class ProtocolMemberLogin extends Protocol {
	int memberNo;		//���� ������ȣ
	String id; 			//���� ���̵�
	String pw;			//���� �н�����
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
