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

// 세션 객체
public class ServerSideSession extends Session {

	private CharacterInfo characterInfo;	//캐릭터 정보
	
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
			// 가입조건 검사
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
			
			// db에 데이터 저장하기
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
		// 프로토콜에 맞춰 캐스팅
		ProtocolMemberLogin login = (ProtocolMemberLogin)pack;
				
		// db에서 데이터 가져오기
		MemberVO mv = MemberVO.selectOne(login.getId());
		
		// 클라이언트에 보낼 데이터
		ProtocolMemberLogin res = new ProtocolMemberLogin();
		res.setId(login.getId());
		res.setPw("");
		res.setErr(Protocol.ERR_MEMBER_LOGIN);
		
		if (null != mv) {
			// 아이디 대소문자 구별없이 비교하고 패스워드가 같으면 로그인 성공
			if (mv.getId().equalsIgnoreCase(login.getId()) && mv.getPw().equals(login.getPw())) {
				characterInfo = new CharacterInfo();
				
				characterInfo.setMemberID(mv.getId());
				characterInfo.setMemberNo(mv.getMno());
				
				res.setMemberNo(mv.getMno());			
				res.setErr(Protocol.ERR_OK);
				
				// UI에 찍어주고
				ServerUI.getInstance().msgAppend(login.getId() + "가 로그인에 성공 했습니다.");
				
				// 연결리스트에서 삭제
				ServerNetwork.getInstance().connectDel(this);
				
				// 로그인 유저리스트에 넣어줍니다.
				ServerNetwork.getInstance().sessionAdd(this);
			}
			else {						
				// UI에 찍어주고
				ServerUI.getInstance().msgAppend(login.getId() + "가 로그인에 실패 했습니다.");
			}	
		}		
		
		// 클라이언트에 데이터 보내기
		send(res);		
	}
	
	@Override
	public void recvCharacterEnter(Protocol pack) {
		// 프로토콜에 맞춰 캐스팅
		ProtocolCharacterEnter charEnter = (ProtocolCharacterEnter)pack;		
				
		// db에서 데이터 가져오기		
		CharacterVO cv = CharacterVO.joinOne(charEnter.getCharacter().getMemberID());
		
		characterInfo.setCharacterNo(cv.getCno());
		characterInfo.setCharacterName(cv.getName());
		characterInfo.setCharacterType(cv.getChartype());
		characterInfo.setCharacterLevel(cv.getCharlevel());
		characterInfo.setCharacterExp(cv.getCharexp());
		
		// 클라이언트에 보낼 데이터
		ProtocolCharacterEnter res = new ProtocolCharacterEnter();				
		
		// 데이터가 있으면
		if (0 != cv.getMno()) {							
						
			res.setErr(Protocol.ERR_OK);
			
			// 게임방에 진입한다.
			int pos = GameRoom.getInstance().characterAdd(characterInfo);
			characterInfo.setCharacterPanelIndex(pos);
			
			// 현재까지의 데이터를 넣는다.
			res.setCharacter(characterInfo);
			
			// UI에 찍어주고
			ServerUI.getInstance().msgAppend(characterInfo.getCharacterName() + " 캐릭터가 들어왔습니다.");
			ServerUI.getInstance().msgAppend("현재 참가자수: "+GameRoom.getInstance().getCharacterCount() );
			ServerUI.getInstance().msgAppend("접속시간: " + characterInfo.getEnDate());
			ServerUI.getInstance().msgAppend(GameRoom.getInstance().getCharList()[GameRoom.getInstance().delegateKing()].getCharacterName()
					+"님이 방장입니다.");
			
			
			// 나를 제외한 로그인 성공한 모든 유저에게 보낸다.
			ProtocolOtherCharacterEnter broadcast = new ProtocolOtherCharacterEnter(res);			
			ServerNetwork.getInstance().send(this, broadcast);
		}
		else {
			
			// 캐릭터 불러오기 실패시 실패 정보를 채운후
			res.setErr(Protocol.ERR_CHARACTER_ENTER);
			
			// 현재까지의 데이터를 넣는다.
			res.setCharacter(characterInfo);
			
			// UI에 찍어주고
			ServerUI.getInstance().msgAppend(characterInfo.getMemberID() + " 캐릭터가 게임방 진입에 실패했습니다.");
			
			// 캐릭터가 게임방에서 나온다.
			int pos = GameRoom.getInstance().characterDel(characterInfo);
			characterInfo.setCharacterPanelIndex(pos);
		}
				
		send(res);
	}		
	
	@Override
	public void recvCharacterList(Protocol pack) {
		// 프로토콜에 맞춰 캐스팅
		ProtocolCharacterList charList = (ProtocolCharacterList)pack;
						
		// 클라이언트에 보낼 데이터
		charList.setCharList(GameRoom.getInstance().getCharList());		
				
		send(charList);
	}		
	
	@Override
	public void recvCharacterLeave(Protocol pack) {
		
		if (null == characterInfo) {
			return;
		}

		// 클라이언트에 보낼 데이터
		ProtocolCharacterLeave res = new ProtocolCharacterLeave();
		
		res.setCharacter(characterInfo);
		
		// 데이터가 있으면
		if (0 != characterInfo.getCharacterNo()) {										
			res.setErr(Protocol.ERR_OK);
			
			// UI에 찍어주고
			ServerUI.getInstance().msgAppend(characterInfo.getCharacterName() + "가 나갔습니다");
			
			// db에 캐릭터 상태를 저장한다.
			characterSave();
			
			
			// 나를 제외한 로그인 성공한 모든 유저에게 보낸다.
			ProtocolOtherCharacterLeave broadcast = new ProtocolOtherCharacterLeave(res);			
			ServerNetwork.getInstance().send(this, broadcast);
		}
		else {
			
			// 캐릭터 불러오기 실패시 실패 정보를 채운후
			res.setErr(Protocol.ERR_CHARACTER_LEAVE);
			
			// UI에 찍어주고
			ServerUI.getInstance().msgAppend(characterInfo.getCharacterName() + " 나간 캐릭터 불러오기에 실패했습니다.");			
		}
		
		// 캐릭터가 게임방에서 나온다.
		int pos = GameRoom.getInstance().characterDel(characterInfo);
		characterInfo.setCharacterPanelIndex(pos);
		ServerUI.getInstance().msgAppend(GameRoom.getInstance().getCharList()[GameRoom.getInstance().delegateKing()].getCharacterName()
				+"님이 방장입니다.");
		send(res);
	}
	
	public void characterSave() {
		if (0 != characterInfo.getCharacterNo()) {
			
			// 값이 있을 경우, 현재 캐릭터 상태를 저장한다.
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
			
		// UI에 찍어주고
		ServerUI.getInstance().msgAppend("[" + characterInfo.getCharacterName() + "] " + chat.getChat());		
		
		// 나를 제외한 로그인 성공한 모든 유저에게 보낸다.
		ProcotolOtherCharacterChat broadcast = new ProcotolOtherCharacterChat(chat);			
		ServerNetwork.getInstance().send(this, broadcast);
	}		
	
	@Override
	public void recvCharacterCanvas(Protocol pack) {
		ProcotolCharacterCanvas canvas = (ProcotolCharacterCanvas)pack;		
		
		System.out.println("그림받음" + canvas.getImage());
		
		canvas.setName(characterInfo.getCharacterName());
		canvas.setMemberNo(characterInfo.getMemberNo());
		canvas.setErr(Protocol.ERR_OK);							
		
		// 나를 제외한 로그인 성공한 모든 유저에게 보낸다.
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
