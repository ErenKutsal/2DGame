package main;

import java.awt.Rectangle;

import entity.Entity;

public class Camera {
	
	public GamePanel gameP;
	public Entity entity;
	
	public int cameraX, cameraY;
	
	public Camera(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public void dontGoOutOfMap() {
		
		if (cameraX < 0) {
			cameraX = 0;
		}
		
		else if (cameraX > gameP.tileSize * gameP.tileM.mapTiles[0].length - gameP.screenWidth) {
			cameraX = gameP.tileSize * gameP.tileM.mapTiles[0].length - gameP.screenWidth;
		}
		
		if (cameraY < 0) {
			cameraY = 0;
		}
		
		else if (cameraY > gameP.tileSize * gameP.tileM.mapTiles.length - gameP.screenHeight) {
			cameraY = gameP.tileSize * gameP.tileM.mapTiles.length - gameP.screenHeight;
		}
		
	}
	
	
	public void followEntity(Entity entity) {
		
		cameraX = entity.worldX - (gameP.screenWidth / 2) + (gameP.tileSize / 2);
		cameraY = entity.worldY - (gameP.screenHeight / 2) + (gameP.tileSize / 2);
		
		dontGoOutOfMap();
	}
	
	public void followEntityInBox(Entity entity, Rectangle box) {
		
		if (entity.worldX - cameraX < box.x) {
			cameraX = entity.worldX - box.x;
		}
		
		else if (entity.worldX - cameraX + 48 > box.x + box.width) {
			cameraX = entity.worldX - box.x - box.width + 48;
		}
		
		if (entity.worldY - cameraY < box.y) {
			cameraY = entity.worldY - box.y;
		}
		
		else if (entity.worldY - cameraY + 48 > box.y + box.height) {
			cameraY = entity.worldY - box.y - box.height + 48;
		}
		
		if (cameraX + 24 < entity.worldX - (gameP.screenWidth / 2) + (gameP.tileSize / 2)) {
			cameraX += entity.speed/2;
		} else if (cameraX > entity.worldX - (gameP.screenWidth / 2) + (gameP.tileSize / 2) + 24) {
			cameraX -= entity.speed/2;
		}
		if (cameraY + 24 < entity.worldY - (gameP.screenHeight / 2) + (gameP.tileSize / 2)) {
			cameraY += entity.speed/2;
		} else if (cameraY > entity.worldY - (gameP.screenHeight / 2) + (gameP.tileSize / 2) + 24) {
			cameraY -= entity.speed/2;
		}
		
		dontGoOutOfMap();
		
	}
	
	public void followArrowKeys(KeyHandler keyH, int speed) {
		
		if (KeyHandler.upArrowPressed) {
	       cameraY -= speed;
	    }
	    if (KeyHandler.downArrowPressed) {
	    	cameraY += speed;
	    }
	    if (KeyHandler.rightArrowPressed) {
	        cameraX += speed;
	    }
	    if (KeyHandler.leftArrowPressed) {
	        cameraX -= speed;
	    }
	    System.out.println(cameraX);
	    System.out.println(cameraY);
	    
	    dontGoOutOfMap();
	}
	

}
