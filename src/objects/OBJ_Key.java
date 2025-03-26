package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Camera;

public class OBJ_Key extends SuperObject{
	
	
	public OBJ_Key(Camera cam) {
		//TODO add rectangle coordinates and size
		super(cam);
		name = "key";
		imageFile = "/objects/key.png";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
