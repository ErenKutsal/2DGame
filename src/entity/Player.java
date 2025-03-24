package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Camera;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	public GamePanel gameP;
	public KeyHandler keyH;
	public Camera cam;
	
	public int playerX, playerY; //Coordinates to draw the player.
	
	public Player(GamePanel gameP, KeyHandler keyH, Camera cam) {
		
		this.gameP = gameP;
		this.keyH = keyH;
		this.cam = cam;
		
		setDefaultValues();
		getPlayerImage();
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
	}
	
	public void setDefaultValues() {
		worldX = (gameP.worldWidth / 2) - (gameP.tileSize / 2);
		worldY = (gameP.worldHeight / 2) - (gameP.tileSize / 2);
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

	    if (keyH.upPressed) {
	        direction = "up";
	        directionVector[1] = -1;
	    }
	    if (keyH.downPressed) {
	        direction = "down";
	        directionVector[1] = 1;
	    }
	    if (keyH.rightPressed) {
	        direction = "right";
	        directionVector[0] = 1;
	    }
	    if (keyH.leftPressed) {
	        direction = "left";
	        directionVector[0] = -1;
	    }
	    
	    CollisionChecker collisionChecker = gameP.collisionChecker;
	    collisionOn = false;
	    collisionChecker.checkTile(this);
	    
	    if (collisionOn) {
	    	return;
	    }
	    // Normalize direction vector
	    double magnitude = Math.sqrt(directionVector[0] * directionVector[0] + directionVector[1] * directionVector[1]);
	    if (magnitude != 0) {
	        
	    	directionVector[0] /= magnitude;
	        directionVector[1] /= magnitude;
	        
	        
	        worldX += Math.round((float)directionVector[0] * speed);
	        worldY += Math.round((float)directionVector[1] * speed);
	        
	        //update the hitbox coordinates
//	        solidArea.x = worldX + 8;
//	        solidArea.y = worldY + 16;
	        
	       
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
		System.out.println(cam.cameraX);
		g2.drawImage(image, screenX, screenY, gameP.tileSize, gameP.tileSize, null);
		
	}
	
}
