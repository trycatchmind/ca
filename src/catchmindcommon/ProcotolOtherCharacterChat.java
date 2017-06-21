package catchmindcommon;

public class ProcotolOtherCharacterChat extends ProcotolCharacterChat {
	
	public ProcotolOtherCharacterChat() {
		this.protocol = PROTOCOL_OTHERCHARACTER_CHAT;
	}
	
	public ProcotolOtherCharacterChat(ProcotolCharacterChat protocol) {
		this.protocol = PROTOCOL_OTHERCHARACTER_CHAT;
		this.memberNo = protocol.memberNo;
		this.name = protocol.name;
		this.chat = protocol.chat;
	}
}
