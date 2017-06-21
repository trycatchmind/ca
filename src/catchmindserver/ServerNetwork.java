package catchmindserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import catchmindcommon.Protocol;
import catchmindcommon.Session;

// ���� ���� ��Ʈ��ũ Ŭ����(thread) 
public class ServerNetwork extends Thread {	
	
	private static ServerNetwork instance;
	
	// ��������� ���Ǹ���Ʈ
	ArrayList<ServerSideSession> connectList = new ArrayList<ServerSideSession>();
	
	// �α��οϷ�� ���Ǹ���Ʈ
	ArrayList<ServerSideSession> sessionList = new ArrayList<ServerSideSession>();
	
	private ServerNetwork() {
	
	}

	@Override
	public void run() {
		try(ServerSocket ss = new ServerSocket(5000)) {
			while (true) {
				
				// ���� �����ϴ� ����
				ServerSideSession us = new ServerSideSession(ss.accept());
				
				// �޽��� �߰��� �ְ�
				ServerUI.getInstance().msgAppend(us.getIP() + "�� �����Ͽ����ϴ�.");
				
				// ���Ḹ ������ ���·� ����Ʈ�� �ִ´�.
				connectAdd(us);
				
				// ���� ������ ����(������ �ޱ�� ��������)
				us.start(); 
			}			
		} catch (IOException e) {
			
		}
		
	}
	 
	// �ڽ��� ������ �α��� ��ü�� ���� ��ε�ĳ����
	public synchronized void send(Session session, Protocol data) {
		Iterator<ServerSideSession> iter = this.sessionList.iterator();
		while(iter.hasNext()) {
			ServerSideSession m2 = iter.next();			
			if (null != m2 && false == session.equals(m2)) {
				m2.send(data);
			}
		}
	}
	
	// �α����� ��ü ��ü�� ���� ��ε�ĳ����
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
	
	// �̱���
	public synchronized static ServerNetwork getInstance(){
		if(instance == null){
			instance = new ServerNetwork();
		}
		return instance;
	}
}
