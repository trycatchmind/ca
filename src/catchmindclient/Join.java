package catchmindclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//ȸ�������ϴ� window
//id,pw,pwȮ��,ĳ���ͼ���
//���Թ�ư,�����ư

public class Join extends JFrame implements MouseListener{
	final static int NUM_CHAR=6;					//���ð����� ĳ���ͼ�
	JLabel[] character=new JLabel[NUM_CHAR];		//ĳ���� ���� ���̺� �迭
	ImageIcon[] imgChar = new ImageIcon[NUM_CHAR];	//ĳ���� �̹��� �迭
	String[] charName = {"�߿���","����","�����˽�Ÿ��2��","������","����","�غ���"};//ĳ�����̸��迭	
	JLabel jlJoinTitle, jlId, jlPw, jlPwConfirm, jlChar, jlCharacterName,jlJoinLabelId,jlJoinLabelPw,jlJoinLabelPwConfirm,jlJoinLabelChar,jlJoinLabelCharacterName;	//ȸ������ �����׸� ���̺�
	JTextField jtfId, jtfCharacterName;			//ȸ������ �����׸� �Է� �ؽ�Ʈ�ʵ�
	JPasswordField jtfPw, jtfPwConfirm;
	JLabel jlJoin, jlExit;							//����/������ ���̺�
	int characterType = -1;                     //ĳ������ �ε��� ��
	JPanel jpBackground;
	JScrollPane jspBackground;
	ImageIcon bgColor,iJoinLabel,iJlExit,iJlJoin,iTitle;
	
