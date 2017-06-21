package catchmindclient;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LogIn extends JFrame implements MouseListener,KeyListener,Runnable{
	
	JLabel jlLogin, jlExit, jlJoin;
	JLabel jlId, jlPw,jlIdImg,jlPwImg,jlTtImg;
	JTextField jtfId;
	ImageIcon iJoin,iLogin,ilExit,bgColor,iLogLabel,ilTitle;
	JPanel jpBackground;
	JScrollPane jspBackground;
	JPasswordField  jtfPw; //.getPasssword로 값 가져오기

	Socket s;
	
	public LogIn(){
		super("chat client");						//title설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);	//x누르면 꺼지는 method
		setBounds(150,150,600,500);					//창 크기와 위치 설정
		setLayout(null);							//기본 레이아웃없애기
		
		
		//이미지 생성자
		iJoin = new ImageIcon("src/catchmindimg/jlJoin.PNG");
		iLogin = new ImageIcon("src/catchmindimg/jlLogin.PNG");
		ilExit = new ImageIcon("src/catchmindimg/jlExit.PNG");
		iLogLabel = new ImageIcon("src/catchmindimg/loginLabel.PNG");
		ilTitle = new ImageIcon("src/catchmindimg/gameTitle2.PNG");
		
		//instance
		Font f = new Font("나눔고딕",Font.BOLD,20);
		jlId = new JLabel("ID",JLabel.CENTER);
		jlPw = new JLabel("PW",JLabel.CENTER);
		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jlLogin = new JLabel(iLogin);
		jlExit = new JLabel(ilExit);
		jlJoin = new JLabel(iJoin);
		jlIdImg = new JLabel(iLogLabel);
		jlPwImg = new JLabel(iLogLabel);
		jlTtImg = new JLabel(ilTitle);
		
		//비밀번호 암호화
		
		
		//배경
		bgColor = new ImageIcon("src/catchmindimg/bjColor.jpg");
		jpBackground = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(bgColor.getImage(), 0, 0, null);
				setOpaque(false);
			}
		};
		
		jpBackground.setLayout(null);
		
		//모든 컴포넌트의 크기와 사이즈
		jlTtImg.setBounds(100,100, 400, 100);
		jlId.setBounds(130,	260, 50,30);
		jlIdImg.setBounds(125,250,60,40);
		jtfId.setBounds(250,260,200,30);
		jlPw.setBounds(130,300,50,30);
		jlPwImg.setBounds(125,290,60,40);
		jtfPw.setBounds(250,300,200,30);
		jlLogin.setBounds(110,390,100,40);
		jlExit.setBounds(230,390,100,40);
		jlJoin.setBounds(350,390,100,40);
		
		
		
		//ActionListener
		jlLogin.addMouseListener(this);
		jlExit.addMouseListener(this);
		jlJoin.addMouseListener(this);
		
		//keyboardListener
		jtfId.addKeyListener(this);
		jtfPw.addKeyListener(this);
		
		//window에 component 추가
		jpBackground.add(jlId);
		jpBackground.add(jlIdImg);
		jpBackground.add(jtfId);
		jpBackground.add(jlPw);
		jpBackground.add(jlPwImg);
		jpBackground.add(jtfPw);
		jpBackground.add(jlLogin);
		jpBackground.add(jlExit);
		jpBackground.add(jlJoin);
		jpBackground.add(jlTtImg);
		
		//jscroll
		jspBackground = new JScrollPane(jpBackground); 
		setContentPane(jspBackground);
				
		setResizable(false);				//사이즈조절 안되게
		setVisible(true);					//보이게
	}//constructor end
	

	@Override
	public void mouseClicked(MouseEvent e) {}


	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj == jlLogin){
			//로그인
			System.out.println("로그인함");
			ClientMain cm = ClientMain.getInstance();
			ClientSideSession cs = cm.getClientSession();
			cs.sendMemberLogin(jtfId.getText(), String.valueOf(jtfPw.getPassword()));
			System.out.println(String.valueOf(jtfPw.getPassword()));
			
		}else if(obj == jlExit){
			int select = JOptionPane.showConfirmDialog(this, "정말 종료하시겠습니까?", "확인", 0);
			if(select == 0){
				System.exit(0);
			}//if end
		}else if(obj == jlJoin){
			//회원가입창을 띄움
			System.out.println("회원가입함");
			
			ClientMain cm = ClientMain.getInstance();
			
			//로그인 창을 안보이게 해놓고, 회원가입창을 뜨게 한다.
			this.setVisible(false);
			cm.getJoinWindow().setVisible(true);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyboard = e.getKeyCode();
		
		if(keyboard==e.VK_ENTER)
		{
			if(jtfId.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "아이디를 확인해주세요");
			}
			else if(String.valueOf(jtfPw.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(this, "패스워드를 확인해주세요");
			}
			else{
				System.out.println("로그인 테스트");
				ClientMain cm = ClientMain.getInstance();
				ClientSideSession cs = cm.getClientSession();
				cs.sendMemberLogin(jtfId.getText(), String.valueOf(jtfPw.getPassword()));
				
			}
		}
	}


	//안쓰는 override method들
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}	

	@Override
	public void run() {
	
	}//run end	
//	public static void main(String[] args) {
//		new LogIn();
//	}
}
