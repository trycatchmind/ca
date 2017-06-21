package catchmindserver;

import java.util.Iterator;
import java.util.TreeSet;

import catchmindcommon.CharacterInfo;

public class GameRoom {

	public final static int MAX_ROOM_CHARACTER_COUNT = 4;

	private CharacterInfo[] charList = new CharacterInfo[MAX_ROOM_CHARACTER_COUNT];
	private static GameRoom instance;
	private int characterCount = 0;
	private TreeSet<Long> enDate = new TreeSet<Long>();
	private CharacterInfo[] charTurn = new CharacterInfo[4];

	public CharacterInfo[] getCharTurn() {
		return charTurn;
	}

	public void setCharTurn(CharacterInfo[] charTurn) {
		this.charTurn = charTurn;
	}

	public TreeSet<Long> getEnDate() {
		return enDate;
	}

	public void setEnDate(TreeSet<Long> enDate) {
		this.enDate = enDate;
	}

	public int getCharacterCount() {
		return characterCount;
	}

	public void setCharacterCount(int characterCount) {
		this.characterCount = characterCount;
	}

	private GameRoom() {

	}

	public synchronized CharacterInfo[] getCharList() {
		return charList;
	}

	public synchronized void setCharList(CharacterInfo[] charList) {
		this.charList = charList;
	}

	public synchronized CharacterInfo getCharacter(String name) {
		for (int i = 0; i < charList.length; ++i) {
			CharacterInfo character = charList[i];
			if (null != character && name.equalsIgnoreCase(character.getCharacterName())) {
				return character;
			}
		}

		return null;
	}

	public synchronized CharacterInfo getCharacter(int cno) {
		for (int i = 0; i < charList.length; ++i) {
			CharacterInfo character = charList[i];
			if (null != character && cno == character.getCharacterNo()) {
				return character;
			}
		}

		return null;
	}

	public synchronized int characterAdd(CharacterInfo character) {
		for (int i = 0; i < charList.length; ++i) {
			CharacterInfo temp = charList[i];
			if (null == temp) {
				character.setCharacterPanelIndex(i);
				charList[i] = character;
				characterCount++;
				charList[i].setEnDate(System.currentTimeMillis());
				enDate.add(charList[i].getEnDate());
				return i;
			}
		}
		return -1;
	}

	public synchronized int characterDel(CharacterInfo character) {
		for (int i = 0; i < charList.length; ++i) {
			CharacterInfo temp = charList[i];
			if (null != temp && temp.equals(character)) {
				character.setCharacterPanelIndex(-1);
				enDate.remove(charList[i].getEnDate());
				charList[i] = null;
				characterCount--;
				return i;
			}
		}
		return -1;
	}

	public synchronized void characterTurn(CharacterInfo[] charTurn) {
		Iterator<Long> itr = enDate.iterator();
		int j = -1;
		while (itr.hasNext()) {
			j++;

			for (int i = 0; i < enDate.size(); i++) {
				if (charList[i].getEnDate() == itr.next()) {
					charTurn[j] = charList[i];
				}
			}
		}
	}

	// ½Ì±ÛÅæ
	public synchronized static GameRoom getInstance() {
		if (instance == null) {
			instance = new GameRoom();
		}
		return instance;
	}

	public synchronized int delegateKing() {
		for (int i = 0; i < charList.length; i++) {

			if (charList[i] != null && charList[i].getEnDate() == enDate.first()) {
				charList[i].setKing(true);

				return i;
			}
		}
		return -1;
	}
}
