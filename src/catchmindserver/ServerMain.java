package catchmindserver;

import java.util.TimerTask;

public class ServerMain { 
	
	public static void main(String[] args) {
		//���� ui ����
		ServerUI cs = ServerUI.getInstance();
		
		//db Ŀ�ؼ� �ϳ��� �����		
		DBProc.getInstance().connect();
		
		//���ӹ� �ϳ��� ������
		GameRoom gr = GameRoom.getInstance();
		
		//���� ��Ʈ��ũ ��ü ����
		ServerNetwork.getInstance().start();		
	}	
}
