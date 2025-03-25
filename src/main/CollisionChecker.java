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
		
		float[] directionVector = entity.directionVector;
		
		// Check horizontal movement (left/right)
		if (directionVector[0] != 0) {
		    int newCol = (directionVector[0] > 0) 
		        ? (entitySolidWorldRightX + speed) / tileSize  // Moving right
		        : (entitySolidWorldLeftX - speed) / tileSize;  // Moving left
		    
		    Tile topTile = tiles[entityTopRow][newCol];
		    Tile bottomTile = tiles[entityBottomRow][newCol];
		    
		    if ((topTile.collision || bottomTile.collision)) {
		        directionVector[0] = 0;
		    }
		}

		// Check vertical movement (up/down)
		if (directionVector[1] != 0) {
		    int newRow = (directionVector[1] > 0) 
		        ? (entitySolidWorldBottomY + speed) / tileSize  // Moving down
		        : (entitySolidWorldTopY - speed) / tileSize;   // Moving up
		    
		    Tile leftTile = tiles[newRow][entityLeftCol];
		    Tile rightTile = tiles[newRow][entityRightCol];
		    
		    if ((leftTile.collision || rightTile.collision)) {
		        directionVector[1] = 0;
		    }
		}
	}
	
}
