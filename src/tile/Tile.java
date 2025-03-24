package tile;

import java.awt.image.BufferedImage;

public class Tile {

	public int worldX, worldY;
	public BufferedImage image;
	public boolean collision = false;
	
	public Tile(BufferedImage image, int x, int y) {
		this.image = image;
		worldX = x;
		worldY = y;
	}
}
