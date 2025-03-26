package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Camera;

public class OBJ_Door extends SuperObject{

	public OBJ_Door(Camera cam) {
		
		super(cam);
		name = "door";
		imageFile = "/objects/door.png";
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
