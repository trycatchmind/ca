package catchmindclient;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//jbtnImg backgroundcolor바꾸기
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
		System.out.println("user1 패널");
		setBounds (200,300,250,215);
		setLayout(null);
		
		//UserData 필요한 생성자들
		jpUser = new JPanel(); //유저 이미지 붙일 패널
		jpUser.setLayout(new BorderLayout()); 
		jpUserData=new JPanel();
		jpUserData.setLayout(null);  //user들 정보 붙일 패널
		
		setLayout(null);
		jlImg = new JLabel("이미지"); //이미지를 붙인 버튼
		jlId = new JLabel("아이디"); //아이디 
		jlLevel = new JLabel("레벨"); //레벨
		jlCorrect = new JLabel("정답"); //경험치
		
		//**나중에 삭제 하기
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//생성자들 크기 조절
		jpUser.setBounds(0,0,120,180);
		jpUserData.setBounds(120,0,130,180);
		
		jlId.setBounds(0,0,130,60);
		jlLevel.setBounds(0,60,130,60);
		jlCorrect.setBounds(0,120,130,60);
		
		
		//화면에 붙이기
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
