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
	
	// 로그인 정보 보내기(아이디, 패스워드)
	public void sendMemberCreate(String id, String pwd, int charType, String name) {
		ProtocolMemberCreate create = new ProtocolMemberCreate();			
		create.setId(id);
		create.setPwd(pwd);
		create.setCharacterType(charType);
		create.setCharacterName(name);
		
		send(create);		
	}
	
	// 로그인 정보 보내기(아이디, 패스워드)
	public void sendMemberLogin(String id, String pwd) {
		ProtocolMemberLogin login = new ProtocolMemberLogin();			
		login.setId(id);
		login.setPw(pwd);
		
		send(login);		
	}
	
	// 내 캐릭터가 게임에 들어간다고 알리기
	public void sendCharacterEnter() {
		ProtocolCharacterEnter character = new ProtocolCharacterEnter();
		character.setCharacter(ClientMain.getInstance().getCharacterInfo());		
        
        send(character);
	}
	
	// 내 캐릭터가 게임에 들어간다고 알리기
	public void sendCharacterList() {
		ProtocolCharacterList charList = new ProtocolCharacterList();
        
        send(charList);
	}
	
	// 채팅하기
	public void sendCharacterChat(String str) {
		ProcotolCharacterChat chat = new ProcotolCharacterChat();
		chat.setChat(str);

		send(chat);
	}
	
	// 그림 내보내기
	public void sendCharacterCanvas(BufferedImage image) {
		ProcotolCharacterCanvas canvas = new ProcotolCharacterCanvas();
		canvas.setImage(image);

		send(canvas);
	}
	
	@Override
	public void initialize() {}
	
	@Override
	public void release() {}
	
	// 로그인 성공
	@Override
	public void recvMemberLogin(Protocol pack) {
		ProtocolMemberLogin login = (ProtocolMemberLogin)pack;	
		
		if (Protocol.ERR_OK == login.getErr()) {
			System.out.println("로그인 성공");			
			
			//로그인창은 가리기
			ClientMain.getInstance().getLoginWindow().setVisible(false);
			//메인 JFrame을 보이기
			ClientMain.getInstance().setGameWindow(new GameClient());
			ClientMain.getInstance().getGameWindow().setVisible(true);

			//서버에서 받은 각종 데이터 채우기
			CharacterInfo character = new CharacterInfo(); 
			character.setMemberNo(login.getMemberNo());
			character.setMemberID(login.getId());
			
			ClientMain.getInstance().setCharacterInfo(character); 			
			
			//로그인 성공후 내 캐릭터가 게임에 접속한다고 여러 유저에게 알리기         
			sendCharacterEnter();
		}
		else {
			System.out.println("로그인 실패");
			
			JOptionPane.showMessageDialog(null, "로그인 실패하였습니다. 다시 입력해 주세요.");
		}
	}
	
	// 내 캐릭터가 게임에 진입
	@Override
	public void recvCharacterEnter(Protocol pack) {
		ProtocolCharacterEnter character = (ProtocolCharacterEnter)pack;	
			
		if (Protocol.ERR_OK == character.getErr()) {									
			
			//서버에서 받은 각종 데이터 채우기	
			ClientMain.getInstance().setCharacterInfo(character.getCharacter());						
			
			//나머지 캐릭터 달라고 요청
			sendCharacterList();			
		}
		else {
			System.out.println("내 캐릭터 데이터 없음");
		}
	}
	
	
	@Override
	public void recvCharacterList(Protocol pack) {		
		ProtocolCharacterList charList = (ProtocolCharacterList)pack;	
		
		if (Protocol.ERR_OK == charList.getErr()) {									
			
			//서버에서 받은 각종 데이터 채우기
			CharacterInfo[] chInfo = charList.getCharList();
			
			//다른 캐릭터 정보도 채운다.
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
			System.out.println("캐릭터 정보 없음");
		}
	}

	//내가 그린 캔버스가 나에게 올 경우, 할일 없음
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

	// 다른 유저의 캐릭터가 게임에 진입
	@Override
	public void recvOtherCharacterEnter(Protocol pack) {
		ProtocolOtherCharacterEnter character = (ProtocolOtherCharacterEnter)pack;	
			
		if (Protocol.ERR_OK == character.getErr()) {
			
			//서버에서 받은 각종 데이터 채우기
			//다른 캐릭터 정보를 채운다.			
			CharacterInfo ch = character.getCharacter();
			CharacterPanel panel = ClientMain.getInstance().getGameWindow().character[ch.getCharacterPanelIndex()];
			panel.jlCharacterImg.setIcon(new ImageIcon("src/catchmindimg/robot" + ch.getCharacterType() + ".png"));
			panel.jlCharacterName.setText(ch.getCharacterName());
			panel.jlCharacterLevel.setText(String.valueOf(ch.getCharacterLevel()));
			panel.jlCharacterCorrect.setText(String.valueOf(ch.getCharacterExp()));	
			ClientMain.getInstance().getGameWindow().chat.jtaChatLog.append("["+ch.getCharacterName()+"] 님이 입장하였습니다. \n" );
//			
//			if(GameRoom.getInstance().getCharacterCount() >=1)
//				ClientMain.getInstance().getGameWindow().jlStart.setIcon(
//						new ImageIcon(ClientMain.getInstance().getGameWindow().imgsrc[1]));
		}
		else {
			System.out.println("타유저 캐릭터 캐릭터 데이터 없음");
		}
	}
	
	// 내 캐릭터가 게임에서 나갈때, 할일 없음
	@Override
	public void recvCharacterLeave(Protocol pack) {
		ProtocolCharacterLeave charLeave = (ProtocolCharacterLeave)pack;
	}
	
	// 다른 유저의 캐릭터가 나갈시
	@Override
	public void recvOtherCharacterLeave(Protocol pack) {
		ProtocolOtherCharacterLeave charLeave = (ProtocolOtherCharacterLeave)pack;	
			
		if (Protocol.ERR_OK == charLeave.getErr()) {
			//서버에서 받은 나간 캐릭터 지우기
			CharacterInfo ch = charLeave.getCharacter();
											
			CharacterPanel panel = ClientMain.getInstance().getGameWindow().character[ch.getCharacterPanelIndex()];
			panel.jlCharacterImg.setIcon(null);
			panel.jlCharacterName.setText("");
			panel.jlCharacterLevel.setText("");
			panel.jlCharacterCorrect.setText("");		
			ClientMain.getInstance().getGameWindow().chat.jtaChatLog.append("["+ch.getCharacterName()+"] 님이 퇴장하였습니다. \n" );
		}
		else {
			System.out.println("나간 타유저 캐릭터 캐릭터 데이터 없음");
		}
	}
	
	// 채팅하기 나한테 보냄, 할일 없음
	@Override
	public void recvCharacterChat(Protocol pack) {
		ProcotolCharacterChat chat = (ProcotolCharacterChat)pack;
		
		
	}
	
	// 채팅하기 다른 캐릭터가 보냄
	@Override
	public void recvOtherCharacterChat(Protocol pack) {
		ProcotolOtherCharacterChat chat = (ProcotolOtherCharacterChat)pack;
		
		if (Protocol.ERR_OK == chat.getErr()) {
			JTextArea area = ClientMain.getInstance().getGameWindow().chat.jtaChatLog;
			area.append("[" + chat.getName() + "] " +chat.getChat() + "\n");
			JScrollBar jsb = ClientMain.getInstance().getGameWindow().chat.jspChatLog.getVerticalScrollBar();
			//jsb.getMaximum(); //최대치가 얼마인지 받아오기
			jsb.setValue(jsb.getMaximum()); //jsb바의 최대치로 세팅한다.
		}
	}	
	
	// 맴버 만들기
	@Override
	public void recvMemberCreate(Protocol pack) {
		ProtocolMemberCreate create = (ProtocolMemberCreate)pack;
		
		if (Protocol.ERR_OK == create.getErr()) {
			System.out.println("캐릭터 생성 성공");	
			
			JOptionPane.showMessageDialog(null, "축하합니다. 회원에 가입되셨습니다.");
			
			//로그인창 보이기
			ClientMain.getInstance().getLoginWindow().setVisible(true);
			//가입창 가리기
			ClientMain.getInstance().getJoinWindow().setVisible(false);
		}
		else {
			System.out.println("캐릭터 생성 실패");
			
			JOptionPane.showMessageDialog(null, "회원 가입에 실패하였습니다. 다시 입력해 주세요.");			
		}
	}


}

	