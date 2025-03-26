package main;

import java.util.ArrayList;

import objects.*;

public class AssetSetter {

	GamePanel gameP;
	Camera cam;
	
	public AssetSetter(GamePanel gameP) {
		this.gameP = gameP;
		ArrayList<SuperObject> objects = gameP.objects;
		cam = gameP.cam;
	}
	
	public void setObject() {
		
		int tileSize = gameP.tileSize;
		
		OBJ_Key key = new OBJ_Key(cam);
		key.worldX = 20 * tileSize;
		key.worldY = 5 * tileSize;
		gameP.objects.add(key);
		
		OBJ_Door door = new OBJ_Door(cam);
		door.worldX = 40 * tileSize;
		door.worldY = 10 * tileSize;
		gameP.objects.add(door);
		
		OBJ_Chest chest = new OBJ_Chest(cam);
		chest.worldX = 40 * tileSize;
		chest.worldY = 15 * tileSize;
		gameP.objects.add(chest);
		
		
	}
}
