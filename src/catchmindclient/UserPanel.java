package catchmindclient;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//jbtnImg backgroundcolor�ٲٱ�
public class UserPanel extends JPanel{
	JPanel jpUser;
	JPanel jpUserData;
	JPanel jpUserAll;
	JLabel jlImg;
	JLabel jlId;
	JLabel jlLevel;
	JLabel jlCorrect;
	ImageIcon iImg;
	
	UserPanel(){
		
		this.setBackground(Color.blue);
		System.out.println("user1 �г�");
		setBounds (200,300,250,215);
		setLayout(null);
		
		//UserData �ʿ��� �����ڵ�
		jpUser = new JPanel(); //���� �̹��� ���� �г�
		jpUser.setLayout(new BorderLayout()); 
		jpUserData=new JPanel();
		jpUserData.setLayout(null);  //user�� ���� ���� �г�
		
		setLayout(null);
		jlImg = new JLabel("�̹���"); //�̹����� ���� ��ư
		jlId = new JLabel("���̵�"); //���̵� 
		jlLevel = new JLabel("����"); //����
		jlCorrect = new JLabel("����"); //����ġ
		
		//**���߿� ���� �ϱ�
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//�����ڵ� ũ�� ����
		jpUser.setBounds(0,0,120,180);
		jpUserData.setBounds(120,0,130,180);
		
		jlId.setBounds(0,0,130,60);
		jlLevel.setBounds(0,60,130,60);
		jlCorrect.setBounds(0,120,130,60);
		
		
		//ȭ�鿡 ���̱�
		jpUser.add(jlImg);
		jpUserData.add(jlId);
		jpUserData.add(jlLevel);
		jpUserData.add(jlCorrect);
		add(jpUser);
		add(jpUserData);

		//add(jpUserAll);
		
		setVisible(true);
		
	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new User1();
//
//	}

}
