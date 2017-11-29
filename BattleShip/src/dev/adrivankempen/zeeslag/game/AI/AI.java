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
	private Tile returnTile;
	private int randomDirection;
	
	private int x, y, d;
	
	private Random random = new Random();
	
	public AI(Bord bord) {
		this.bord = bord;
	}
	
	/**Raad een random tegel*/
	public Tile randomTile() {
		x = random.nextInt(10);
		y = random.nextInt(10);
		
		returnTile = bord.getTiles()[x][y];
		
		return returnTile;
	}
	
	/**Raad een random plaats richting*/
	public int randomDirection() {
		d = random.nextInt(2);
		
		return d;
	}
	
	/**Bepaal de volgende aanval*/
	public void attackHit(Tile[] tiles) {
		randomDirection = random.nextInt(4);
		if(randomDirection == 0 && randomDirection == 2) {
			
		} else if(randomDirection == 1 && randomDirection == 3) {
			
		}
	}
}
