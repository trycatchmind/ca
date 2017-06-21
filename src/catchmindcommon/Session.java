package catchmindcommon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import catchmindserver.ServerNetwork;

// 네트워크 세션 객체, 객체당 쓰레드로 동작함
public abstract class Session extends Thread {
	protected Socket sock;
	
	public Session() {

	}	
	
	public InetAddress getAddress() {
		return this.sock.getInetAddress();
	}
	
	public String getIP() {
		return getAddress().getHostAddress();
	}
	
	// host에 port 번호로 접속 
	public boolean connect(String host, int port) {
		try {
			// host에 port 번호로 접속
			this.sock = new Socket(host, port);
			
			// 데이터를 받기위한 쓰레드 시작
			start();
			
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}	 
	
	@Override
	public void run() {				
				
		try {
			
			initialize();
			
			while (true) {								
				
				// 데이터는 객체로 주고 받는다. protocol 객체참조				
				ObjectInputStream osr = new ObjectInputStream(this.sock.getInputStream());
				
				Protocol protocol = (Protocol)osr.readObject();
				
				// 프로토콜 번호를 확인해서 추상 함수 호출
				// 사용시 각 함수를 오버라이딩할 것
				switch(protocol.getProtocol()) {
				case Protocol.PROTOCOL_CHARACTER_CHAT:
					recvCharacterChat(protocol);
					break;
				case Protocol.PROTOCOL_OTHERCHARACTER_CHAT:
					recvOtherCharacterChat(protocol);
					break;
				case Protocol.PROTOCOL_CHARACTER_CANVAS:
					recvCharacterCanvas(protocol);
					break;
				case Protocol.PROTOCOL_OTHERCHARACTER_CANVAS:
					recvOtherCharacterCanvas(protocol);
					break;
				case Protocol.PROTOCOL_MEMBER_LOGIN:
					recvMemberLogin(protocol);					
					break;				
				case Protocol.PROTOCOL_MEMBER_CREATE:
					recvMemberCreate(protocol);
					break;
				case Protocol.PROTOCOL_CHARACTER_ENTER:
					recvCharacterEnter(protocol);					
					break;
				case Protocol.PROTOCOL_OTHERCHARACTER_ENTER:
					recvOtherCharacterEnter(protocol);					
					break;
				case Protocol.PROTOCOL_CHARACTER_LIST:
					recvCharacterList(protocol);
					break;
				case Protocol.PROTOCOL_CHARACTER_LEAVE:
					recvCharacterLeave(protocol);					
					break;
				case Protocol.PROTOCOL_OTHERCHARACTER_LEAVE:
					recvOtherCharacterLeave(protocol);					
					break;
				
				default:
					System.out.println("unknown protocol!");
					break;
				}				
			}								
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {		
			//e.printStackTrace();
		}
		
		release();
	}
	
	// 데이터를 보낼시 사용
	public void send(Protocol protocol) {		
		try {
			// 발신객체
			ObjectOutputStream oos = new ObjectOutputStream(this.sock.getOutputStream());
			
			// 데이터 발송
			oos.writeObject(protocol);
			oos.flush();
		} catch (IOException e) {
			//e.printStackTrace();
		}							
	}
	
	// 네트워크 시작전 호출
	public abstract void initialize();
	
	// 네트워크 완료시 호출
	public abstract void release();
	
	// 맴버가 로그인할 시 구현
	public abstract void recvMemberLogin(Protocol pack);
	
	// 맴버 생성시 구현
	public abstract void recvMemberCreate(Protocol pack);
	
	// 캐릭터 진입 구현, 나에 대한 정보만 옴
	public abstract void recvCharacterEnter(Protocol pack);
	
	// 캐릭터 진입시 구현, 다른 캐릭터에 대한 정보만 옴
	public abstract void recvOtherCharacterEnter(Protocol pack);
	
	// 캐릭터 진입시 구현, 다른 캐릭터에 대한 모든 정보를 얻는다  
	public abstract void recvCharacterList(Protocol pack);
	
	//캐릭터가 나갈시 구현, 나에 대한 정보만 옴
	public abstract void recvCharacterLeave(Protocol pack);
	
	// 캐릭터 나갈시 구현, 다른 캐릭터에 대한 정보만 옴
	public abstract void recvOtherCharacterLeave(Protocol pack);
	
	// 캐릭터가 채팅시 구현, 나에 대한 정보만 옴
	public abstract void recvCharacterChat(Protocol pack);
	
	// 캐릭터가 채팅시 구현, 다른 캐릭터에 대한 정보만 옴
	public abstract void recvOtherCharacterChat(Protocol pack);	
	
	// 캐릭터가 그림을 보낼시 구현, 내 그림에 대한 정보만 옴
	public abstract void recvCharacterCanvas(Protocol pack);
		
	// 캐릭터가 그림을 보낼시 구현, 다른 캐릭터 그림에 대한 정보만 옴
	public abstract void recvOtherCharacterCanvas(Protocol pack);	
	
/*	//게임 문제 출제자에게 제시어가 보인다.
	public abstract void recvCharacterWord(Protocol pack);*/
}
