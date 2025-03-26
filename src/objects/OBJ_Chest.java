package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Camera;

public class OBJ_Chest extends SuperObject{

	public OBJ_Chest(Camera cam) {
		
		super(cam);
		name = "chest";
		imageFile = "/objects/chest.png";
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
