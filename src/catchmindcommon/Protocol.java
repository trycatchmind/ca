package catchmindcommon;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

// �����Ұ�
// object���� ��Ʈ��ũ�� ������ �ְ� ����
public abstract class Protocol implements Serializable {
	
	public static final int ERR_OK = 0;
	public static final int ERR_MEMBER_LOGIN = 10010;
	public static final int ERR_MEMBER_CREATE = 10020;
	public static final int ERR_CHARACTER_ENTER = 10030;
	public static final int ERR_CHARACTER_LEAVE = 10040;
	public static final int ERR_CHARACTER_LIST = 10050;
	public static final int ERR_CHARACTER_CHAT = 10060;					
	public static final int ERR_CHARACTER_CANVAS = 10070;
	
	// �ɹ� ������ ����
	public static final int PROTOCOL_MEMBER_CREATE = 10;
	// �ɹ��� �α����� ��
	public static final int PROTOCOL_MEMBER_LOGIN = 20;
	// ĳ���� ���� ����, ���� ���� ������ ��
	public static final int PROTOCOL_CHARACTER_ENTER = 30;
	// ĳ���� ���Խ� ����, �ٸ� ĳ���Ϳ� ���� ������ ��
	public static final int PROTOCOL_OTHERCHARACTER_ENTER = 31;
	//ĳ���Ͱ� ������ ����, ���� ���� ������ ��
	public static final int PROTOCOL_CHARACTER_LEAVE = 40;
	// ĳ���� ������ ����, �ٸ� ĳ���Ϳ� ���� ������ ��
	public static final int PROTOCOL_OTHERCHARACTER_LEAVE = 41;
	// ĳ���� ���Խ� ����, �ٸ� ĳ���Ϳ� ���� ��� ������ �ּ���
	public static final int PROTOCOL_CHARACTER_LIST = 50;
	// ĳ���Ͱ� ä�ý� ����, ���� ���� ������ ��
	public static final int PROTOCOL_CHARACTER_CHAT = 60;
	// ĳ���Ͱ� ä�ý� ����, �ٸ� ĳ���Ϳ� ���� ������ ��
	public static final int PROTOCOL_OTHERCHARACTER_CHAT = 61;
	// ĳ���Ͱ� �׸��� ������ ����, �� �׸��� ���� ������ ��
	public static final int PROTOCOL_CHARACTER_CANVAS = 70;
	// ĳ���Ͱ� �׸��� ������ ����, �ٸ� ĳ���� �׸��� ���� ������ ��
	public static final int PROTOCOL_OTHERCHARACTER_CANVAS = 71;
	//���ӽ��۵� �� ������ ������
	public static final int PROTOCOL_GAMESTART = 90;
	//������ ���� �ÿ� ������ ������
	public static final int PROTOCOL_GAMEEND=91;
	//���� �Ѽ�Ʈ �� �ϳ��� ���尡 ������ �� ������ ������
	public static final int PROTOCOL_GAMEROUNDSTART = 100;
	//���� �Ѽ�Ʈ �� �ϳ��� ���尡 ������ �� ������ ������
	public static final int PROTOCOL_GAMEROUNDEND=101;
	//Ÿ�̸� ����
	public static final int PROTOCOL_TIMERSTART = 110;
	//Ÿ�̸� ��
	public static final int PROTOCOL_TIMEREND= 111;
	//������ ������ �ο��� �ش�
	public static final int PROTOCOL_CAHRACTERORDER= 120;
	
	
	protected int protocol;		//�������� ��ȣ
	private int err;
	
	public Protocol(int protocol) {
		this.protocol = protocol;
		setErr(ERR_OK);
	}
	
	public int getProtocol() {
		return protocol;
	}

	public int getErr() {
		return err;
	}

	public void setErr(int err) {
		this.err = err;
	}	
}
