package catchmindcommon;

public class ProtocolOtherCharacterLeave extends ProtocolCharacterLeave {	
	
	public ProtocolOtherCharacterLeave() {
		this.protocol = PROTOCOL_OTHERCHARACTER_LEAVE;
	}
	
	public ProtocolOtherCharacterLeave(ProtocolCharacterLeave protocol) {
		this.protocol = PROTOCOL_OTHERCHARACTER_LEAVE;
		this.setCharacter(protocol.getCharacter());
	}
}
