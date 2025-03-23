package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, rightPressed, leftPressed;
	
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
	}

}
