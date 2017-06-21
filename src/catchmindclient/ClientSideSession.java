package catchmindclient;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

import catchmindcommon.CharacterInfo;
import catchmindcommon.ProcotolCharacterCanvas;
import catchmindcommon.ProcotolCharacterChat;
import catchmindcommon.ProcotolOtherCharacterCanvas;
import catchmindcommon.ProcotolOtherCharacterChat;
import catchmindcommon.Protocol;
import catchmindcommon.ProtocolCharacterEnter;
import catchmindcommon.ProtocolCharacterLeave;
import catchmindcommon.ProtocolCharacterList;
import catchmindcommon.ProtocolMemberCreate;
import catchmindcommon.ProtocolMemberLogin;
import catchmindcommon.ProtocolOtherCharacterEnter;
import catchmindcommon.ProtocolOtherCharacterLeave;
import catchmindcommon.Session;

public class ClientSideSession extends Session {	
	
	// �α��� ���� ������(���̵�, �н�����)
	public void sendMemberCreate(String id, String pwd, int charType, String name) {
		ProtocolMemberCreate create = new ProtocolMemberCreate();			
		create.setId(id);
		create.setPwd(pwd);
		create.setCharacterType(charType);
		create.setCharacterName(name);
		
		send(create);		
	}
	
	// �α��� ���� ������(���̵�, �н�����)
	public void sendMemberLogin(String id, String pwd) {
		ProtocolMemberLogin login = new ProtocolMemberLogin();			
		login.setId(id);
		login.setPw(pwd);
		
		send(login);		
	}
	
	// �� ĳ���Ͱ� ���ӿ� ���ٰ� �˸���
	public void sendCharacterEnter() {
		ProtocolCharacterEnter character = new ProtocolCharacterEnter();
		character.setCharacter(ClientMain.getInstance().getCharacterInfo());		
        
        send(character);
	}
	
	// �� ĳ���Ͱ� ���ӿ� ���ٰ� �˸���
	public void sendCharacterList() {
		ProtocolCharacterList charList = new ProtocolCharacterList();
        
        send(charList);
	}
	
	// ä���ϱ�
	public void sendCharacterChat(String str) {
		ProcotolCharacterChat chat = new ProcotolCharacterChat();
		chat.setChat(str);

		send(chat);
	}
	
	// �׸� ��������
	public void sendCharacterCanvas(BufferedImage image) {
		ProcotolCharacterCanvas canvas = new ProcotolCharacterCanvas();
		canvas.setImage(image);

		send(canvas);
	}
	
	@Override
	public void initialize() {}
	
	@Override
	public void release() {}
	
	// �α��� ����
	@Override
	public void recvMemberLogin(Protocol pack) {
		ProtocolMemberLogin login = (ProtocolMemberLogin)pack;	
		
		if (Protocol.ERR_OK == login.getErr()) {
			System.out.println("�α��� ����");			
			
			//�α���â�� ������
			ClientMain.getInstance().getLoginWindow().setVisible(false);
			//���� JFrame�� ���̱�
			ClientMain.getInstance().setGameWindow(new GameClient());
			ClientMain.getInstance().getGameWindow().setVisible(true);

			//�������� ���� ���� ������ ä���
			CharacterInfo character = new CharacterInfo(); 
			character.setMemberNo(login.getMemberNo());
			character.setMemberID(login.getId());
			
			ClientMain.getInstance().setCharacterInfo(character); 			
			
			//�α��� ������ �� ĳ���Ͱ� ���ӿ� �����Ѵٰ� ���� �������� �˸���         
			sendCharacterEnter();
		}
		else {
			System.out.println("�α��� ����");
			
			JOptionPane.showMessageDialog(null, "�α��� �����Ͽ����ϴ�. �ٽ� �Է��� �ּ���.");
		}
	}
	
	// �� ĳ���Ͱ� ���ӿ� ����
	@Override
	public void recvCharacterEnter(Protocol pack) {
		ProtocolCharacterEnter character = (ProtocolCharacterEnter)pack;	
			
		if (Protocol.ERR_OK == character.getErr()) {									
			
			//�������� ���� ���� ������ ä���	
			ClientMain.getInstance().setCharacterInfo(character.getCharacter());						
			
			//������ ĳ���� �޶�� ��û
			sendCharacterList();			
		}
		else {
			System.out.println("�� ĳ���� ������ ����");
		}
	}
	
	
	@Override
	public void recvCharacterList(Protocol pack) {		
		ProtocolCharacterList charList = (ProtocolCharacterList)pack;	
		
		if (Protocol.ERR_OK == charList.getErr()) {									
			
			//�������� ���� ���� ������ ä���
			CharacterInfo[] chInfo = charList.getCharList();
			
			//�ٸ� ĳ���� ������ ä���.
			for (int i = 0; i < chInfo.length; ++i)	{
				if (null != chInfo[i]) {
					CharacterPanel panel = ClientMain.getInstance().getGameWindow().character[chInfo[i].getCharacterPanelIndex()];
					panel.jlCharacterImg.setIcon(new ImageIcon("src/catchmindimg/robot" + chInfo[i].getCharacterType() + ".png"));
					panel.jlCharacterName.setText(chInfo[i].getCharacterName());
					panel.jlCharacterLevel.setText(String.valueOf(chInfo[i].getCharacterLevel()));
					panel.jlCharacterCorrect.setText(String.valueOf(chInfo[i].getCharacterExp()));
					
				
				}
			}					
		}
		else {
			System.out.println("ĳ���� ���� ����");
		}
	}

