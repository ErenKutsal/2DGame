package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Currency;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768px
	public final int screenHeight = tileSize * maxScreenRow; //576px
	
	int FPS = 60;
	
	Camera cam = new Camera(this);
	
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this, cam);

	
	public Player player = new Player(this, keyH, cam);
	//players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	//World Settings
	public final int maxWorldRow = 50;
	public final int maxWorldCol = 50;
	public final int worldHeight = maxWorldCol * tileSize;
	public final int worldWidth = maxWorldRow * tileSize;
	
	public GamePanel() {
		
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		addKeyListener(keyH);
		setFocusable(true);
	}

	public void startGame() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //0.017 sec 
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		int count = 0;
		long fpsTime = System.nanoTime();
		long fpsTime2 = System.nanoTime();
		
		while (gameThread != null) {
			
			
			//System.out.println("aaa");
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime);
			fpsTime2 += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if (fpsTime2 - fpsTime > 1000000000) {
				System.out.println("FPS: " + count);
				fpsTime = System.nanoTime();
				fpsTime2 = System.nanoTime();
				count = 0;
			}
			
			if (delta > drawInterval) {
				
				count++;
				//Update
				update();
				//Draw
				repaint();
				
				delta -= drawInterval;
			}
		}
	}
	
	public void update() {
		
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		
		
		g2.dispose();
	}
	
	

}
