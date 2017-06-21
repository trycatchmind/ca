package catchmindcommon;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProcotolCharacterCanvas extends Protocol {
	int memberNo;		//���� ������ȣ
	String name; 		//ĳ���� �̸�
	byte[] image;		//�׸� �׸�
	
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
		// ��Ʈ��ũ���� �ְ� ���� �� �ְ� ����ȭ ��Ŵ
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