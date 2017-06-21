package catchmindclient;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Drawing extends JPanel{
	JPanel jpSelect; 
	//���� �����ϴ� �гΰ� �׸��� �׸��� ���� �г� �ΰ����� ����
	JLabel jlRed, jlBlack, jlBlue,jlEraser,jlClear;
	//������ ������ �Ķ���, ���찳, ���������ư	
	ImageIcon iBlack,iRed,iBlue,iEraser,iClear;
	MyCanvas can;
	//������ Ŭ����
	 
	
	public Drawing(){
		setBounds(200,200,600,400);
		setLayout(null);

		iBlack = new ImageIcon("src/catchmindimg/black.PNG");
		iRed = new ImageIcon("src/catchmindimg/red.PNG");
		iBlue = new ImageIcon("src/catchmindimg/blue.PNG");
		iEraser = new ImageIcon("src/catchmindimg/eraser.PNG");
		iClear = new ImageIcon("src/catchmindimg/jlClear1.PNG");		
		//���߿� ����
		jpSelect = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(new ImageIcon("src/catchmindimg/drawingSelectbar.png").getImage(),
						0, 0, null);
				super.paintComponent(g);
			}
		}; //���� ���찳�� �г�
		jpSelect.setLayout(null);
		jlRed = new JLabel(iRed); //������ ��ư
		jlBlack= new JLabel(iBlack); //������ ��ư
		jlBlue= new JLabel(iBlue); //�Ķ��� ��ư
		jlEraser= new JLabel(iEraser); //���찳 ��ư
		jlClear= new JLabel(iClear); //�������� ��ư
		
		//ĵ����
		can=new MyCanvas(); 
		can.setBackground(Color.white); 
		
		//������ ũ�� ����
		jpSelect.setBounds(0,400,600,40);
		jlRed.setBounds(20,5,30,30); //��ư ������ ������ 30
		jlBlack.setBounds(70,5,30,30);
		jlBlue.setBounds(120,5,30,30);
		jlEraser.setBounds(170,5,35,30);
		jlClear.setBounds(220,3,100,30);
		can.setBounds(0, 0, 600, 400);
		
		
		
		jpSelect.add(jlRed);
		jpSelect.add(jlBlack);
		jpSelect.add(jlBlue);
		jpSelect.add(jlEraser);
		jpSelect.add(jlClear);
		
		
		//�г� �����ϰ�
		jpSelect.setOpaque(false);
		setOpaque(false);
		
		add(can,"Center");
		add(jpSelect,"South");
		
	}

//	public static void main(String[] args) {
//		new DrawingTest();	
//		}

}