	//���� �׸� ĵ������ ������ �� ���, ���� ����
	@Override
	public void recvCharacterCanvas(Protocol pack) {
		ProcotolCharacterCanvas canvas = (ProcotolCharacterCanvas)pack;
	}

	@Override
	public void recvOtherCharacterCanvas(Protocol pack) {
		ProcotolOtherCharacterCanvas canvas = (ProcotolOtherCharacterCanvas)pack;
		
		if (Protocol.ERR_OK == canvas.getErr()) {
			MyCanvas otherCanvas = ClientMain.getInstance().getGameWindow().getMyCanvas();
			otherCanvas.imageImport(canvas.getImage());						
		}		
	}

	// �ٸ� ������ ĳ���Ͱ� ���ӿ� ����
	@Override
	public void recvOtherCharacterEnter(Protocol pack) {
		ProtocolOtherCharacterEnter character = (ProtocolOtherCharacterEnter)pack;	
			
		if (Protocol.ERR_OK == character.getErr()) {
			
			//�������� ���� ���� ������ ä���
			//�ٸ� ĳ���� ������ ä���.			
			CharacterInfo ch = character.getCharacter();
			CharacterPanel panel = ClientMain.getInstance().getGameWindow().character[ch.getCharacterPanelIndex()];
			panel.jlCharacterImg.setIcon(new ImageIcon("src/catchmindimg/robot" + ch.getCharacterType() + ".png"));
			panel.jlCharacterName.setText(ch.getCharacterName());
			panel.jlCharacterLevel.setText(String.valueOf(ch.getCharacterLevel()));
			panel.jlCharacterCorrect.setText(String.valueOf(ch.getCharacterExp()));	
			ClientMain.getInstance().getGameWindow().chat.jtaChatLog.append("["+ch.getCharacterName()+"] ���� �����Ͽ����ϴ�. \n" );
//			
//			if(GameRoom.getInstance().getCharacterCount() >=1)
//				ClientMain.getInstance().getGameWindow().jlStart.setIcon(
//						new ImageIcon(ClientMain.getInstance().getGameWindow().imgsrc[1]));
		}
		else {
			System.out.println("Ÿ���� ĳ���� ĳ���� ������ ����");
		}
	}
	
	// �� ĳ���Ͱ� ���ӿ��� ������, ���� ����
	@Override
	public void recvCharacterLeave(Protocol pack) {
		ProtocolCharacterLeave charLeave = (ProtocolCharacterLeave)pack;
	}
	
	// �ٸ� ������ ĳ���Ͱ� ������
	@Override
	public void recvOtherCharacterLeave(Protocol pack) {
		ProtocolOtherCharacterLeave charLeave = (ProtocolOtherCharacterLeave)pack;	
			
		if (Protocol.ERR_OK == charLeave.getErr()) {
			//�������� ���� ���� ĳ���� �����
			CharacterInfo ch = charLeave.getCharacter();
											
			CharacterPanel panel = ClientMain.getInstance().getGameWindow().character[ch.getCharacterPanelIndex()];
			panel.jlCharacterImg.setIcon(null);
			panel.jlCharacterName.setText("");
			panel.jlCharacterLevel.setText("");
			panel.jlCharacterCorrect.setText("");		
			ClientMain.getInstance().getGameWindow().chat.jtaChatLog.append("["+ch.getCharacterName()+"] ���� �����Ͽ����ϴ�. \n" );
		}
		else {
			System.out.println("���� Ÿ���� ĳ���� ĳ���� ������ ����");
		}
	}
	
	// ä���ϱ� ������ ����, ���� ����
	@Override
	public void recvCharacterChat(Protocol pack) {
		ProcotolCharacterChat chat = (ProcotolCharacterChat)pack;
		
		
	}
	
	// ä���ϱ� �ٸ� ĳ���Ͱ� ����
	@Override
	public void recvOtherCharacterChat(Protocol pack) {
		ProcotolOtherCharacterChat chat = (ProcotolOtherCharacterChat)pack;
		
		if (Protocol.ERR_OK == chat.getErr()) {
			JTextArea area = ClientMain.getInstance().getGameWindow().chat.jtaChatLog;
			area.append("[" + chat.getName() + "] " +chat.getChat() + "\n");
			JScrollBar jsb = ClientMain.getInstance().getGameWindow().chat.jspChatLog.getVerticalScrollBar();
			//jsb.getMaximum(); //�ִ�ġ�� ������ �޾ƿ���
			jsb.setValue(jsb.getMaximum()); //jsb���� �ִ�ġ�� �����Ѵ�.
		}
	}	
	
	// �ɹ� �����
	@Override
	public void recvMemberCreate(Protocol pack) {
		ProtocolMemberCreate create = (ProtocolMemberCreate)pack;
		
		if (Protocol.ERR_OK == create.getErr()) {
			System.out.println("ĳ���� ���� ����");	
			
			JOptionPane.showMessageDialog(null, "�����մϴ�. ȸ���� ���ԵǼ̽��ϴ�.");
			
			//�α���â ���̱�
			ClientMain.getInstance().getLoginWindow().setVisible(true);
			//����â ������
			ClientMain.getInstance().getJoinWindow().setVisible(false);
		}
		else {
			System.out.println("ĳ���� ���� ����");
			
			JOptionPane.showMessageDialog(null, "ȸ�� ���Կ� �����Ͽ����ϴ�. �ٽ� �Է��� �ּ���.");			
		}
	}


}

	