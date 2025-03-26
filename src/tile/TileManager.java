package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Camera;
import main.GamePanel;

public class TileManager {

	GamePanel gameP;
	public Camera cam;
	int TILES_LENGTH = 10;
	public Tile[][] mapTiles;
	
	public enum TILES {
		
		WATER("/tile/water.png"), WALL("/tile/wall.png"), GRASS("/tile/grass.png"), TREE("/tile/tree.png");
		
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
		String filePath = "/maps/island.txt";
		this.cam = cam;
		loadMap(filePath);
		System.out.println("bok");
		System.out.println("2D Oyuna Hoş Geldin! Blok yerleştirmeyi açma/kapama için 1'e, blok yerleştirmek için Boşluğa bas!");
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
						Tile grassTile = new Tile(TILES.GRASS.image, col*gameP.tileSize, row*gameP.tileSize);
						mapTiles[row][col] = grassTile;
						break;
					case "1":
						Tile waterTile = new Tile(TILES.WATER.image, col*gameP.tileSize, row*gameP.tileSize);
						waterTile.collision = true;
						mapTiles[row][col] = waterTile;
						break;
					case "2":
						Tile wallTile = new Tile(TILES.WALL.image, col*gameP.tileSize, row*gameP.tileSize);
						wallTile.collision = true;
						mapTiles[row][col] = wallTile;
						break;
					case "3":
						Tile treeTile = new Tile(TILES.TREE.image, col*gameP.tileSize, row*gameP.tileSize);
						treeTile.collision = true;
						mapTiles[row][col] = treeTile;
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
