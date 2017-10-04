package dev.adrivankempen.zeeslag.game.bord;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.game.ship.Fregat;
import dev.adrivankempen.zeeslag.game.ship.Kruiser;
import dev.adrivankempen.zeeslag.game.ship.Mijnveger;
import dev.adrivankempen.zeeslag.game.ship.Slagschip;

public class SpelerBord extends Bord {	
	private Slagschip slagschip;
	private Kruiser kruiser;
	private Fregat fregat1, fregat2;
	private Mijnveger mijnveger;
	
	private int currentIndex = 0;
	private int direction = 0;
	
	private Handler handler;
	
	public SpelerBord(int x, int y, Handler handler) {
		super(x, y, handler);
		
		slagschip = new Slagschip(0, -1, -1, this);
		kruiser = new Kruiser(0, -1, -1, this);
		fregat1 = new Fregat(0, -1, -1, this);
		fregat2 = new Fregat(0, -1, -1, this);
		mijnveger = new Mijnveger(0, -1, -1, this);
		
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
		preview();
	}
	
	private void place() {
		if(click() != null) {
			if(currentIndex == 0){
				if(slagschip.checkPosition(direction, 5, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					slagschip = new Slagschip(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else if(currentIndex == 1) {
				if(kruiser.checkPosition(direction, 4, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					kruiser = new Kruiser(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else if(currentIndex == 2) {
				if(fregat1.checkPosition(direction, 3, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					fregat1 = new Fregat(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else if(currentIndex == 3) {
				if(fregat2.checkPosition(direction, 3, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					fregat2 = new Fregat(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
				}
			} else if(currentIndex == 4) {
				if(mijnveger.checkPosition(direction, 2, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this)) {
					mijnveger = new Mijnveger(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY(), this);
					currentIndex++;
					System.out.println("ALL PLACED");
				}
			}
		}
	}
	
	private void preview() {
		try {
			if(currentIndex == 0){
				slagschip.preview(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY());
			} else if(currentIndex == 1) {
				kruiser.preview(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY());
			} else if(currentIndex == 2) {
				fregat1.preview(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY());
			} else if(currentIndex == 3) {
				fregat2.preview(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY());
			} else if(currentIndex == 4) {
				mijnveger.preview(direction, getTile(click()).getCoorX(), getTile(click()).getCoorY());
			}
		} catch(NullPointerException e){}
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