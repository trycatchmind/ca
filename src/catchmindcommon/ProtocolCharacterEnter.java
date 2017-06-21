package catchmindcommon;

// 캐릭터로 접속
public class ProtocolCharacterEnter extends Protocol {
	CharacterInfo character;
	
	public ProtocolCharacterEnter() {
		super(Protocol.PROTOCOL_CHARACTER_ENTER);
	}	

	public CharacterInfo getCharacter() {
		return character;
	}

	public void setCharacter(CharacterInfo character) {
		this.character = character;
	}	
}