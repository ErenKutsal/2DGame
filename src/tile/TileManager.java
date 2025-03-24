package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import entity.Player;
import main.Camera;
import main.GamePanel;

public class TileManager {

	GamePanel gameP;
	public Camera cam;
	int TILES_LENGTH = 10;
	Tile[][] mapTiles;
	
	public enum TILES {
		
		WATER("/tile/water.png"), WALL("/tile/wall.png"), GRASS("/tile/grass.png");
		
		public BufferedImage image;
		
		TILES(String path) {
			
			try {
				image = ImageIO.read(getClass().getResourceAsStream(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public TileManager(GamePanel gameP, Camera cam) {
		
		this.gameP = gameP;
		mapTiles = new Tile[gameP.maxWorldRow][gameP.maxWorldCol];
		String filePath = "/maps/worldMap";
		this.cam = cam;
		loadMap(filePath);
		System.out.println("bok");
	}
	
	public void loadMap(String filePath) {
		
		try {
			InputStream inputStream = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
			int row = 0;
			int col = 0;
			
			while (row < gameP.maxWorldRow && col < gameP.maxWorldCol) {
				line = br.readLine();
				String[] contents = line.strip().split(" ");
				
				for (String content : contents) {
					
					switch (content) {
					case "0":
						mapTiles[row][col] = new Tile(TILES.GRASS.image, col*gameP.tileSize, row*gameP.tileSize);
						break;
					case "1":
						mapTiles[row][col] = new Tile(TILES.WATER.image, col*gameP.tileSize, row*gameP.tileSize);
						break;
					case "2":
						mapTiles[row][col] = new Tile(TILES.WALL.image, col*gameP.tileSize, row*gameP.tileSize);
						break;
					
					}
					++col;
				}
				col = 0;
				row++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	public void draw(Graphics2D g2) {
		
		int tileSize = gameP.tileSize;
		Player player = gameP.player;
		
		for (int row = 0; row < gameP.maxWorldRow; row++) {
			
			for (int col = 0; col < gameP.maxWorldCol; col++) {
				
				Tile tile = mapTiles[row][col];
				
				int screenX = tile.worldX - cam.cameraX;
				int screenY = tile.worldY - cam.cameraY;
				
				if (screenX + gameP.tileSize >= 0 && screenX - gameP.tileSize <= gameP.worldWidth &&
						screenY + gameP.tileSize >= 0 && screenY - gameP.tileSize <= gameP.worldHeight)
				g2.drawImage(tile.image, screenX, screenY, tileSize, tileSize, null);
				
			}
		}
	}
}
