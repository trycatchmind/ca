package catchmindclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


//jbtnImg backgroundcolor�ٲٱ�

public class CharacterPanel extends JPanel{
	JLabel jlCharacterImg;		//ĳ���� �̹����� ���� ��
	JLabel jlCharacterBlank;	//������̹��� ���� ��
	
	JPanel jpCharacterData;		//ĳ���� ������ �г�
		JLabel jlImageName;
		JLabel jlImageLevel;
		JLabel jlImageCorrect;
		JLabel jlCharacterName;		//ĳ���� �̸� ��
		JLabel jlCharacterLevel;	//ĳ���� ���� ��
		JLabel jlCharacterCorrect;	//ĳ���� ����� ��
	ImageIcon imgLabelBG = new ImageIcon("src/catchmindimg/labelBackground.png");
	
	
	CharacterPanel(){
		setBounds(200,300,320,190);
		setLayout(null);
		
		//UserData �ʿ��� �����ڵ�
		jlCharacterImg = new JLabel();	//�̹��� ���� label
		jlCharacterBlank = new JLabel(new ImageIcon("src/catchmindimg/blank.png"));
		jpCharacterData=new JPanel();		
		jpCharacterData.setLayout(null);		//user�� ���� ���� �г�
		jlImageName = new JLabel(imgLabelBG);
		jlImageLevel = new JLabel(imgLabelBG);
		jlImageCorrect = new JLabel(imgLabelBG);
		jlCharacterName = new JLabel("", JLabel.CENTER);	//���̵� 
		jlCharacterLevel = new JLabel("",JLabel.CENTER);	//����
		jlCharacterCorrect = new JLabel("",JLabel.CENTER);	//�����

		
		//���̺� ��Ʈ ����
		jlCharacterName.setFont(new Font("���� ���",Font.BOLD,25));
		jlCharacterLevel.setFont(new Font("���� ���",Font.BOLD,25));
		jlCharacterCorrect.setFont(new Font("���� ���",Font.BOLD,25));
		
				
		//�����ڵ� ũ�� ����
		jpCharacterData.setBounds(120,5,300,215);
		jlCharacterImg.setBounds(5,5,120,180);
		jlCharacterBlank.setBounds(0, 0, 120, 200);
		jlImageName.setBounds(15, 10, 170, 50);
		jlImageLevel.setBounds(15, 70, 170, 50);
		jlImageCorrect.setBounds(15, 130, 170, 50);
		jlCharacterName.setBounds(10,5,180,60);
		jlCharacterLevel.setBounds(10,65,180,60);
		jlCharacterCorrect.setBounds(10,125,180,60);
		
		//ĳ���� ������ ��� �����ϰ�
		jpCharacterData.setOpaque(false);
		
		//������ �гο� �󺧺��̱�
		jpCharacterData.add(jlCharacterName);
		jpCharacterData.add(jlImageName);
		jpCharacterData.add(jlCharacterLevel);
		jpCharacterData.add(jlImageLevel);
		jpCharacterData.add(jlCharacterCorrect);
		jpCharacterData.add(jlImageCorrect);
		
		
		
		//�����쿡 �гκ��̱�
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
