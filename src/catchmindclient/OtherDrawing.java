package catchmindclient;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OtherDrawing extends JPanel{
	JPanel jpSelect; 
	//���� �����ϴ� �гΰ� �׸��� �׸��� ���� �г� �ΰ����� ����

	OtherCanvas can;
	//������ Ŭ����
	 
	
	public OtherDrawing(){
		setBounds(200,200,600,440);
		setLayout(null);


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
		jpSelect.setOpaque(false);

		
		//ĵ����
		can=new OtherCanvas(); 
		can.setBackground(Color.white); 
		
		//������ ũ�� ����
		jpSelect.setBounds(0,400,600,40);
		can.setBounds(0, 0, 600, 400);
		
		
		
		
		//�г� �����ϰ�
		jpSelect.setOpaque(false);
//		setOpaque(false);
		
		add(can,"Center");
		add(jpSelect,"South");
		
		
	}

/*	public static void main(String[] args) {
		new OtherDrawing();	
		}*/

}
