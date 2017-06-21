package catchmindcommon;

//다른 유저의 캐릭터가 접속시
public class ProtocolOtherCharacterEnter extends ProtocolCharacterEnter {	
	
	public ProtocolOtherCharacterEnter() {
		this.protocol = PROTOCOL_OTHERCHARACTER_ENTER;
	}
	
	public ProtocolOtherCharacterEnter(ProtocolCharacterEnter protocol) {
		this.protocol = PROTOCOL_OTHERCHARACTER_ENTER;
		this.setCharacter(protocol.getCharacter());
	}
}
