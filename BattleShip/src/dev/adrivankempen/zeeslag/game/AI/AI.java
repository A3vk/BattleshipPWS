package dev.adrivankempen.zeeslag.game.AI;

import java.util.Random;

import dev.adrivankempen.zeeslag.game.Tile;
import dev.adrivankempen.zeeslag.game.bord.Bord;

public class AI {
	/*
	 * bord: een instantie van het bord waarop op dit moment iets uitgevoerd wordt
	 * returnTile: de tegel die terug gegeven wordt als random Tile
	 * randomDirection: een random getal (0, 1, 2, 3) dat de volgende raad richting aangeeft
	 * 
	 * x: een random x coördinaat dat de returnTile bepaald
	 * y: een random y coördinaat dat de returnTile bepaald
	 * d: een random direction --> de richting waarin de boot geplaatst word
	 * 
	 * random: regelt de random getallen
	 */
	private Bord bord;
	private Tile currentTile;
	private Tile firstHit;
	private boolean afterHit = false;
	private boolean newDirection = true;
	private int attackDirection;
	private int currentIndex = 0;
	
	private Random random = new Random();
	
	public AI(Bord bord) {
		this.bord = bord;
	}
	
	public void attack() {
		try {
			if(firstHit.getIsSunken()) {
				afterHit = false;
				currentIndex = 0;
				newDirection = true;
			}
		} catch (NullPointerException e) {}
		
		if(!afterHit) {
			currentTile = randomAttackTile();
			if(currentTile.attack()) {
				firstHit = currentTile;
				currentIndex++;
				afterHit = true;
			}
		} else {
			if(newDirection) {
				attackDirection = newAttackDirection();
			}
			if(attackDirection == 0) {
				if(bord.translate(currentTile, -1, 0).attack()) {
					currentTile = bord.translate(currentTile, -1, 0);
					currentIndex++;
					newDirection = false;
				} else if(newDirection == false) {
					if(currentIndex < 0) {
						if(currentTile.getCoorX() == 0) {
							
						}
					}
					currentTile = firstHit;
					switchDirection();
				}
			} else if(attackDirection == 1) {
				if(bord.translate(currentTile, 0, 1).attack()) {
					currentTile = bord.translate(currentTile, 0, 1);
					currentIndex++;
					newDirection = false;
				} else if(newDirection == false) {
					currentTile = firstHit;
					switchDirection();
				}
			} else if(attackDirection == 2) {
				if(bord.translate(currentTile, 1, 0).attack()) {
					currentTile = bord.translate(currentTile, 1, 0);
					currentIndex++;
					newDirection = false;
				} else if(newDirection == false) {
					currentTile = firstHit;
					switchDirection();
				}
			} else if(attackDirection == 3) {
				if(bord.translate(currentTile, 0, -1).attack()) {
					currentTile = bord.translate(currentTile, 0, -1);
					currentIndex++;
					newDirection = false;
				} else if(newDirection == false) {
					currentTile = firstHit;
					switchDirection();
				}
			}
		}
		
		if(attackDirection == 0 && currentTile.getCoorX() == 0){
			switchDirection();
		} else if(attackDirection == 1 && currentTile.getCoorY() == 9) {
			switchDirection();
		} else if(attackDirection == 2 && currentTile.getCoorX() == 9) {
			switchDirection();
		} else if(attackDirection == 3 && currentTile.getCoorY() == 0) {
			switchDirection();
		}
	}
	
	private void switchDirection() {
		if(attackDirection == 0) {
			attackDirection = 2;
		} else if(attackDirection == 1) {
			attackDirection = 3;
		} else if(attackDirection == 2) {
			attackDirection = 0;
		} else if(attackDirection == 3) {
			attackDirection = 1;
		}
	}
	
	/**Raad een random tegel*/
	public Tile randomTile() {
		return bord.getTiles()[randomNumber(10)][randomNumber(10)];
	}
	
	public Tile randomAttackTile() {
		boolean b = true;
		Tile tile = randomTile();
		
		while(b) {
			tile = randomTile();
			
			if(tile.getCanGetShot() && !tile.getIsShot()) {
				b = false;
			}
		}
		
		return tile;
	}
	
	/**Raad een random plaats richting*/
	public int randomDirection() {
		return randomNumber(2);
	}
	
	private int randomNumber(int i) {
		int returnInt;
		
		returnInt = random.nextInt(i);
		
		return returnInt;
	}
	
	private int newAttackDirection() {
		boolean b = false;
		int i = 0;
		
		while(!b) {
			try {
				i = randomNumber(4);
				if(i == 0) {
					if(currentTile.getCoorX() != 0) {
						if(!bord.translate(currentTile, -1, 0).getIsShot()) {
							b = true;
						}
					}
				} else if(i == 1) {
					if(currentTile.getCoorY() != 10) {
						if(!bord.translate(currentTile, 0, 1).getIsShot()) {
							b = true;
						}
					}
				} else if(i == 2) {
					if(currentTile.getCoorX() != 10) {
						if(!bord.translate(currentTile, 1, 0).getIsShot()) {
							b = true;
						}
					}
				} else if(i == 3) {
					if(currentTile.getCoorY() != 0) {
						if(!bord.translate(currentTile, 0, -1).getIsShot()) {
							b = true;
						}
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		return i;
	}
}
