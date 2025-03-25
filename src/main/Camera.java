package main;

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

}
