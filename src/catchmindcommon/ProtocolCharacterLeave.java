package catchmindcommon;

public class ProtocolCharacterLeave extends Protocol {
	CharacterInfo character;
	
	public ProtocolCharacterLeave() {
		super(Protocol.PROTOCOL_CHARACTER_LEAVE);
	}

	public CharacterInfo getCharacter() {
		return character;
	}

	public void setCharacter(CharacterInfo character) {
		this.character = character;
	}		
}