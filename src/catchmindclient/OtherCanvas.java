package catchmindclient;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class OtherCanvas extends Canvas{
	public void imageImport(BufferedImage bimg) {		
		Graphics2D cg = bimg.createGraphics();
				
		// ���� ȭ���� �����
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        
        // ���� �̹����� �����
		getGraphics().drawImage(bimg, 0,0, null);		
	}

}
