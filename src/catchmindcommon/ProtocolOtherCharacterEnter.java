package catchmindcommon;

//�ٸ� ������ ĳ���Ͱ� ���ӽ�
public class ProtocolOtherCharacterEnter extends ProtocolCharacterEnter {	
	
	public ProtocolOtherCharacterEnter() {
		this.protocol = PROTOCOL_OTHERCHARACTER_ENTER;
	}
	
	public ProtocolOtherCharacterEnter(ProtocolCharacterEnter protocol) {
		this.protocol = PROTOCOL_OTHERCHARACTER_ENTER;
		this.setCharacter(protocol.getCharacter());
	}
}
