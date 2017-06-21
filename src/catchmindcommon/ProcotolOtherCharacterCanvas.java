package catchmindcommon;

public class ProcotolOtherCharacterCanvas extends ProcotolCharacterCanvas {
	
	public ProcotolOtherCharacterCanvas() {
		this.protocol = PROTOCOL_OTHERCHARACTER_CANVAS;
	}
	
	public ProcotolOtherCharacterCanvas(ProcotolCharacterCanvas protocol) {
		this.protocol = PROTOCOL_OTHERCHARACTER_CANVAS;
		this.memberNo = protocol.memberNo;
		this.name = protocol.name;
		this.image = protocol.image;
	}
}
