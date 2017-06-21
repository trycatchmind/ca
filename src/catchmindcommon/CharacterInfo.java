package catchmindcommon;

import java.io.Serializable;
import java.util.Date;

public class CharacterInfo implements Serializable {	 
	private int memberNo;		//�ɹ� ������ȣ
	private String memberID;	//�ɹ� ���̵�
	 
	private int characterNo;	//ĳ���� ������ȣ(DB���� �Ҵ�)
	private String characterName; //ĳ���� �̸�	
	private int characterType;	//ĳ���� Ÿ��(0 ~ 5)
	private int characterLevel;	//ĳ���� ����(1 ~ )
	private long characterExp;	//ĳ���� ����ġ(0 ~ )  
	private long enDate; //��ȿ� ���Ȱ� ���ÿ� �ð� �ο�
	private int characterPanelIndex;	//�����г� ��ġ (0 ~ 3)
	private boolean isKing = false; //king �̸� true
	private int correct;
	private int characterTrun;
	
	
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public boolean isKing() {
		return isKing;
	}
	public void setKing(boolean isKing) {
		this.isKing = isKing;
	}
	public long getEnDate() {
		return enDate;
	}
	public void setEnDate(long enDate) {
		this.enDate = enDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getCharacterNo() {
		return characterNo;
	}
	public void setCharacterNo(int characterNo) {
		this.characterNo = characterNo;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public int getCharacterType() {
		return characterType;
	}
	public void setCharacterType(int characterType) {
		this.characterType = characterType;
	}
	public int getCharacterLevel() {
		return characterLevel;
	}
	public void setCharacterLevel(int characterLevel) {
		this.characterLevel = characterLevel;
	}
	public long getCharacterExp() {
		return characterExp;
	}
	public void setCharacterExp(long characterExp) {
		this.characterExp = characterExp;
	}
	public int getCharacterPanelIndex() {
		return characterPanelIndex;
	}
	public void setCharacterPanelIndex(int characterPanelIndex) {
		this.characterPanelIndex = characterPanelIndex;
	}	
}
