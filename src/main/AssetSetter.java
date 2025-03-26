package main;


import objects.*;

public class AssetSetter {

	GamePanel gameP;
	Camera cam;
	
	public AssetSetter(GamePanel gameP) {
		this.gameP = gameP;
		cam = gameP.cam;
	}
	
	public void setObject() {
		
		int tileSize = gameP.tileSize;
		
		OBJ_Key key = new OBJ_Key(cam);
		key.worldX = 23 * tileSize;
		key.worldY = 44 * tileSize;
		gameP.objects.add(key);
		
		OBJ_Door door = new OBJ_Door(cam);
		door.worldX = 25 * tileSize;
		door.worldY = 30 * tileSize;
		gameP.objects.add(door);
		
		OBJ_Chest chest = new OBJ_Chest(cam);
		chest.worldX = 26 * tileSize;
		chest.worldY = 33 * tileSize;
		gameP.objects.add(chest);
		
		
	}
}
