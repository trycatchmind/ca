package catchmindclient;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameChat extends JPanel{
	
	JPanel jpChatInput;	//ä���Էµ��� �г�
	JLabel jlSend;	//ä�����۽�Ű�� ��ư
	JTextField jtfChatInput;	//ä���Է��� ���� textfield
	JTextArea jtaChatLog; //ä���� �������� textarea
	JScrollPane jspChatLog; //ä�÷αװ� �������� â�� ��ũ��ȭ
	int tempX=600;
	int tempY=tempX*4/10; // ä��â ����,������ ����(����)
	
	
	public GameChat(){
		//������ �ӽð� 600*400
		setSize(tempX+15,tempY);
		setLayout(null);
		
		//component instance
		Font f = new Font("�������",Font.BOLD,15);
		jpChatInput = new JPanel();
		jlSend = new JLabel(new ImageIcon("src/catchmindimg/jlsend.png"));
		jtfChatInput = new JTextField(42); //��Ʈ�� ��Ʈ������, â����� ���� �Ű������� �ٲ�����
		jtaChatLog = new JTextArea();
		jspChatLog = new JScrollPane(jtaChatLog,
									JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		
		//component�� Font ����
		jpChatInput.setFont(f);		//ä�� �Է�â
		jtfChatInput.setFont(f);	//ä�� �α�â
		
		//Inputpanel�� component ������ .��ġ����
		jtfChatInput.setBounds(0,0,tempX-100,70);
		jlSend.setBounds(tempX-100,0,110,70);
		
		//Input panel�� component ����
		jpChatInput.add(jtfChatInput);
		jpChatInput.add(jlSend);		
		
		//chat log�� component size, location ����
		jspChatLog.setBounds(0,0,tempX,tempY-70);
		jpChatInput.setBounds(0,tempY-70,tempX,35);
		
		
		//��üpanel �����ϰ�
		jpChatInput.setOpaque(false);
		setOpaque(false);
				
		
		//ä�÷α�â���� Ŀ���ȵ���
		jtaChatLog.setEditable(false);
		
		//panel�� ����
		add(jpChatInput);
		add(jspChatLog);
				
		//â ���̰�
		setVisible(true);
		
	}//constructor end
	
	
	
//	public static void main(String[] args) {
//		//â ���� Ȯ�ο� ���θ޼ҵ�
//		new GameChat();
//	}//mainmethodend

}
