package catchmindcommon;

//ȸ������
public class ProtocolMemberCreate extends Protocol {
	int memberNo;		//���� ������ȣ
	String id; 			//���� ���̵�
	String pwd;			//���� �н�����
	int characterType;	//ĳ���� Ÿ��
	String characterName; //ĳ���� �̸�
	
	public ProtocolMemberCreate() {
		super(Protocol.PROTOCOL_MEMBER_CREATE);
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getCharacterType() {
		return characterType;
	}

	public void setCharacterType(int characterType) {
		this.characterType = characterType;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}	
}
