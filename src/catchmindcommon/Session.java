package catchmindcommon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import catchmindserver.ServerNetwork;

// ��Ʈ��ũ ���� ��ü, ��ü�� ������� ������
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
	
	// host�� port ��ȣ�� ���� 
	public boolean connect(String host, int port) {
		try {
			// host�� port ��ȣ�� ����
			this.sock = new Socket(host, port);
			
			// �����͸� �ޱ����� ������ ����
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
				
				// �����ʹ� ��ü�� �ְ� �޴´�. protocol ��ü����				
				ObjectInputStream osr = new ObjectInputStream(this.sock.getInputStream());
				
				Protocol protocol = (Protocol)osr.readObject();
				
				// �������� ��ȣ�� Ȯ���ؼ� �߻� �Լ� ȣ��
				// ���� �� �Լ��� �������̵��� ��
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
	
	// �����͸� ������ ���
	public void send(Protocol protocol) {		
		try {
			// �߽Ű�ü
			ObjectOutputStream oos = new ObjectOutputStream(this.sock.getOutputStream());
			
			// ������ �߼�
			oos.writeObject(protocol);
			oos.flush();
		} catch (IOException e) {
			//e.printStackTrace();
		}							
	}
	
	// ��Ʈ��ũ ������ ȣ��
	public abstract void initialize();
	
	// ��Ʈ��ũ �Ϸ�� ȣ��
	public abstract void release();
	
	// �ɹ��� �α����� �� ����
	public abstract void recvMemberLogin(Protocol pack);
	
	// �ɹ� ������ ����
	public abstract void recvMemberCreate(Protocol pack);
	
	// ĳ���� ���� ����, ���� ���� ������ ��
	public abstract void recvCharacterEnter(Protocol pack);
	
	// ĳ���� ���Խ� ����, �ٸ� ĳ���Ϳ� ���� ������ ��
	public abstract void recvOtherCharacterEnter(Protocol pack);
	
	// ĳ���� ���Խ� ����, �ٸ� ĳ���Ϳ� ���� ��� ������ ��´�  
	public abstract void recvCharacterList(Protocol pack);
	
	//ĳ���Ͱ� ������ ����, ���� ���� ������ ��
	public abstract void recvCharacterLeave(Protocol pack);
	
	// ĳ���� ������ ����, �ٸ� ĳ���Ϳ� ���� ������ ��
	public abstract void recvOtherCharacterLeave(Protocol pack);
	
	// ĳ���Ͱ� ä�ý� ����, ���� ���� ������ ��
	public abstract void recvCharacterChat(Protocol pack);
	
	// ĳ���Ͱ� ä�ý� ����, �ٸ� ĳ���Ϳ� ���� ������ ��
	public abstract void recvOtherCharacterChat(Protocol pack);	
	
	// ĳ���Ͱ� �׸��� ������ ����, �� �׸��� ���� ������ ��
	public abstract void recvCharacterCanvas(Protocol pack);
		
	// ĳ���Ͱ� �׸��� ������ ����, �ٸ� ĳ���� �׸��� ���� ������ ��
	public abstract void recvOtherCharacterCanvas(Protocol pack);	
	
/*	//���� ���� �����ڿ��� ���þ ���δ�.
	public abstract void recvCharacterWord(Protocol pack);*/
}
