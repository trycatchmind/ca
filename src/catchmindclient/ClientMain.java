package catchmindclient;

import javax.swing.JOptionPane;

import catchmindcommon.CharacterInfo;

public class ClientMain {
	
	// JFrame â��
	private LogIn loginWindow;			//�α���â	
	private GameClient gameWindow;		//����â
	private Join joinWindow;			//ȸ������â
	
	// Ŭ���̾�Ʈ ��Ʈ��ũ
	private ClientSideSession	clientSession;
	
	// ����� ������
	// �ϼ����� : �α����� ���Ŀ��� ������ ����(4������� �������)  
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

	// �̱���
	public synchronized static ClientMain getInstance(){
		if(instance == null){
			instance = new ClientMain();
		}
		return instance;
	}	

	public static void main(String[] args) {				 
		
		//ó�� Ŭ���̾�Ʈ ������ �α���â
		ClientMain.getInstance().setLoginWindow(new LogIn());
		ClientMain.getInstance().setJoinWindow(new Join());
		ClientMain.getInstance().getJoinWindow().setVisible(false);
		
		ClientMain.getInstance().setClientSession(new ClientSideSession());		
		
		// ���� ����
		while (true) {
			boolean conn = ClientMain.getInstance().getClientSession().connect("ec2-52-78-77-246.ap-northeast-2.compute.amazonaws.com", 5000);
//			boolean conn = ClientMain.getInstance().getClientSession().connect("192.168.0.5", 5000);
			if (false == conn) {
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?");
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