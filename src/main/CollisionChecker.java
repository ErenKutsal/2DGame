package main;

import java.awt.Rectangle;

import entity.*;
import tile.Tile;

public class CollisionChecker {

	public GamePanel gameP;
	
	public CollisionChecker(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public void checkTile(Entity entity) {
		 
		Rectangle solidArea = entity.solidArea;
		int tileSize = gameP.tileSize;
		int speed = entity.speed;
		Tile[][] tiles = gameP.tileM.mapTiles;
		
		//Entity hitbox coordinates
		int entitySolidWorldLeftX = entity.worldX + solidArea.x;
		int entitySolidWorldRightX = entity.worldX + solidArea.x + solidArea.width;
		int entitySolidWorldTopY = entity.worldY + solidArea.y;
		int entitySolidWorldBottomY = entity.worldY + solidArea.y + solidArea.height;
		
		int entityLeftCol = entitySolidWorldLeftX / tileSize;
		int entityRightCol = entitySolidWorldRightX / tileSize;
		int entityBottomRow = entitySolidWorldBottomY / tileSize;
		int entityTopRow = entitySolidWorldTopY / tileSize;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entitySolidWorldTopY - speed)/ tileSize;
			Tile topLeftTile = tiles[entityTopRow][entityLeftCol];
			Tile topRightTile = tiles[entityTopRow][entityRightCol];
			
			if (topLeftTile.collision || topRightTile.collision) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entitySolidWorldBottomY - speed)/ tileSize;
			Tile bottomLeftTile = tiles[entityBottomRow][entityLeftCol];
			Tile bottomRightTile = tiles[entityBottomRow][entityRightCol];
			
			if (bottomLeftTile.collision || bottomRightTile.collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entitySolidWorldRightX - speed)/ tileSize;
			Tile rightTopTile = tiles[entityTopRow][entityRightCol];
			Tile rightBottomTile = tiles[entityBottomRow][entityRightCol];
			
			if (rightTopTile.collision || rightBottomTile.collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entitySolidWorldLeftX - speed)/ tileSize;
			Tile leftTopTile = tiles[entityTopRow][entityLeftCol];
			Tile leftBottomTile = tiles[entityBottomRow][entityLeftCol];
			
			if (leftTopTile.collision || leftBottomTile.collision) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
}
