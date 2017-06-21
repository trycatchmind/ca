package catchmindcommon;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProcotolCharacterCanvas extends Protocol {
	int memberNo;		//유저 고유번호
	String name; 		//캐릭터 이름
	byte[] image;		//그린 그림
	
	public ProcotolCharacterCanvas() {
		super(PROTOCOL_CHARACTER_CANVAS);
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImage() {
		ByteArrayInputStream buffer = new ByteArrayInputStream(this.image);
		BufferedImage img = null;
		try {
			img = ImageIO.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void setImage(BufferedImage img) {
		// 네트워크에서 주고 받을 수 있게 직렬화 시킴
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();		
		try {
			ImageIO.write(img, "png", buffer);
			buffer.flush();
			this.image = buffer.toByteArray();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}