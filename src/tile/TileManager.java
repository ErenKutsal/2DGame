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

import main.GamePanel;

public class TileManager {

	GamePanel gameP;
	Tile[] tiles;
	int TILES_LENGTH = 10;
	int[][] mapTiles;
	
	
	public TileManager(GamePanel gameP) {
		
		this.gameP = gameP;
		tiles = new Tile[TILES_LENGTH];
		mapTiles = new int[gameP.maxScreenRow][gameP.maxScreenCol];
		
		getTileImage();
		parseFile();
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
	
	public void parseFile() {
		
		try {
			InputStream inputStream = getClass().getResourceAsStream("/maps/mapData.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
			int row = 0;
			int col = 0;
			while ((line = br.readLine()) != null) {
				String[] contents = line.strip().split(" ");
				for (String content : contents) {
					System.out.println(content);
				}
				for (String content : contents) {
					mapTiles[row][col] = Integer.valueOf(content);
					col++;
				}
				col = 0;
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		
		int x = 0;
		int y = 0;
		int tileSize = gameP.tileSize;
		for (int row = 0; row < gameP.maxScreenRow; row++) {
			
			for (int col = 0; col < gameP.maxScreenCol; col++) {
				int tileType = mapTiles[row][col];
				//System.out.println(tileType);
				Tile tile = tiles[tileType];
				g2.drawImage(tile.image, x, y, tileSize, tileSize, null);
				x += tileSize;
			}
			y += tileSize;
			x = 0;
		}
	}
}
