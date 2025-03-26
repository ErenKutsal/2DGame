package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Camera;
import main.GamePanel;

public class SuperObject {
	
	public String imageFile;
	public BufferedImage image;
	public String name;
	public Camera cam;
	public boolean collision = false;
	public int worldX, worldY;
	public int screenX, screenY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0; 
	public int solidAreaDefaultY = 0;
	
	public SuperObject() {
		
	}
	public SuperObject(Camera cam) {
		
		this.cam = cam;
		
	}
	public void draw(Graphics2D g2, GamePanel gameP) {
	
		int tileSize = gameP.tileSize;
		
		screenX = worldX - cam.cameraX;
		screenY = worldY - cam.cameraY;
		
		if (screenX + gameP.tileSize >= 0 && screenX - gameP.tileSize <= gameP.worldWidth &&
				screenY + gameP.tileSize >= 0 && screenY - gameP.tileSize <= gameP.worldHeight) {
			
			g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
		}
	}
}
