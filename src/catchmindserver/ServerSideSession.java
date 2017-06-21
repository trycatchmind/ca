package catchmindserver;

import java.net.Socket;

import catchmindcommon.CharacterInfo;
import catchmindcommon.ProcotolCharacterChat;
import catchmindcommon.ProcotolCharacterCanvas;
import catchmindcommon.ProcotolOtherCharacterChat;
import catchmindcommon.ProcotolOtherCharacterCanvas;
import catchmindcommon.Protocol;
import catchmindcommon.ProtocolCharacterEnter;
import catchmindcommon.ProtocolCharacterLeave;
import catchmindcommon.ProtocolCharacterList;
import catchmindcommon.ProtocolMemberCreate;
import catchmindcommon.ProtocolMemberLogin;
import catchmindcommon.ProtocolOtherCharacterEnter;
import catchmindcommon.ProtocolOtherCharacterLeave;
import catchmindcommon.Session;

// ���� ��ü
public class ServerSideSession extends Session {

	private CharacterInfo characterInfo;	//ĳ���� ����
	
	ServerSideSession(Socket sock) {
		this.sock = sock;
	}		
	
	@Override
	public void initialize() {}
	
	@Override
	public void release() {
		ServerNetwork.getInstance().connectDel(this);
		ServerNetwork.getInstance().sessionDel(this);
		
		recvCharacterLeave(new ProtocolCharacterLeave());
	}	 
	
