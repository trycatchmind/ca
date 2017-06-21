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
    // ó���� ��� �� �������� �ϱ� ���ؼ� x,y -�� ����
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
        g.fillOval(x-(brushSize/2), y-(brushSize/2), brushSize, brushSize); // x, y ������ 4,4 ũ���� �� �׸���
         
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
		// ȭ�鿡 �ִ� ������ �������� ����� ���� ��ũ���� ����					
		BufferedImage bimg = null;		
		
		// ���� ĵ�۽� ��ġ�� ã�Ƽ�
		Rectangle screenRect = new Rectangle(getLocationOnScreen(), getSize());
		
		try {
			// ��ũ������ ������
			bimg = new Robot().createScreenCapture(screenRect);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		// �̹��� ��ȯ
		return bimg;
	}
	
	public void imageImport(BufferedImage bimg) {		
		Graphics2D cg = bimg.createGraphics();
				
		// ���� ȭ���� �����
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        
        // ���� �̹����� �����
		getGraphics().drawImage(bimg, 0,0, null);		
	}
}


