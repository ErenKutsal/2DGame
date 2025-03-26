package main;

import java.awt.Rectangle;

import entity.*;
import objects.OBJ_Key;
import objects.SuperObject;
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
		    	System.out.println("carpıs");
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
		    	System.out.println("asdd");
		        directionVector[1] = 0;
		    }
		}
	}
	
	public int checkObject(Entity entity) {
		//checks for collision, returns object index if entity is a player.
		
		boolean isPlayer = false;
		int objectIndex = -1;
		int index = 0;
		
		if (entity instanceof Player) {
			isPlayer = true;
		}
		for (SuperObject object : gameP.objects) {
			
			if (object == null) {
				index++;
				continue;
			}
			//Player's solid area coordinates
			entity.solidArea.x += entity.worldX;
			entity.solidArea.y += entity.worldY;
			//Object's solid area coordinates
			object.solidArea.x += object.worldX;
			object.solidArea.y += object.worldY;
			if (object instanceof OBJ_Key) {
				
				System.out.print(entity.solidArea.x + " " + object.solidArea.x);
				System.out.println();
				System.out.println(entity.solidArea.y + " " + object.solidArea.y);
				System.out.println();
			}
			
			int speed = entity.speed;
			float[] directionVector = entity.directionVector;
			
			// Check horizontal movement (left/right)
			if (directionVector[0] != 0) {
				entity.solidArea.x = (directionVector[0] > 0) 
						? (entity.solidArea.x + speed)  // Moving right
						: (entity.solidArea.x - speed);  // Moving left
				if (entity.solidArea.intersects(object.solidArea)) {
					System.out.println("çarpışma");
					if (object.collision) {
						directionVector[0] = 0;
					}
					if (isPlayer) {
						objectIndex = index;
					}
				}
			}
			
			// Check vertical movement (up/down)
			if (directionVector[1] != 0) {
				entity.solidArea.y = (directionVector[1] > 0) 
						? (entity.solidArea.y + speed)  // Moving down
						: (entity.solidArea.y - speed); // Moving up
				if (entity.solidArea.intersects(object.solidArea)) {
					System.out.println("çarpışma");
					if (object.collision) {
						directionVector[1] = 0;
					}
					if (isPlayer) {
						objectIndex = index;
					}
				}
				
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			object.solidArea.x = object.solidAreaDefaultX;
			object.solidArea.y = object.solidAreaDefaultY;
			
			index++;
		}
		
		return objectIndex;
	}
	
}
