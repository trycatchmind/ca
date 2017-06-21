package catchmindclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import catchmindserver.GameRoom;

public class GameClient extends JFrame {

	static int CHARACTER_IN_ROOM = 4;			//한방에 들어가는 캐릭터4명
	CharacterPanel[] character
		= new CharacterPanel[CHARACTER_IN_ROOM];//캐릭터수만큼의 캐릭터 패널배열 생성
	GameChat chat;								//채팅패널
	JLabel jlStart,jlExit;						//시작, 나가기버튼
	Drawing jpCanvas;//캔버스
	OtherDrawing jpOtherCanvas;
	JLabel jlTimer,jlWord; 						//타이머를 붙일 패널
	GameTimer timer;							//타이머 클래스
	ImageIcon iBackground;
	JPanel jpBackground;
	JScrollPane jspBackground;
	static String[] imgsrc={"src/catchmindimg/mainJlNoStart.png",
			"src/catchmindimg/mainJlStart.png"};
	
	
	
	//color instance
	Color red = Color.red;
	Color blue = Color.blue;
	Color black = Color.black;
	Color white = Color.white;
	
	GameClient(){
		setBounds(500,300,1440,930);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// x누르면 꺼지게
		
		//게임 참가유저 패널
		character[0] = new CharacterPanel();
		character[1] = new CharacterPanel();
		character[2] = new CharacterPanel();
		character[3] = new CharacterPanel();
		

		//Canvas panel
		jpCanvas = new Drawing();
		jpOtherCanvas = new OtherDrawing();
		jpOtherCanvas.setOpaque(false);
		
		//채팅창 panel
		chat = new GameChat();
		
		//ReadyButton
		jlStart = new JLabel(new ImageIcon(imgsrc[0]));
		jlExit = new JLabel(new ImageIcon("src/catchmindimg/mainjlExit.PNG"));
		
		//단어 레이블
		jlWord = new JLabel();
		
		//배경
		iBackground = new ImageIcon("src/catchmindimg/BG1.PNG");
		jpBackground = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
                g.drawImage(iBackground.getImage(),0,0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
            }
		};
		
		jpBackground.setLayout(null);
		
		//canvas 들어가는 panel표시
		jpCanvas.setBounds(400,170,600,440);
		jpOtherCanvas.setBounds(400,170,600,440);
		
		//채팅 패널 위치 
		chat.setBounds(400,620,600,400);
 
		//참가자 패널 위치
		character[0].setBounds(30, 160, 320,190);
		character[1].setBounds(1050,160, 320,190);
		character[2].setBounds(30, 380, 320,190);
		character[3].setBounds(1050,380, 320,190);
		
		//레디버튼위치
		jlStart.setBounds(1050,620,300,100);
		jlExit.setBounds(1165,30,200,58);
		
		jlWord.setBounds(400, 120,300,50); //단어 레이블 위치 바꾸기
		jlWord.setFont(new Font("맑은 고딕",Font.BOLD,18));
		
		//각 패널 추가
		jpBackground.add(character[0]);
		jpBackground.add(character[1]);
		jpBackground.add(character[2]);
		jpBackground.add(character[3]);
		jpBackground.add(chat);
		jpBackground.add(jlStart);
		jpBackground.add(jpCanvas);
		jpBackground.add(jlExit);
		jpBackground.add(jlWord);
		jpBackground.add(jpOtherCanvas);
		jpOtherCanvas.setVisible(false);
//		jpBackground.add(jlTimer); //타이머를 볼 수 있는 레이블
		
		//JScrollpanel
		jspBackground = new JScrollPane(jpBackground);
		setContentPane(jspBackground);

		//리스너부착
		Handler hd = new Handler();
		chat.jlSend.addMouseListener(hd);
		chat.jtfChatInput.addKeyListener(hd);
		jpCanvas.can.addMouseMotionListener(hd);
		jpCanvas.can.addMouseListener(hd);
		jpCanvas.jlRed.addMouseListener(hd);
		jpCanvas.jlBlue.addMouseListener(hd);
		jpCanvas.jlBlack.addMouseListener(hd);
		jpCanvas.jlEraser.addMouseListener(hd);
		jpCanvas.jlClear.addMouseListener(hd);
		jlExit.addMouseListener(hd);
		
