package catchmindclient;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameChat extends JPanel{
	
	JPanel jpChatInput;	//채팅입력들어가는 패널
	JLabel jlSend;	//채팅전송시키는 버튼
	JTextField jtfChatInput;	//채팅입력이 들어가는 textfield
	JTextArea jtaChatLog; //채팅이 보여지는 textarea
	JScrollPane jspChatLog; //채팅로그가 보여지는 창의 스크롤화
	int tempX=600;
	int tempY=tempX*4/10; // 채팅창 가로,세로의 비율(임의)
	
	
	public GameChat(){
		//사이즈 임시값 600*400
		setSize(tempX+15,tempY);
		setLayout(null);
		
		//component instance
		Font f = new Font("맑은고딕",Font.BOLD,15);
		jpChatInput = new JPanel();
		jlSend = new JLabel(new ImageIcon("src/catchmindimg/jlsend.png"));
		jtfChatInput = new JTextField(42); //폰트와 폰트사이즈, 창사이즈에 따라서 매개변수값 바뀌어야함
		jtaChatLog = new JTextArea();
		jspChatLog = new JScrollPane(jtaChatLog,
									JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		
		//component에 Font 설정
		jpChatInput.setFont(f);		//채팅 입력창
		jtfChatInput.setFont(f);	//채팅 로그창
		
		//Inputpanel의 component 사이즈 .위치설정
		jtfChatInput.setBounds(0,0,tempX-100,70);
		jlSend.setBounds(tempX-100,0,110,70);
		
		//Input panel에 component 부착
		jpChatInput.add(jtfChatInput);
		jpChatInput.add(jlSend);		
		
		//chat log의 component size, location 지정
		jspChatLog.setBounds(0,0,tempX,tempY-70);
		jpChatInput.setBounds(0,tempY-70,tempX,35);
		
		
		//전체panel 투명하게
		jpChatInput.setOpaque(false);
		setOpaque(false);
				
		
		//채팅로그창에는 커서안들어가게
		jtaChatLog.setEditable(false);
		
		//panel을 부착
		add(jpChatInput);
		add(jspChatLog);
				
		//창 보이게
		setVisible(true);
		
	}//constructor end
	
	
	
//	public static void main(String[] args) {
//		//창 구현 확인용 메인메소드
//		new GameChat();
//	}//mainmethodend

}
