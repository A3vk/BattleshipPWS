package dev.adrivankempen.zeeslag;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.adrivankempen.zeeslag.display.Display;
import dev.adrivankempen.zeeslag.gfx.Assets;
import dev.adrivankempen.zeeslag.input.MouseManager;
import dev.adrivankempen.zeeslag.states.GameState;
import dev.adrivankempen.zeeslag.states.SetupState;
import dev.adrivankempen.zeeslag.states.State;

public class Game implements Runnable{
	/*
	 * Display: 
	 * title: de titel van het scherm
	 * width, height: de lengte en hoogte van het scherm
	 * 
	 * running: controleert of dat het programma open staat
	 * thread: 
	 * 
	 *  bs: regelt het renderen van de game
	 *  g: regelt het renderen
	 *  
	 *  handler: algemenen variabelen en de inputs
	 *  
	 *  gameState: de state die de game zelf regelt
	 *  setupState: de state waarbij je het spel kan beginnen (en instellingen aan kan passen)
	 *  
	 *  mouseManager: regelt mouse input
	 */
	private Display display;
	private String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	private MouseManager mouseManager;
	
	private State gameState;
	@SuppressWarnings("unused")
	private State setupState;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		handler = new Handler(this);
		mouseManager = new MouseManager();
	}
	
	/**voegt de mouseManager toe aan het frame en de canvas,
	 * laad de Assets in, regelt de State*/
	private void init() {
		display = new Display(title, width, height);
		display.getJFrame().addMouseListener(handler.getMouseManager());
		display.getJFrame().addMouseMotionListener(handler.getMouseManager());
		display.getCanvas().addMouseListener(handler.getMouseManager());
		display.getCanvas().addMouseMotionListener(handler.getMouseManager());
		
		Assets.init();
		
		gameState = new GameState(handler);
		setupState = new SetupState(handler);
		State.setState(gameState);
	}
	
	/**hoofd tick methode*/
	private void tick() {
		//zorg dat de goede State de tick doorkrijgt
		if(State.getState() != null)
			State.getState().tick();
	}
	
	/**hoofd render methode*/
	private void render() {
		//maak een nieuwe bufferstrategy
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//Clear screen
		g.clearRect(0, 0, width, height);
		
		//zorg dat de goede State de render doorkrijgt
		if(State.getState() != null)
			State.getState().render(g);
		
		//ga naar het volgende frame
		bs.show();
		g.dispose();
	}
	
	/**regelt de game*/
	public void run() {
		init();
		
		//cap de fps bij 60 fps
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
//				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	/**regelt het correct opstarten van de game*/
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**regelt het correct afsluiten van de game*/
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
