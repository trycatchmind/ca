package catchmindclient;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class OtherCanvas extends Canvas{
	public void imageImport(BufferedImage bimg) {		
		Graphics2D cg = bimg.createGraphics();
				
		// 현재 화면을 지우고
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        
        // 받은 이미지를 덮어씌움
		getGraphics().drawImage(bimg, 0,0, null);		
	}

}
