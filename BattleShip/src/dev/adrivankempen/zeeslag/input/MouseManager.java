package dev.adrivankempen.zeeslag.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
	private int mouseX, mouseY;
	private boolean leftPressed, rightPressed;
	
	public boolean getLeftPressed() {
		return leftPressed;
	}
	
	public void setLeftPressed() {
		this.leftPressed = false;
	}
	
	public boolean getRightPressed() {
		return rightPressed;
	}
	
	public void setRightPressed() {
		this.rightPressed = false;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}

	@Override
	//controleer ofdat de linker of rechter muisknop ingedrukt wordt
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	@Override
	//controleer ofdat de linker of rechter muisknop los gelaten wordt
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}

	@Override
	//update de x en y positie van de muis bij elke beweging van de muis
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	//standaard functies die nodig zijn voor een MouseManager
	@Override
	public void mouseDragged(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
