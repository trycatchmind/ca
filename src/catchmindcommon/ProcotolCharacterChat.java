package catchmindcommon;

public class ProcotolCharacterChat extends Protocol {
	int memberNo;		//���� ������ȣ
	String name; 		//ĳ���� �̸�
	String chat;		//ä��
	
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
