package catchmindcommon;

public class ProcotolCharacterChat extends Protocol {
	int memberNo;		//유저 고유번호
	String name; 		//캐릭터 이름
	String chat;		//채팅
	
	public ProcotolCharacterChat() {
		super(Protocol.PROTOCOL_CHARACTER_CHAT);
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}				
}
