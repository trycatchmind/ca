package catchmindcommon;

//회원가입
public class ProtocolMemberCreate extends Protocol {
	int memberNo;		//유저 고유번호
	String id; 			//유저 아이디
	String pwd;			//유저 패스워드
	int characterType;	//캐릭터 타입
	String characterName; //캐릭터 이름
	
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
