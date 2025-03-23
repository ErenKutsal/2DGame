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
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);
	
	Player player = new Player(this, keyH);
	//players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
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
		
		while (gameThread != null) {
			
			//System.out.println("aaa");
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta > drawInterval) {
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
