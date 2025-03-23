package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class TileManager {

	GamePanel gameP;
	Tile[] tiles;
	int TILES_LENGTH = 10;
	int[][] mapTiles;
	
	
	public TileManager(GamePanel gameP) {
		
		this.gameP = gameP;
		tiles = new Tile[TILES_LENGTH];
		mapTiles = new int[gameP.maxWorldRow][gameP.maxWorldCol];
		String filePath = "/maps/worldMap";
		
		getTileImage();
		loadMap(filePath);
	}
	
	public void getTileImage() {
		
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				/*
				for (String content : contents) {
					System.out.println(content);
				}
				*/
				
				for (String content : contents) {
					mapTiles[row][col] = Integer.valueOf(content);
					col++;
				}
				col = 0;
				row++;
			}
			br.close();
			for (int[] array : mapTiles) {
				for (int value : array) {
					System.out.print(value);
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public void draw(Graphics2D g2) {
		
		int tileSize = gameP.tileSize;
		Player player = gameP.player;
		
		for (int row = 0; row < gameP.maxWorldRow; row++) {
			
			for (int col = 0; col < gameP.maxWorldCol; col++) {
				
				int tileType = mapTiles[row][col];
				Tile tile = tiles[tileType];
				
				int worldX = row * tileSize;
				int worldY = col * tileSize;
				int screenX = worldX - player.worldX + player.screenX;
				int screenY = worldY - player.worldY + player.screenY;
				
				
				//only render required tiles
				if (screenX + gameP.tileSize >= 0 && screenX - gameP.tileSize <= gameP.worldWidth &&
						screenY + gameP.tileSize >= 0 && screenY - gameP.tileSize <= gameP.worldHeight)
				g2.drawImage(tile.image, screenX, screenY, tileSize, tileSize, null);
				
			}
		}
	}
}
