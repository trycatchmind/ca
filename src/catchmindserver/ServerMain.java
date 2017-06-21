package catchmindserver;

import java.util.TimerTask;

public class ServerMain { 
	
	public static void main(String[] args) {
		//서버 ui 시작
		ServerUI cs = ServerUI.getInstance();
		
		//db 커넥션 하나만 사용함		
		DBProc.getInstance().connect();
		
		//게임방 하나만 존재함
		GameRoom gr = GameRoom.getInstance();
		
		//서버 네트워크 객체 시작
		ServerNetwork.getInstance().start();		
	}	
}