	@Override
	public void recvMemberCreate(Protocol pack) {
		ProtocolMemberCreate create = (ProtocolMemberCreate)pack;				
	
		create.setErr(Protocol.ERR_OK);
		try {
			// �������� �˻�
			if (create.getId().isEmpty()) {  		
				create.setErr(Protocol.ERR_MEMBER_CREATE);
				throw new Exception("protocol check condition (id)");
			}
			if (create.getPwd().isEmpty()) {
				create.setErr(Protocol.ERR_MEMBER_CREATE);
				throw new Exception("protocol check condition (pwd)");
			}
			if (create.getCharacterName().isEmpty()) {
				create.setErr(Protocol.ERR_MEMBER_CREATE);
				throw new Exception("protocol check condition (name)");
			}
			if (0 > create.getCharacterType() || create.getCharacterType() > 5) {
				create.setErr(Protocol.ERR_MEMBER_CREATE);
				throw new Exception("protocol check condition (type)");
			}
			
			// db�� ������ �����ϱ�
			MemberVO mv = MemberVO.insertOne(create.getId(), create.getPwd());
			if (null == mv) {
				create.setErr(Protocol.ERR_MEMBER_CREATE);
				throw new Exception("db check error (member)");
			}

			create.setId(mv.getId());
			create.setPwd("");
			create.setMemberNo(mv.getMno());
			
			CharacterVO cv = CharacterVO.insertOne(mv.getMno(), create.getCharacterName(), create.getCharacterType());
			if (null == cv) {
				create.setErr(Protocol.ERR_MEMBER_CREATE);
				throw new Exception("db check error (character)");
			}
			
			create.setCharacterName(cv.getName());
			create.setCharacterType(cv.getChartype());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		send(create);
	}				
	
	@Override
	public void recvMemberLogin(Protocol pack) {
		// �������ݿ� ���� ĳ����
		ProtocolMemberLogin login = (ProtocolMemberLogin)pack;
				
		// db���� ������ ��������
		MemberVO mv = MemberVO.selectOne(login.getId());
		
		// Ŭ���̾�Ʈ�� ���� ������
		ProtocolMemberLogin res = new ProtocolMemberLogin();
		res.setId(login.getId());
		res.setPw("");
		res.setErr(Protocol.ERR_MEMBER_LOGIN);
		
		if (null != mv) {
			// ���̵� ��ҹ��� �������� ���ϰ� �н����尡 ������ �α��� ����
			if (mv.getId().equalsIgnoreCase(login.getId()) && mv.getPw().equals(login.getPw())) {
				characterInfo = new CharacterInfo();
				
				characterInfo.setMemberID(mv.getId());
				characterInfo.setMemberNo(mv.getMno());
				
				res.setMemberNo(mv.getMno());			
				res.setErr(Protocol.ERR_OK);
				
				// UI�� ����ְ�
				ServerUI.getInstance().msgAppend(login.getId() + "�� �α��ο� ���� �߽��ϴ�.");
				
				// ���Ḯ��Ʈ���� ����
				ServerNetwork.getInstance().connectDel(this);
				
				// �α��� ��������Ʈ�� �־��ݴϴ�.
				ServerNetwork.getInstance().sessionAdd(this);
			}
			else {						
				// UI�� ����ְ�
				ServerUI.getInstance().msgAppend(login.getId() + "�� �α��ο� ���� �߽��ϴ�.");
			}	
		}		
		
		// Ŭ���̾�Ʈ�� ������ ������
		send(res);		
	}
	
	@Override
	public void recvCharacterEnter(Protocol pack) {
		// �������ݿ� ���� ĳ����
		ProtocolCharacterEnter charEnter = (ProtocolCharacterEnter)pack;		
				
		// db���� ������ ��������		
		CharacterVO cv = CharacterVO.joinOne(charEnter.getCharacter().getMemberID());
		
		characterInfo.setCharacterNo(cv.getCno());
		characterInfo.setCharacterName(cv.getName());
		characterInfo.setCharacterType(cv.getChartype());
		characterInfo.setCharacterLevel(cv.getCharlevel());
		characterInfo.setCharacterExp(cv.getCharexp());
		
		// Ŭ���̾�Ʈ�� ���� ������
		ProtocolCharacterEnter res = new ProtocolCharacterEnter();				
		
		// �����Ͱ� ������
		if (0 != cv.getMno()) {							
						
			res.setErr(Protocol.ERR_OK);
			
			// ���ӹ濡 �����Ѵ�.
			int pos = GameRoom.getInstance().characterAdd(characterInfo);
			characterInfo.setCharacterPanelIndex(pos);
			
			// ��������� �����͸� �ִ´�.
			res.setCharacter(characterInfo);
			
			// UI�� ����ְ�
			ServerUI.getInstance().msgAppend(characterInfo.getCharacterName() + " ĳ���Ͱ� ���Խ��ϴ�.");
			ServerUI.getInstance().msgAppend("���� �����ڼ�: "+GameRoom.getInstance().getCharacterCount() );
			ServerUI.getInstance().msgAppend("���ӽð�: " + characterInfo.getEnDate());
			ServerUI.getInstance().msgAppend(GameRoom.getInstance().getCharList()[GameRoom.getInstance().delegateKing()].getCharacterName()
					+"���� �����Դϴ�.");
			
			
			// ���� ������ �α��� ������ ��� �������� ������.
			ProtocolOtherCharacterEnter broadcast = new ProtocolOtherCharacterEnter(res);			
			ServerNetwork.getInstance().send(this, broadcast);
		}
		else {
			
			// ĳ���� �ҷ����� ���н� ���� ������ ä����
			res.setErr(Protocol.ERR_CHARACTER_ENTER);
			
			// ��������� �����͸� �ִ´�.
			res.setCharacter(characterInfo);
			
			// UI�� ����ְ�
			ServerUI.getInstance().msgAppend(characterInfo.getMemberID() + " ĳ���Ͱ� ���ӹ� ���Կ� �����߽��ϴ�.");
			
			// ĳ���Ͱ� ���ӹ濡�� ���´�.
			int pos = GameRoom.getInstance().characterDel(characterInfo);
			characterInfo.setCharacterPanelIndex(pos);
		}
				
		send(res);
	}		
	
	@Override
	public void recvCharacterList(Protocol pack) {
		// �������ݿ� ���� ĳ����
		ProtocolCharacterList charList = (ProtocolCharacterList)pack;
						
		// Ŭ���̾�Ʈ�� ���� ������
		charList.setCharList(GameRoom.getInstance().getCharList());		
				
		send(charList);
	}		
	
	@Override
	public void recvCharacterLeave(Protocol pack) {
		
		if (null == characterInfo) {
			return;
		}

		// Ŭ���̾�Ʈ�� ���� ������
		ProtocolCharacterLeave res = new ProtocolCharacterLeave();
		
		res.setCharacter(characterInfo);
		
		// �����Ͱ� ������
		if (0 != characterInfo.getCharacterNo()) {										
			res.setErr(Protocol.ERR_OK);
			
			// UI�� ����ְ�
			ServerUI.getInstance().msgAppend(characterInfo.getCharacterName() + "�� �������ϴ�");
			
			// db�� ĳ���� ���¸� �����Ѵ�.
			characterSave();
			
			
			// ���� ������ �α��� ������ ��� �������� ������.
			ProtocolOtherCharacterLeave broadcast = new ProtocolOtherCharacterLeave(res);			
			ServerNetwork.getInstance().send(this, broadcast);
		}
		else {
			
			// ĳ���� �ҷ����� ���н� ���� ������ ä����
			res.setErr(Protocol.ERR_CHARACTER_LEAVE);
			
			// UI�� ����ְ�
			ServerUI.getInstance().msgAppend(characterInfo.getCharacterName() + " ���� ĳ���� �ҷ����⿡ �����߽��ϴ�.");			
		}
		
		// ĳ���Ͱ� ���ӹ濡�� ���´�.
		int pos = GameRoom.getInstance().characterDel(characterInfo);
		characterInfo.setCharacterPanelIndex(pos);
		ServerUI.getInstance().msgAppend(GameRoom.getInstance().getCharList()[GameRoom.getInstance().delegateKing()].getCharacterName()
				+"���� �����Դϴ�.");
		send(res);
	}
	
	public void characterSave() {
		if (0 != characterInfo.getCharacterNo()) {
			
			// ���� ���� ���, ���� ĳ���� ���¸� �����Ѵ�.
			CharacterVO cv = new CharacterVO();
			cv.setChartype(characterInfo.getCharacterType());
			cv.setCharlevel(characterInfo.getCharacterLevel());
			cv.setCharexp(characterInfo.getCharacterExp());
			cv.setCno(characterInfo.getCharacterNo());
			
			CharacterVO.updateOne(cv);
		}
	}
	
	@Override
	public void recvCharacterChat(Protocol pack) {
		ProcotolCharacterChat chat = (ProcotolCharacterChat)pack;		
		
		chat.setName(characterInfo.getCharacterName());
		chat.setMemberNo(characterInfo.getMemberNo());		
		chat.setErr(Protocol.ERR_OK);
			
		// UI�� ����ְ�
		ServerUI.getInstance().msgAppend("[" + characterInfo.getCharacterName() + "] " + chat.getChat());		
		
		// ���� ������ �α��� ������ ��� �������� ������.
		ProcotolOtherCharacterChat broadcast = new ProcotolOtherCharacterChat(chat);			
		ServerNetwork.getInstance().send(this, broadcast);
	}		
	
	@Override
	public void recvCharacterCanvas(Protocol pack) {
		ProcotolCharacterCanvas canvas = (ProcotolCharacterCanvas)pack;		
		
		System.out.println("�׸�����" + canvas.getImage());
		
		canvas.setName(characterInfo.getCharacterName());
		canvas.setMemberNo(characterInfo.getMemberNo());
		canvas.setErr(Protocol.ERR_OK);							
		
		// ���� ������ �α��� ������ ��� �������� ������.
		ProcotolOtherCharacterCanvas broadcast = new ProcotolOtherCharacterCanvas(canvas);			
		ServerNetwork.getInstance().send(this, broadcast);		
	}

	public void close() {		
		
	}
	
	@Override
	public void recvOtherCharacterEnter(Protocol pack) {
		
	}
	
	@Override
	public void recvOtherCharacterLeave(Protocol pack) {
		
	}
	
	@Override
	public void recvOtherCharacterChat(Protocol pack) {
		ProcotolOtherCharacterChat chat = (ProcotolOtherCharacterChat)pack;
	}
	
	@Override
	public void recvOtherCharacterCanvas(Protocol pack) {
		ProcotolOtherCharacterCanvas canvas = (ProcotolOtherCharacterCanvas)pack;
	}

/*	@Override
	public void recvCharacterWord(Protocol pack) {
		
		
	}*/
	
}
