package catchmindcommon;

public class ProtocolCharacterList extends Protocol {
	CharacterInfo [] charList;
	
	public ProtocolCharacterList() {
		super(Protocol.PROTOCOL_CHARACTER_LIST);
	}

	public CharacterInfo[] getCharList() {
		return charList;
	}

	public void setCharList(CharacterInfo[] charList) {
		this.charList = charList;
	}		
}
