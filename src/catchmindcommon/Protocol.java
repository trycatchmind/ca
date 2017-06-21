package catchmindcommon;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

// 생성불가
// object으로 네트워크상 데이터 주고 받음
public abstract class Protocol implements Serializable {
	
	public static final int ERR_OK = 0;
	public static final int ERR_MEMBER_LOGIN = 10010;
	public static final int ERR_MEMBER_CREATE = 10020;
	public static final int ERR_CHARACTER_ENTER = 10030;
	public static final int ERR_CHARACTER_LEAVE = 10040;
	public static final int ERR_CHARACTER_LIST = 10050;
	public static final int ERR_CHARACTER_CHAT = 10060;					
	public static final int ERR_CHARACTER_CANVAS = 10070;
	
	// 맴버 생성시 구현
	public static final int PROTOCOL_MEMBER_CREATE = 10;
	// 맴버가 로그인할 시
	public static final int PROTOCOL_MEMBER_LOGIN = 20;
	// 캐릭터 진입 구현, 나에 대한 정보만 옴
	public static final int PROTOCOL_CHARACTER_ENTER = 30;
	// 캐릭터 진입시 구현, 다른 캐릭터에 대한 정보만 옴
	public static final int PROTOCOL_OTHERCHARACTER_ENTER = 31;
	//캐릭터가 나갈시 구현, 나에 대한 정보만 옴
	public static final int PROTOCOL_CHARACTER_LEAVE = 40;
	// 캐릭터 나갈시 구현, 다른 캐릭터에 대한 정보만 옴
	public static final int PROTOCOL_OTHERCHARACTER_LEAVE = 41;
	// 캐릭터 진입시 구현, 다른 캐릭터에 대한 모든 정보를 주세요
	public static final int PROTOCOL_CHARACTER_LIST = 50;
	// 캐릭터가 채팅시 구현, 나에 대한 정보만 옴
	public static final int PROTOCOL_CHARACTER_CHAT = 60;
	// 캐릭터가 채팅시 구현, 다른 캐릭터에 대한 정보만 옴
	public static final int PROTOCOL_OTHERCHARACTER_CHAT = 61;
	// 캐릭터가 그림을 보낼시 구현, 내 그림에 대한 정보만 옴
	public static final int PROTOCOL_CHARACTER_CANVAS = 70;
	// 캐릭터가 그림을 보낼시 구현, 다른 캐릭터 그림에 대한 정보만 옴
	public static final int PROTOCOL_OTHERCHARACTER_CANVAS = 71;
	//게임시작될 시 정보를 보내줌
	public static final int PROTOCOL_GAMESTART = 90;
	//게임이 끝날 시에 정보를 보내줌
	public static final int PROTOCOL_GAMEEND=91;
	//게임 한세트 내 하나의 라운드가 시작할 시 정보를 보내줌
	public static final int PROTOCOL_GAMEROUNDSTART = 100;
	//게임 한세트 내 하나의 라운드가 시작할 시 정보를 보내줌
	public static final int PROTOCOL_GAMEROUNDEND=101;
	//타이머 시작
	public static final int PROTOCOL_TIMERSTART = 110;
	//타이머 끝
	public static final int PROTOCOL_TIMEREND= 111;
	//게임의 순번을 부여해 준다
	public static final int PROTOCOL_CAHRACTERORDER= 120;
	
	
	protected int protocol;		//프로토콜 번호
	private int err;
	
	public Protocol(int protocol) {
		this.protocol = protocol;
		setErr(ERR_OK);
	}
	
	public int getProtocol() {
		return protocol;
	}

	public int getErr() {
		return err;
	}

	public void setErr(int err) {
		this.err = err;
	}	
}
