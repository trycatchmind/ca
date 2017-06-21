package catchmindclient;

import javax.swing.JOptionPane;

import catchmindcommon.CharacterInfo;

public class ClientMain {
	
	// JFrame 창들
	private LogIn loginWindow;			//로그인창	
	private GameClient gameWindow;		//게임창
	private Join joinWindow;			//회원가입창
	
	// 클라이언트 네트워크
	private ClientSideSession	clientSession;
	
	// 사용자 데이터
	// 완성시점 : 로그인한 이후에는 정보가 들어옴(4명까지만 접속허용)  
	private CharacterInfo characterInfo;	
		
	static public ClientMain instance;
	
	private ClientMain() {
		
	}
	
	public LogIn getLoginWindow() {
		return loginWindow;
	}

	public void setLoginWindow(LogIn loginWindow) {
		this.loginWindow = loginWindow;
	}

	public GameClient getGameWindow() {
		return gameWindow;
	}

	public void setGameWindow(GameClient gameWindow) {
		this.gameWindow = gameWindow;
	}

	public Join getJoinWindow() {
		return joinWindow;
	}

	public void setJoinWindow(Join joinWindow) {
		this.joinWindow = joinWindow;
	}

	public ClientSideSession getClientSession() {
		return clientSession;
	}

	public void setClientSession(ClientSideSession clientSession) {
		this.clientSession = clientSession;
	}		
		
	public CharacterInfo getCharacterInfo() {
		return characterInfo;
	}

	public void setCharacterInfo(CharacterInfo characterInfo) {
		this.characterInfo = characterInfo;
	}

	// 싱글톤
	public synchronized static ClientMain getInstance(){
		if(instance == null){
			instance = new ClientMain();
		}
		return instance;
	}	

	public static void main(String[] args) {				 
		
		//처음 클라이언트 시작은 로그인창
		ClientMain.getInstance().setLoginWindow(new LogIn());
		ClientMain.getInstance().setJoinWindow(new Join());
		ClientMain.getInstance().getJoinWindow().setVisible(false);
		
		ClientMain.getInstance().setClientSession(new ClientSideSession());		
		
		// 서버 연결
		while (true) {
			boolean conn = ClientMain.getInstance().getClientSession().connect("ec2-52-78-77-246.ap-northeast-2.compute.amazonaws.com", 5000);
//			boolean conn = ClientMain.getInstance().getClientSession().connect("192.168.0.5", 5000);
			if (false == conn) {
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?");
				if (0 == result) {
					System.exit(0);
				} 
			}
			
			else {
				break;
			}
		}		
				
	}//main method end
}