	Join(){
		//window�� ��ġ�� ������(���Ǽ���)
		setBounds(500,300,500,800);
		
		//�⺻ ���̾ƿ� ����
		setLayout(null);
		
		//x��ư ������ ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//component instance
		//Ÿ��Ʋ�� ��Ʈ����
		iTitle = new ImageIcon("src/catchmindimg/join.png");
		Font f = new Font("�����ٸ���",Font.BOLD,30);
		jlJoinTitle = new JLabel(iTitle);
		jlJoinTitle.setFont(f);
		
		//���
		iJoinLabel = new ImageIcon("src/catchmindimg/joinLable.png");
		iJlExit = new ImageIcon("src/catchmindimg/jlExit.png");
		iJlJoin = new ImageIcon("src/catchmindimg/jlJoin.png");
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
		
		//���̵�
		jlId = new JLabel("       ���̵�");
		jlJoinLabelId = new JLabel(iJoinLabel);
		jtfId = new JTextField();
		
		//��й�ȣ�� Ȯ��
		jlPw = new JLabel("     ��й�ȣ");
		jlJoinLabelPw = new JLabel(iJoinLabel);
		jtfPw = new JPasswordField();
		jlPwConfirm = new JLabel("��й�ȣ Ȯ��");
		jlJoinLabelPwConfirm = new JLabel(iJoinLabel);
		jtfPwConfirm = new JPasswordField();
		
		
		
		
		//ĳ����
		jlChar = new JLabel("  ĳ���� ����");
		jlJoinLabelChar = new JLabel(iJoinLabel);
		for(int i =0; i<character.length;i++){
			imgChar[i] = new ImageIcon("src/catchmindimg/halfrobot" + i + ".PNG");
			character[i] = new JLabel(charName[i]);
			character[i].setIcon(imgChar[i]);
			
		}
		jlCharacterName = new JLabel("   ĳ���͸�");
		jlJoinLabelCharacterName = new JLabel(iJoinLabel);
		jtfCharacterName = new JTextField();
		
		//���԰� ��ҹ�ư	
		jlJoin = new JLabel(iJlJoin);
		jlExit = new JLabel(iJlExit);
		
		
		//component�� ��ġ�� ������ ����
		int x=100, y=200;
		jlJoinTitle.setBounds(150,50,200,100);
		jlId.setBounds(x,y,100,30);
		jlPw.setBounds(x,y+50,100,30);
		jlPwConfirm.setBounds(x,y+100,100,30);
		jlChar.setBounds(x,y+150,100,30);
		
		//Label��� ��ġ
		jlJoinLabelId.setBounds(x-10,y,100,30);
		jlJoinLabelPw.setBounds(x-10,y+50,100,30);
		jlJoinLabelPwConfirm.setBounds(x-10,y+100,100,30);
		jlJoinLabelChar.setBounds(x-10,y+150,100,30);
		
		
		
		jtfId.setBounds(x+150, y, 150, 30);
		jtfPw.setBounds(x+150, y+50, 150, 30);
		jtfPwConfirm.setBounds(x+150, y+100, 150, 30);
		
		for(int i=0; i<character.length/3;i++)
			for(int j=0; j<3;j++)
				character[3*i+j].setBounds(x+(76*j)+70, y+(88*i)+180, 76, 88);
		jlJoin.setBounds(x, 650, 100, 40);
		jlExit.setBounds(x+150, 650, 100, 40);
		jlCharacterName.setBounds(x, y+380, 150, 30);
		jlJoinLabelCharacterName.setBounds(x-40, y+380, 150, 30);
		jtfCharacterName.setBounds(x+150, y +380, 150, 30);
		
		
		//character image�� ���̺� ������ �����ֵ���
		for(int i =0; i<NUM_CHAR; i++)
			character[i].setOpaque(true);
			
			
		//window�� �߰�
		jpBackground.add(jlJoinTitle);
		jpBackground.add(jlId);
		jpBackground.add(jlPw);
		jpBackground.add(jlPwConfirm);
		jpBackground.add(jlChar);
		
		
		
		
		jpBackground.add(jtfId);
		jpBackground.add(jtfPw);
		jpBackground.add(jtfPwConfirm);
		for(int i=0; i<character.length;i++){
			character[i].setBackground(Color.white);
			jpBackground.add(character[i]);
		}
		jpBackground.add(jlCharacterName);
		jpBackground.add(jtfCharacterName);
		jpBackground.add(jlJoin);
		jpBackground.add(jlExit);
				
		//���̺���
		jpBackground.add(jlJoinLabelId);
		jpBackground.add(jlJoinLabelPw);
		jpBackground.add(jlJoinLabelPwConfirm);
		jpBackground.add(jlJoinLabelChar);
		jpBackground.add(jlJoinLabelCharacterName);
		
		//������ ����
		for(int i=0; i<NUM_CHAR; i++)				//ĳ���Ϳ� ������ ����
			character[i].addMouseListener(this);
		jlJoin.addMouseListener(this);				//ȸ������
		jlExit.addMouseListener(this);				//���
		//Ŭ���ϸ� �׵θ�������(�����ؾ���)
		
		//jscroll
		jspBackground = new JScrollPane(jpBackground); 
		setContentPane(jspBackground);
		
		//������ �⺻���
		setResizable(false);	//�����������ȵǰ�
		setVisible(true);		//���̰�
	}//constructor end
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}//mouseClicked method end


	@Override
	public void mousePressed(MouseEvent e) {
		JLabel obj = (JLabel)e.getSource();
		
		boolean judge = true;
		
		if(obj.equals(jlJoin)){
			//����Ŭ�� ->������ ������ ����
			
			//�ȿ� ������ ������ alert �޽����� ���.
			if(jtfId.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���");
			}
			else if(String.valueOf(jtfPw.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(this, "�н����带 �Է����ּ���");
			}
			else if(String.valueOf(jtfPwConfirm.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(this, "�н����� Ȯ�ΰ��� �Է����ּ���");
			}
			else if(!String.valueOf(jtfPw.getPassword()).equals(String.valueOf(jtfPwConfirm.getPassword()))){
				JOptionPane.showMessageDialog(this, "�н����尡 �ٸ��ϴ�.");
				jtfPw.setText("");
				jtfPwConfirm.setText("");
			}
			else if(characterType < 0){
				JOptionPane.showMessageDialog(this, "ĳ���͸� �������ּ���");
			}
			else if(jtfCharacterName.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "�г����� �Է����ּ���");
				System.out.println("���õ�ĳ����:" + characterType);
			}
			else{
				ClientMain.getInstance().getClientSession().sendMemberCreate(jtfId.getText(), String.valueOf(jtfPw.getPassword()), characterType, jtfCharacterName.getText());
				//��������
				System.out.println("�����ϱ�");
			}
			
			
		}else if(obj.equals(jlExit)){
			//���Ŭ��
			int select = JOptionPane.showConfirmDialog(this, "������ ����Ͻðڽ��ϱ�?", "Ȯ��", 0);
			System.out.println(select);
			if(select==0){
				this.setVisible(false);
				ClientMain cm = ClientMain.getInstance();
				cm.getLoginWindow().setVisible(true);
			}
		}else{
			//ĳ������ �ε������� �� ĳ���ͷ��̺�迭�� ��������.
			if(obj.equals(character[0])) characterType = 0;
			else if(obj.equals(character[1])) characterType = 1;
			else if(obj.equals(character[2])) characterType = 2;
			else if(obj.equals(character[3])) characterType = 3;
			else if(obj.equals(character[4])) characterType = 4;
			else if(obj.equals(character[5])) characterType = 5;
								
			obj.setBackground(Color.DARK_GRAY);
						
			//ĳ���͸� ����Ʈ �̸� �����ϱ�
			jtfCharacterName.setText(obj.getText());
			
			//���õ��� ���� ĳ���� ���� ���ֱ�
			for(int i=0; i<NUM_CHAR; i++)
			{
				if(character[i] != obj)
					character[i].setBackground(Color.white);
			}
		}
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}


	public static void main(String[] args) {
		new Join();
	}//main method end
}
