package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Camera;

public class OBJ_Door extends SuperObject{

	public boolean isClosed = true;
	
	public OBJ_Door(Camera cam) {
		//TODO add rectangle coordinates and size
		super(cam);
		name = "door";
		imageFile = "/objects/closedDoor.png";
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void open() {
		imageFile = "/objects/openDoor.png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = false;
	}
	
	public void close() {
		imageFile = "/objects/closedDoor.png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
