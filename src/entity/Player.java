package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Camera;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import tile.Tile;
import tile.TileManager;
import tile.TileManager.TILES;

public class Player extends Entity{

	public GamePanel gameP;
	public KeyHandler keyH;
	public Camera cam;
	public TileManager tileM;
	
	public int playerX, playerY; //Coordinates to draw the player.
	
	
	public Player(GamePanel gameP, KeyHandler keyH, Camera cam, TileManager tileM) {
		
		this.gameP = gameP;
		this.keyH = keyH;
		this.cam = cam;
		this.tileM = tileM;
		
		setDefaultValues();
		getPlayerImage();
		
		solidArea = new Rectangle();
		solidArea.x = 20;
		solidArea.y = 36;
		solidArea.width = 8;
		solidArea.height = 12;
		
	}
	
	public void setDefaultValues() {
		worldX = 200;
		worldY = 200;
		speed = 4;
		
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player2.png"));
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void update() {
	    directionVector[0] = 0;
	    directionVector[1] = 0;
	    isMoving = false;

	    if (keyH.upPressed) {
	        direction = "up";
	        directionVector[1] = -1;
	        isMoving = true;
	    }
	    if (keyH.downPressed) {
	        direction = "down";
	        directionVector[1] = 1;
	        isMoving = true;
	    }
	    if (keyH.rightPressed) {
	        direction = "right";
	        directionVector[0] = 1;
	        isMoving = true;
	    }
	    if (keyH.leftPressed) {
	        direction = "left";
	        directionVector[0] = -1;
	        isMoving = true;
	    }
	    
	    CollisionChecker collisionChecker = gameP.collisionChecker;
	    collisionChecker.checkTile(this);
	    

	    // Normalize direction vector
	    
	    double magnitude = Math.sqrt(directionVector[0] * directionVector[0] + directionVector[1] * directionVector[1]);
	    
	    if (magnitude != 0) {
	        
	    	directionVector[0] /= magnitude;
	        directionVector[1] /= magnitude;
	        
	       
	        
	        worldX += Math.round((float)directionVector[0] * speed);
	        worldY += Math.round((float)directionVector[1] * speed);
	        

	        // Update sprite animation
	        spriteCounter++;
	        if (spriteCounter > 10) {
	            spriteNum = (spriteNum == 1) ? 2 : 1;
	            spriteCounter = 0;
	        }
	    }
	}

	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			} else if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			} else if (spriteNum == 2) {
				image = down2;
			}
			break;
			
		case "right":
			if (spriteNum == 1) {
				image = right1;
			} else if (spriteNum == 2) {
				image = right2;
			}
			break;
			
		case "left":
			if (spriteNum == 1) {
				image = left1;
			} else if (spriteNum == 2) {
				image = left2;
			}
			break;
		}
		
		int screenX = worldX - cam.cameraX;
		int screenY = worldY - cam.cameraY;
		
		if (keyH.onePressed) {
			displayBox(g2);
		}
		g2.drawImage(image, screenX, screenY, gameP.tileSize, gameP.tileSize, null);
		
	}
	
	public void displayBox(Graphics2D g2) {
		
		int tileSize = gameP.tileSize;
		
		int playerWorldRow = (worldY + (tileSize / 2)) / tileSize;
		int playerWorldCol = (worldX + (tileSize/2)) / tileSize;
		
		int newWorldCol = 0; 
		int newWorldRow = 0;
		
		switch (direction) {
		case "up":
			newWorldCol = playerWorldCol;
			newWorldRow = playerWorldRow - 1;
			break;
		case "down":
			newWorldCol = playerWorldCol;
			newWorldRow = playerWorldRow + 1;
			break;
		case "right":
			newWorldCol = playerWorldCol + 1;
			newWorldRow = playerWorldRow;
			break;
		case "left":
			newWorldCol = playerWorldCol - 1;
			newWorldRow = playerWorldRow;
			break;
		}
		

		if (directionVector[0] != 0) {
			newWorldCol = (directionVector[0] > 0) 
			        ? (playerWorldCol + 1) // Moving right
			        : (playerWorldCol - 1); // Moving left
			
		}
		if (directionVector[1] != 0) {
		    newWorldRow = (directionVector[1] > 0) 
		        ? (playerWorldRow + 1) // Moving down
		        : (playerWorldRow - 1); // Moving up
		    
		}

		Tile oldTile = tileM.mapTiles[newWorldRow][newWorldCol];
		
		int tileScreenX = oldTile.worldX - cam.cameraX;
		int tileScreenY = oldTile.worldY - cam.cameraY;
		if (keyH.spacePressed) {
			Tile newTile = new Tile(TILES.WALL.image, newWorldCol*gameP.tileSize, newWorldRow*gameP.tileSize);
			newTile.collision = true;
			tileM.mapTiles[newWorldRow][newWorldCol] = newTile;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // %50 şeffaflık
		
		g2.drawImage(TILES.WALL.image, tileScreenX, tileScreenY, tileSize, tileSize, null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Opaklığa geri dön

	}
	
}
