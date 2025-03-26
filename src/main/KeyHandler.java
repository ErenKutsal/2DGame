package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public static boolean upPressed, downPressed, rightPressed, leftPressed, onePressed, spacePressed,
	upArrowPressed, downArrowPressed, rightArrowPressed, leftArrowPressed;
	public static boolean spaceJustPressed = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		
		if (code == KeyEvent.VK_W) { //if player pressed W
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) { //if player pressed S
			downPressed = true;
		}
		if (code == KeyEvent.VK_D) { //if player pressed D
			rightPressed = true;
		}
		if (code == KeyEvent.VK_A) { //if player pressed A
			leftPressed = true;
		}
		
		if (code == KeyEvent.VK_UP) { 
			upArrowPressed = true;
		}
		if (code == KeyEvent.VK_DOWN) { 
			downArrowPressed = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightArrowPressed = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftArrowPressed = true;
		}
		
		if (code == KeyEvent.VK_1) { //if player pressed 1
			onePressed = !onePressed;
		}
		if (code == KeyEvent.VK_SPACE && !spaceJustPressed) { //if player pressed SPACE
			System.out.println("boşluk aktif");
			spacePressed = true;
			spaceJustPressed = true;
		} else {
			spacePressed = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) { //if player pressed W
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) { //if player pressed S
			downPressed = false;
		}
		if (code == KeyEvent.VK_D) { //if player pressed D
			rightPressed = false;
		}
		if (code == KeyEvent.VK_A) { //if player pressed A
			leftPressed = false;
		}
		if (code == KeyEvent.VK_SPACE) { //if player pressed SPACE
			spaceJustPressed = false;
		}
		
		if (code == KeyEvent.VK_UP) { 
			upArrowPressed = false;
		}
		if (code == KeyEvent.VK_DOWN) { 
			downArrowPressed = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightArrowPressed = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftArrowPressed = false;
		}
	}

}
