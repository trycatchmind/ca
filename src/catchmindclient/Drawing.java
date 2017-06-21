package catchmindclient;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Drawing extends JPanel{
	JPanel jpSelect; 
	//색을 지정하는 패널과 그림을 그리는 메인 패널 두가지로 구성
	JLabel jlRed, jlBlack, jlBlue,jlEraser,jlClear;
	//빨간색 검정색 파란색, 지우개, 모두지우기버튼	
	ImageIcon iBlack,iRed,iBlue,iEraser,iClear;
	MyCanvas can;
	//컨버스 클래스
	 
	
	public Drawing(){
		setBounds(200,200,600,400);
		setLayout(null);

		iBlack = new ImageIcon("src/catchmindimg/black.PNG");
		iRed = new ImageIcon("src/catchmindimg/red.PNG");
		iBlue = new ImageIcon("src/catchmindimg/blue.PNG");
		iEraser = new ImageIcon("src/catchmindimg/eraser.PNG");
		iClear = new ImageIcon("src/catchmindimg/jlClear1.PNG");		
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
		jlRed = new JLabel(iRed); //빨간색 버튼
		jlBlack= new JLabel(iBlack); //검정색 버튼
		jlBlue= new JLabel(iBlue); //파란색 버튼
		jlEraser= new JLabel(iEraser); //지우개 버튼
		jlClear= new JLabel(iClear); //모두지우기 버튼
		
		//캔버스
		can=new MyCanvas(); 
		can.setBackground(Color.white); 
		
		//생성자 크기 조절
		jpSelect.setBounds(0,400,600,40);
		jlRed.setBounds(20,5,30,30); //버튼 사이의 간격은 30
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
		
		
		//패널 투명하게
		jpSelect.setOpaque(false);
		setOpaque(false);
		
		add(can,"Center");
		add(jpSelect,"South");
		
	}

//	public static void main(String[] args) {
//		new DrawingTest();	
//		}

}
