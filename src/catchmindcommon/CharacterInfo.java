package catchmindcommon;

import java.io.Serializable;
import java.util.Date;

public class CharacterInfo implements Serializable {	 
	private int memberNo;		//맴버 고유번호
	private String memberID;	//맴버 아이디
	 
	private int characterNo;	//캐릭터 고유번호(DB에서 할당)
	private String characterName; //캐릭터 이름	
	private int characterType;	//캐릭터 타입(0 ~ 5)
	private int characterLevel;	//캐릭터 레벨(1 ~ )
	private long characterExp;	//캐릭터 경험치(0 ~ )  
	private long enDate; //방안에 들어옴과 동시에 시간 부여
	private int characterPanelIndex;	//게임패널 위치 (0 ~ 3)
	private boolean isKing = false; //king 이면 true
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
