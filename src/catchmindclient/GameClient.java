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

	static int CHARACTER_IN_ROOM = 4;			//�ѹ濡 ���� ĳ����4��
	CharacterPanel[] character
		= new CharacterPanel[CHARACTER_IN_ROOM];//ĳ���ͼ���ŭ�� ĳ���� �гι迭 ����
	GameChat chat;								//ä���г�
	JLabel jlStart,jlExit;						//����, �������ư
	Drawing jpCanvas;//ĵ����
	OtherDrawing jpOtherCanvas;
	JLabel jlTimer,jlWord; 						//Ÿ�̸Ӹ� ���� �г�
	GameTimer timer;							//Ÿ�̸� Ŭ����
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// x������ ������
		
		//���� �������� �г�
		character[0] = new CharacterPanel();
		character[1] = new CharacterPanel();
		character[2] = new CharacterPanel();
		character[3] = new CharacterPanel();
		

		//Canvas panel
		jpCanvas = new Drawing();
		jpOtherCanvas = new OtherDrawing();
		jpOtherCanvas.setOpaque(false);
		
		//ä��â panel
		chat = new GameChat();
		
		//ReadyButton
		jlStart = new JLabel(new ImageIcon(imgsrc[0]));
		jlExit = new JLabel(new ImageIcon("src/catchmindimg/mainjlExit.PNG"));
		
		//�ܾ� ���̺�
		jlWord = new JLabel();
		
		//���
		iBackground = new ImageIcon("src/catchmindimg/BG1.PNG");
		jpBackground = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
                g.drawImage(iBackground.getImage(),0,0, null);
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
            }
		};
		
		jpBackground.setLayout(null);
		
		//canvas ���� panelǥ��
		jpCanvas.setBounds(400,170,600,440);
		jpOtherCanvas.setBounds(400,170,600,440);
		
		//ä�� �г� ��ġ 
		chat.setBounds(400,620,600,400);
 
		//������ �г� ��ġ
		character[0].setBounds(30, 160, 320,190);
		character[1].setBounds(1050,160, 320,190);
		character[2].setBounds(30, 380, 320,190);
		character[3].setBounds(1050,380, 320,190);
		
		//�����ư��ġ
		jlStart.setBounds(1050,620,300,100);
		jlExit.setBounds(1165,30,200,58);
		
		jlWord.setBounds(400, 120,300,50); //�ܾ� ���̺� ��ġ �ٲٱ�
		jlWord.setFont(new Font("���� ���",Font.BOLD,18));
		
		//�� �г� �߰�
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
//		jpBackground.add(jlTimer); //Ÿ�̸Ӹ� �� �� �ִ� ���̺�
		
		//JScrollpanel
		jspBackground = new JScrollPane(jpBackground);
		setContentPane(jspBackground);

		//�����ʺ���
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
		
		//JFrame �⺻��ɼ���
		setResizable(false);						//���������� �ȵǰ�
		setVisible(true);							//���̰�
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
		
		System.out.println("5������");
		cl.jlStart.setIcon(new ImageIcon(imgsrc[1]));
		cl.jpCanvas.setVisible(false);
		cl.jpOtherCanvas.setVisible(true);
		cl.chat.jtfChatInput.setEditable(false);
		Word wd = new Word();
		String output = wd.strWord[(int)(Math.random()*wd.strWord.length)];
		//0<Math.random()<1*strWord.lengthw 
//else if (������ �������� ������) output = null;
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
	            System.out.println("������");
	            jpCanvas.can.nowColor = red;
	            jpCanvas.can.brushSize=4;
	        }else if(obj == jpCanvas.jlBlue){
	            System.out.println("�Ķ���");
	            jpCanvas.can.nowColor = blue;
	            jpCanvas.can.brushSize=4;
	        }else if(obj == jpCanvas.jlBlack){
	            System.out.println("���");
	            jpCanvas.can.nowColor = black;
	            jpCanvas.can.brushSize=4;
	        }else if(obj == jpCanvas.jlEraser){
	            System.out.println("���찳��");
	            jpCanvas.can.nowColor = white;
	            jpCanvas.can.brushSize=16;
	        }else if(obj == jpCanvas.jlClear){
	            System.out.println("�������");
	            Graphics g=jpCanvas.can.getGraphics();
	            g.clearRect(0, 0, jpCanvas.can.getWidth(), jpCanvas.can.getHeight());
	        }else if(obj == jlExit){
	        	//game clientâ��
	        	System.exit(0);
	        }else if(obj == chat.jlSend){
				//ä��â�� ���۹�ư ������ �� ���۱���
				//1. ä���Է�â ���� 2.ä���Է�â���� Ŀ�� �ű��
				chat.jtfChatInput.setText("");
				chat.jtfChatInput.requestFocus();
			}else if(obj == jlStart){
				if(GameRoom.getInstance().getCharacterCount()>=3){
					//���ӽ���					
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
				//ä�� �Է��� ����Ű ������ �� ���� ����
				//1. ä���Է�â ���� 2.ä���Է�â���� Ŀ�� �ű��
				
				//��â���� ����
				chat.jtaChatLog.append("[��]: "+chat.jtfChatInput.getText() + "\n");
				
				//������ ä�ø޽��� ����
				ClientMain.getInstance().getClientSession().sendCharacterChat(chat.jtfChatInput.getText());
				chat.jtfChatInput.setText("");
				chat.jtfChatInput.requestFocus();
				
				//��ũ�ѳ�����
				JScrollBar jsb = chat.jspChatLog.getVerticalScrollBar();
				//jsb.getMaximum(); //�ִ�ġ�� ������ �޾ƿ���
				jsb.setValue(jsb.getMaximum()); //jsb���� �ִ�ġ�� �����Ѵ�.
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




