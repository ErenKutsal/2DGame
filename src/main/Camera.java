package main;

import java.awt.Rectangle;

import entity.Entity;

public class Camera {
	
	public GamePanel gameP;
	public KeyHandler keyH;
	public Entity entity;
	
	public int cameraX, cameraY;
	
	public Camera(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	
	public void followEntity(Entity entity) {
		
		cameraX = entity.worldX - (gameP.screenWidth / 2) + (gameP.tileSize / 2);
		cameraY = entity.worldY - (gameP.screenHeight / 2) + (gameP.tileSize / 2);
		
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
	
	public void followEntityInBox(Entity entity, Rectangle box) {
		
		
		if (entity.worldX - cameraX < box.x && !(cameraX < 0)) {
			cameraX = entity.worldX - box.x;
		}
		
		else if (entity.worldX - cameraX + 48 > box.x + box.width && !(cameraX > gameP.tileSize * gameP.tileM.mapTiles[0].length - gameP.screenWidth)) {
			cameraX = entity.worldX - box.x - box.width + 48;
		}
		
		if (entity.worldY - cameraY < box.y && !(cameraY < 0)) {
			cameraY = entity.worldY - box.y;
		}
		
		else if (entity.worldY - cameraY + 48 > box.y + box.height && !(cameraY > gameP.tileSize * gameP.tileM.mapTiles.length - gameP.screenHeight)) {
			cameraY = entity.worldY - box.y - box.height + 48;
		}
		
	}

}
