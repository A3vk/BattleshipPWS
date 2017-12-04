package dev.adrivankempen.zeeslag.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.adrivankempen.zeeslag.Handler;
import dev.adrivankempen.zeeslag.gfx.Assets;

public class Tile {	
	/*
	 * SIZE: de groote van de tegel
	 * RAND: de groote van de rand van het bord
	 * alphabet: de letters die gebruikt kunnen worden voor het Id
	 * 
	 * coorX, coorY: de x en y coördinaat van het bord
	 * x, y: de x en y coördinaat van de tegel
	 * rand: random getal generator
	 * 
	 * Id: het Id van de Tile (bv. A3, I8, E4)
	 * 
	 * piece: het soort schip-tegel dat op deze tegel is geplaatst
	 * 
	 * canPlace: kan er een schip geplaatst worden
	 * hasShip: staat er een schip op deze tegel
	 * clicked: is er op deze tegel geklikt
	 * isShot: is deze tegel aangevallen
	 * isSunken: staat er op deze tegel een gezonken boot
	 * 
	 * img: de afbeelding van de Tile
	 * tempImg: de tijdelijke afbeelding van de Tile
	 * 
	 * random: random getallen generator
	 * 
	 * handler: algemenen variabelen en inputs
	 */
	private final int SIZE = 40;
	private final int RAND = 20;
	private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	
	private int coorX, coorY;
	private int x, y;
	private int rand;
	
	private String Id;
	
	//E = leeg
	private char piece = 'E';
	
	private boolean canPlace = true;
	private boolean canGetShot = true;
	private boolean hasShip = false;
	private boolean clicked = false;
	private boolean isShot = false;
	private boolean isSunken = false;
	private boolean isUpdated = false;
	
	private BufferedImage img = Assets.leeg;
	private BufferedImage tempImg = img;
	
	private Random random = new Random();
	
	private Handler handler;
	
	/**maak en plaats de tegel*/
	public Tile(int x, int y, int startX, int startY , Handler handler) {
		coorX = x;
		coorY = y;
		
		this.x = RAND + SIZE * x + startX;
		this.y = RAND + SIZE * y + startY;
		
		this.handler = handler;
		
		Id = createId();
	}
	
	/**render de tegel*/
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	/**is de muis op deze tegel*/
	public String hover() {
		//controleer ofdat de coördinaten van de muis binnen de coördinaten van de tegel zijn
		if(handler.getMouseManager().getMouseX() >= x &&
				handler.getMouseManager().getMouseX() <= x + SIZE &&
				handler.getMouseManager().getMouseY() >= y &&
				handler.getMouseManager().getMouseY() <= y + SIZE) {
			return Id;
		}
		return null;
	}

	/**val de tegel aan*/
	public boolean attack() {
		//controleer ofdat de tegel niet al eerder is aangevallen
		if(!isShot && !isSunken && canGetShot) {
			//controleer ofdat er een schip staat
			if(hasShip) {
				setImg(Assets.hit);
				return true;
			} else {
				//creëer een random getal
				rand = random.nextInt(3) + 1;
				//verander de afbeelding
				if(rand == 1) {
					setImg(Assets.mis1);
				} else if(rand == 2) {
					setImg(Assets.mis2);
				} else if(rand == 3) {
					setImg(Assets.mis3);
				}
			}
			isShot = true;
		}
		return false;
	}
	
	/**maak het Id van de tegel*/
	private String createId() {
		String Id1;
		String Id2;
		
		Id1 = alphabet[coorX];
		Id2 = Integer.toString(coorY + 1);
		
		return (Id1 + Id2);
	}
	
	/**verander de afbeelding van de tegel*/
	public void setImg(BufferedImage img) {
		this.img = img;
		setTempImg(img);
	}
	
	public void setIsUpdated() {
		isUpdated = true;
	}
	
	public boolean getIsUpdated() {
		return isUpdated;
	}
	
	public void setCanGetShot() {
		canGetShot = false;
	}
	
	public boolean getCanGetShot() {
		return canGetShot;
	}
	
	public void setIsSunken() {
		isSunken = true;
	}
	
	public boolean getIsSunken() {
		return isSunken;
	}
	
	public void setPiece(char p) {
		piece = p;
	}
	
	public char getPiece() {
		return piece;
	}
	
	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}	
	
	public int getCoorX() {
		return coorX;
	}
	
	public int getCoorY() {
		return coorY;
	}
	
	public String getId() {
		return Id;
	}
	
	public BufferedImage getImg() {
		return img;
	}

	public boolean getCanPlace() {
		return canPlace;
	}

	public void setCanPlace(boolean b) {
		canPlace = b;
	}
	
	public boolean getHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean b) {
		hasShip = b;
	}
	
	public boolean getIsShot() {
		return isShot;
	}

	public void setIsShot(boolean b) {
		isShot = b;
	}

	public BufferedImage getTempImg() {
		return tempImg;
	}

	public void setTempImg(BufferedImage tempImg) {
		this.tempImg = tempImg;
	}
}
