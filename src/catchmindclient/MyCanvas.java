package catchmindclient;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;


public class MyCanvas extends Canvas {
    // 처음에 까만색 점 안찍히게 하기 위해서 x,y -값 지정
    int prevX=-1;
    int prevY=-1;
    int x = -50;
    int y=-50;
    Color nowColor = Color.black;
    int brushSize=4;
 
    @Override
    public void paint(Graphics g){
//      super.paint(g);
        g.setColor(nowColor);
        g.fillOval(x-(brushSize/2), y-(brushSize/2), brushSize, brushSize); // x, y 지점에 4,4 크기의 원 그리기
         
        Graphics2D g2d = (Graphics2D)g;
         
        g2d.setStroke(new BasicStroke(brushSize));
         
        if(prevX!=-1 && prevY !=-1){            
          g2d.drawLine(x, y, prevX, prevY);           
        }
        prevX = x;
        prevY = y;  
    }//paint method end
 
    @Override
    public void update(Graphics g){
        paint(g);
    }//update method end
	
	public BufferedImage imageExport() {
		// 화면에 있는 내용을 가져오는 방법을 몰라서 스크린샷 찍음					
		BufferedImage bimg = null;		
		
		// 현재 캔퍼스 위치를 찾아서
		Rectangle screenRect = new Rectangle(getLocationOnScreen(), getSize());
		
		try {
			// 스크린샷을 찍은후
			bimg = new Robot().createScreenCapture(screenRect);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		// 이미지 반환
		return bimg;
	}
	
	public void imageImport(BufferedImage bimg) {		
		Graphics2D cg = bimg.createGraphics();
				
		// 현재 화면을 지우고
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        
        // 받은 이미지를 덮어씌움
		getGraphics().drawImage(bimg, 0,0, null);		
	}
}


