package catchmindserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import catchmindcommon.Protocol;
import catchmindcommon.Session;

// 서버 메인 네트워크 클래스(thread) 
public class ServerNetwork extends Thread {	
	
	private static ServerNetwork instance;
	
	// 연결상태인 세션리스트
	ArrayList<ServerSideSession> connectList = new ArrayList<ServerSideSession>();
	
	// 로그인완료된 세션리스트
	ArrayList<ServerSideSession> sessionList = new ArrayList<ServerSideSession>();
	
	private ServerNetwork() {
	
	}

	@Override
	public void run() {
		try(ServerSocket ss = new ServerSocket(5000)) {
			while (true) {
				
				// 새로 접속하는 유저
				ServerSideSession us = new ServerSideSession(ss.accept());
				
				// 메시지 추가해 주고
				ServerUI.getInstance().msgAppend(us.getIP() + "가 접속하였습니다.");
				
				// 연결만 성공한 형태로 리스트에 넣는다.
				connectAdd(us);
				
				// 세션 쓰레드 시작(데이터 받기용 스레드임)
				us.start(); 
			}			
		} catch (IOException e) {
			
		}
		
	}
	 
	// 자신을 제외한 로그인 객체에 대해 브로드캐스팅
	public synchronized void send(Session session, Protocol data) {
		Iterator<ServerSideSession> iter = this.sessionList.iterator();
		while(iter.hasNext()) {
			ServerSideSession m2 = iter.next();			
			if (null != m2 && false == session.equals(m2)) {
				m2.send(data);
			}
		}
	}
	
	// 로그인한 객체 전체에 대해 브로드캐스팅
	public synchronized void sendAll(Protocol data) {
		Iterator<ServerSideSession> iter = this.sessionList.iterator();
		while(iter.hasNext()) {
			ServerSideSession m2 = iter.next();			
			if (null != m2) {
				m2.send(data);
			}
		}
	}
	
	public synchronized void connectAdd(ServerSideSession s) {		
		this.connectList.add(s);
	}
	
	public synchronized void connectDel(ServerSideSession s) {		
		this.connectList.remove(s);
	}
	
	public synchronized void sessionAdd(ServerSideSession s) {
		this.sessionList.add(s);
	}
	
	public synchronized void sessionDel(ServerSideSession s) {		
		this.sessionList.remove(s);
	}	
	
	// 싱글톤
	public synchronized static ServerNetwork getInstance(){
		if(instance == null){
			instance = new ServerNetwork();
		}
		return instance;
	}
}
