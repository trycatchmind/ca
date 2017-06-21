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
	JPasswordField  jtfPw; //.getPasssword�� �� ��������

	Socket s;
	
	public LogIn(){
		super("chat client");						//title����
		setDefaultCloseOperation(EXIT_ON_CLOSE);	//x������ ������ method
		setBounds(150,150,600,500);					//â ũ��� ��ġ ����
		setLayout(null);							//�⺻ ���̾ƿ����ֱ�
		
		
		//�̹��� ������
		iJoin = new ImageIcon("src/catchmindimg/jlJoin.PNG");
		iLogin = new ImageIcon("src/catchmindimg/jlLogin.PNG");
		ilExit = new ImageIcon("src/catchmindimg/jlExit.PNG");
		iLogLabel = new ImageIcon("src/catchmindimg/loginLabel.PNG");
		ilTitle = new ImageIcon("src/catchmindimg/gameTitle2.PNG");
		
		//instance
		Font f = new Font("�������",Font.BOLD,20);
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
		
		//��й�ȣ ��ȣȭ
		
		
		//���
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
		
		//��� ������Ʈ�� ũ��� ������
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
		
		//window�� component �߰�
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
				
		setResizable(false);				//���������� �ȵǰ�
		setVisible(true);					//���̰�
	}//constructor end
	

	@Override
	public void mouseClicked(MouseEvent e) {}


	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj == jlLogin){
			//�α���
			System.out.println("�α�����");
			ClientMain cm = ClientMain.getInstance();
			ClientSideSession cs = cm.getClientSession();
			cs.sendMemberLogin(jtfId.getText(), String.valueOf(jtfPw.getPassword()));
			System.out.println(String.valueOf(jtfPw.getPassword()));
			
		}else if(obj == jlExit){
			int select = JOptionPane.showConfirmDialog(this, "���� �����Ͻðڽ��ϱ�?", "Ȯ��", 0);
			if(select == 0){
				System.exit(0);
			}//if end
		}else if(obj == jlJoin){
			//ȸ������â�� ���
			System.out.println("ȸ��������");
			
			ClientMain cm = ClientMain.getInstance();
			
			//�α��� â�� �Ⱥ��̰� �س���, ȸ������â�� �߰� �Ѵ�.
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
				JOptionPane.showMessageDialog(this, "���̵� Ȯ�����ּ���");
			}
			else if(String.valueOf(jtfPw.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(this, "�н����带 Ȯ�����ּ���");
			}
			else{
				System.out.println("�α��� �׽�Ʈ");
				ClientMain cm = ClientMain.getInstance();
				ClientSideSession cs = cm.getClientSession();
				cs.sendMemberLogin(jtfId.getText(), String.valueOf(jtfPw.getPassword()));
				
			}
		}
	}


	//�Ⱦ��� override method��
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