		//JFrame 기본기능설정
		setResizable(false);						//사이즈조절 안되게
		setVisible(true);							//보이게
	}//constructor end

	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(GameRoom.getInstance().getCharacterCount()>=3)
			jlStart.setIcon(new ImageIcon(imgsrc[1]));
	}
	
	public MyCanvas getMyCanvas() {
		return jpCanvas.can;
	}


	public static void main(String[] args) {
		GameClient cl = new GameClient();

		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5초지남");
		cl.jlStart.setIcon(new ImageIcon(imgsrc[1]));
		cl.jpCanvas.setVisible(false);
		cl.jpOtherCanvas.setVisible(true);
		cl.chat.jtfChatInput.setEditable(false);
		Word wd = new Word();
		String output = wd.strWord[(int)(Math.random()*wd.strWord.length)];
		//0<Math.random()<1*strWord.lengthw 
//else if (게임이 시작하지 않으면) output = null;
		cl.jlWord.setText(output);
		
	}
	
	class Handler implements KeyListener,MouseListener,MouseMotionListener{

		
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			jpCanvas.can.x = e.getX();  
			jpCanvas.can.y = e.getY();
		    System.out.println("x: " + jpCanvas.can.x 
		    				+ "\ty: " + jpCanvas.can.y);
		    jpCanvas.can.repaint();
		}//mouseDragged end



		@Override
		public void mousePressed(MouseEvent e) {
	        // TODO Auto-generated method stub
			Object obj = e.getSource();
	         
	        if(obj == jpCanvas.jlRed){
	            System.out.println("빨간선");
	            jpCanvas.can.nowColor = red;
	            jpCanvas.can.brushSize=4;
	        }else if(obj == jpCanvas.jlBlue){
	            System.out.println("파란선");
	            jpCanvas.can.nowColor = blue;
	            jpCanvas.can.brushSize=4;
	        }else if(obj == jpCanvas.jlBlack){
	            System.out.println("까만선");
	            jpCanvas.can.nowColor = black;
	            jpCanvas.can.brushSize=4;
	        }else if(obj == jpCanvas.jlEraser){
	            System.out.println("지우개선");
	            jpCanvas.can.nowColor = white;
	            jpCanvas.can.brushSize=16;
	        }else if(obj == jpCanvas.jlClear){
	            System.out.println("모두지움");
	            Graphics g=jpCanvas.can.getGraphics();
	            g.clearRect(0, 0, jpCanvas.can.getWidth(), jpCanvas.can.getHeight());
	        }else if(obj == jlExit){
	        	//game client창에
	        	System.exit(0);
	        }else if(obj == chat.jlSend){
				//채팅창의 전송버튼 눌렸을 때 동작구현
				//1. 채팅입력창 비우기 2.채팅입력창으로 커서 옮기기
				chat.jtfChatInput.setText("");
				chat.jtfChatInput.requestFocus();
			}else if(obj == jlStart){
				if(GameRoom.getInstance().getCharacterCount()>=3){
					//게임실행					
				}
			}
	        
			jpCanvas.can.prevX=-1;
			jpCanvas.can.prevY=-1;
	        System.out.println("prevX : " + jpCanvas.can.prevX 
	        				+ "\tprevY:" + jpCanvas.can.prevY);
		}//mousePressed end



		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()== KeyEvent.VK_ENTER){
				//채팅 입력후 엔터키 눌렀을 때 동작 구현
				//1. 채팅입력창 비우기 2.채팅입력창으로 커서 옮기기
				
				//내창에서 할일
				chat.jtaChatLog.append("[나]: "+chat.jtfChatInput.getText() + "\n");
				
				//서버에 채팅메시지 보냄
				ClientMain.getInstance().getClientSession().sendCharacterChat(chat.jtfChatInput.getText());
				chat.jtfChatInput.setText("");
				chat.jtfChatInput.requestFocus();
				
				//스크롤내리기
				JScrollBar jsb = chat.jspChatLog.getVerticalScrollBar();
				//jsb.getMaximum(); //최대치가 얼마인지 받아오기
				jsb.setValue(jsb.getMaximum()); //jsb바의 최대치로 세팅한다.
			}			
		}//keyPressed end

		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {
			ClientMain.getInstance().getClientSession().sendCharacterCanvas(jpCanvas.can.imageExport());	
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void mouseMoved(MouseEvent e) {}
		
	}//Handler class end



}//GameClient class end




