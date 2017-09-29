package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.ship.Fregat;
import dev.adrivankempen.zeeslag.game.ship.Kruiser;
import dev.adrivankempen.zeeslag.game.ship.Mijnveger;
import dev.adrivankempen.zeeslag.game.ship.Ship;
import dev.adrivankempen.zeeslag.game.ship.Slagschip;

public class SpelerBord extends Bord {
	//Ship to call the check code: LOOK FOR FIX
	private final Ship ship = new Ship(0, 0, 0, 0, this);
	
	private Slagschip slagschip;
	private Kruiser kruiser;
	private Fregat fregat1, fregat2;
	private Mijnveger mijnveger;
	
	private int currentIndex = 0;
	private int direction = 0;
	
	private Handler handler;
	
	public SpelerBord(int x, int y, Handler handler) {
		super(x, y, handler);
		
		this.handler = handler;
	}
	
	@Override
	public void tick() {
		if(handler.getMouseManager().getRightPressed()) {
			handler.getMouseManager().setRightPressed();
			if(direction == 0) {
				direction = 1;
			}
			else {
				direction = 0;
			}
		} else if(handler.getMouseManager().getLeftPressed()) {
			handler.getMouseManager().setLeftPressed();
			place();
		}
	}
	
	private void place() {
		if(click() != null) {
			if(currentIndex == 0){
				if(ship.checkPosition(direction, 5, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					slagschip = new Slagschip(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					System.out.println("PLACED: " + currentIndex);
					currentIndex++;
				}
			} else if(currentIndex == 1) {
				if(ship.checkPosition(direction, 4, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					kruiser = new Kruiser(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else if(currentIndex == 2) {
				if(ship.checkPosition(direction, 3, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					fregat1 = new Fregat(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else if(currentIndex == 3) {
				if(ship.checkPosition(direction, 3, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					fregat2 = new Fregat(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else if(currentIndex == 4) {
				if(ship.checkPosition(direction, 2, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					mijnveger = new Mijnveger(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else {
				System.out.println("ALL PLACED");
			}
		}
	}

	//GETTERS EN SETTERS
	public Slagschip getSlagschip() {
		return slagschip;
	}

	public void setSlagschip(Slagschip slagschip) {
		this.slagschip = slagschip;
	}

	public Kruiser getKruiser() {
		return kruiser;
	}

	public void setKruiser(Kruiser kruiser) {
		this.kruiser = kruiser;
	}

	public Fregat getFregat1() {
		return fregat1;
	}

	public void setFregat1(Fregat fregat1) {
		this.fregat1 = fregat1;
	}

	public Fregat getFregat2() {
		return fregat2;
	}

	public void setFregat2(Fregat fregat2) {
		this.fregat2 = fregat2;
	}

	public Mijnveger getMijnveger() {
		return mijnveger;
	}

	public void setMijnveger(Mijnveger mijnveger) {
		this.mijnveger = mijnveger;
	}
}
