package main;

import entity.Entity;

public class Camera {
	
	public GamePanel gameP;
	public KeyHandler keyH;
	public Entity entity;
	
	public int cameraX, cameraY;
	
	public Camera(GamePanel gp) {
		this.gameP = gp;
	}
	
	
	public void followEntity(Entity entity) {
		
		cameraX = entity.worldX - (gameP.screenWidth / 2) + (gameP.tileSize / 2);;
		cameraY = entity.worldY - (gameP.screenHeight / 2) + (gameP.tileSize / 2);;
		
	}

}
