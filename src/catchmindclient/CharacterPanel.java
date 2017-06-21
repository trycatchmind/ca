package catchmindclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


//jbtnImg backgroundcolor바꾸기

public class CharacterPanel extends JPanel{
	JLabel jlCharacterImg;		//캐릭터 이미지가 들어가는 라벨
	JLabel jlCharacterBlank;	//배경흰이미지 들어가는 라벨
	
	JPanel jpCharacterData;		//캐릭터 데이터 패널
		JLabel jlImageName;
		JLabel jlImageLevel;
		JLabel jlImageCorrect;
		JLabel jlCharacterName;		//캐릭터 이름 라벨
		JLabel jlCharacterLevel;	//캐릭터 레벨 라벨
		JLabel jlCharacterCorrect;	//캐릭터 정답수 라벨
	ImageIcon imgLabelBG = new ImageIcon("src/catchmindimg/labelBackground.png");
	
	
	CharacterPanel(){
		setBounds(200,300,320,190);
		setLayout(null);
		
		//UserData 필요한 생성자들
		jlCharacterImg = new JLabel();	//이미지 들어가는 label
		jlCharacterBlank = new JLabel(new ImageIcon("src/catchmindimg/blank.png"));
		jpCharacterData=new JPanel();		
		jpCharacterData.setLayout(null);		//user들 정보 붙일 패널
		jlImageName = new JLabel(imgLabelBG);
		jlImageLevel = new JLabel(imgLabelBG);
		jlImageCorrect = new JLabel(imgLabelBG);
		jlCharacterName = new JLabel("", JLabel.CENTER);	//아이디 
		jlCharacterLevel = new JLabel("",JLabel.CENTER);	//레벨
		jlCharacterCorrect = new JLabel("",JLabel.CENTER);	//정답수

		
		//레이블 폰트 설정
		jlCharacterName.setFont(new Font("맑은 고딕",Font.BOLD,25));
		jlCharacterLevel.setFont(new Font("맑은 고딕",Font.BOLD,25));
		jlCharacterCorrect.setFont(new Font("맑은 고딕",Font.BOLD,25));
		
				
		//생성자들 크기 조절
		jpCharacterData.setBounds(120,5,300,215);
		jlCharacterImg.setBounds(5,5,120,180);
		jlCharacterBlank.setBounds(0, 0, 120, 200);
		jlImageName.setBounds(15, 10, 170, 50);
		jlImageLevel.setBounds(15, 70, 170, 50);
		jlImageCorrect.setBounds(15, 130, 170, 50);
		jlCharacterName.setBounds(10,5,180,60);
		jlCharacterLevel.setBounds(10,65,180,60);
		jlCharacterCorrect.setBounds(10,125,180,60);
		
		//캐릭터 데이터 배경 투명하게
		jpCharacterData.setOpaque(false);
		
		//데이터 패널에 라벨붙이기
		jpCharacterData.add(jlCharacterName);
		jpCharacterData.add(jlImageName);
		jpCharacterData.add(jlCharacterLevel);
		jpCharacterData.add(jlImageLevel);
		jpCharacterData.add(jlCharacterCorrect);
		jpCharacterData.add(jlImageCorrect);
		
		
		
		//윈도우에 패널붙이기
		add(jlCharacterImg);
		add(jlCharacterBlank);
		add(jpCharacterData);
		
		setOpaque(false);

		setVisible(true);		
	}
	

//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CharacterPanel();

	}

}
