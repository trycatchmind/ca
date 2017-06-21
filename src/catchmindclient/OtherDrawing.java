package catchmindclient;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OtherDrawing extends JPanel{
	JPanel jpSelect; 
	//색을 지정하는 패널과 그림을 그리는 메인 패널 두가지로 구성

	OtherCanvas can;
	//컨버스 클래스
	 
	
	public OtherDrawing(){
		setBounds(200,200,600,440);
		setLayout(null);


		//나중에 삭제
		jpSelect = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(new ImageIcon("src/catchmindimg/drawingSelectbar.png").getImage(),
						0, 0, null);
				super.paintComponent(g);
			}
		}; //색과 지우개들어갈 패널
		jpSelect.setLayout(null);
		jpSelect.setOpaque(false);

		
		//캔버스
		can=new OtherCanvas(); 
		can.setBackground(Color.white); 
		
		//생성자 크기 조절
		jpSelect.setBounds(0,400,600,40);
		can.setBounds(0, 0, 600, 400);
		
		
		
		
		//패널 투명하게
		jpSelect.setOpaque(false);
//		setOpaque(false);
		
		add(can,"Center");
		add(jpSelect,"South");
		
		
	}

/*	public static void main(String[] args) {
		new OtherDrawing();	
		}*/

}
