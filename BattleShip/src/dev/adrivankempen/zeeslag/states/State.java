package dev.adrivankempen.zeeslag.states;

import java.awt.Graphics;

import dev.adrivankempen.zeeslag.Handler;

/**de State class houd bij welke state er op dit moment actief is en zorgt dat die state een tick en een render krijgen*/
public abstract class State {
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
