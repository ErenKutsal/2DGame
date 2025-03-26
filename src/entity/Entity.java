package entity;

import java.awt.Rectangle;

public class Entity {

	public int worldX, worldY; //Entity's position on the world map
	public int speed;
	public float[] directionVector = new float[] {0, 0}; //changed to float array to prevent some errors
	
	public boolean isMoving;
	
	
	public String direction;
	
	public Rectangle solidArea; //the part of the entity that is capable of colliding
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn; //useless for now
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}