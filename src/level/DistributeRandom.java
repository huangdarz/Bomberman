package level;

import java.util.LinkedList;
import java.util.Random;

public class DistributeRandom {
	private Random rand;
	private int randAmount;
	
	public int
		amount,
		randomLocationX,
		randomLocationY,
		currentAmount,
		existingLocations[][];
	
	private LinkedList<Location> locations;
	
	public DistributeRandom(Random random, int min, int max) {
		this.rand = random;
		this.randAmount = max - min + 1;
		currentAmount = 0;
		amount = rand.nextInt(randAmount) + min;
		existingLocations = new int[amount+1][2];
		locations = new LinkedList<Location>();
	}
	
	private void locationGeneration() {
		randomLocationX = rand.nextInt(13);
		randomLocationY = rand.nextInt(11);
	}

	private void checkLocationFree() {
		while (randomLocationX % 2 != 0 && randomLocationY % 2 != 0) {
			locationGeneration();
		}
	}
	
	private void amountAndLocation() {
		for (int i = 0; i <= amount; i++) {
			locationGeneration();
			
			checkLocationFree();
			
			while (checkPreviousLocations(i) == true) {
				locationGeneration();
				
				checkLocationFree();
			}
			existingLocations[i][0] = randomLocationX;
			existingLocations[i][1] = randomLocationY;
			
			locations.add(new Location(randomLocationX, randomLocationY));
			
		}
	}
	
	private boolean checkPreviousLocations(int i) {
		if (i != 0) {
			if (randomLocationX == 0 && randomLocationY == 0) {
				return true;
			}
			if (randomLocationX == 1 && randomLocationY == 0) {
				return true;
			}
			if (randomLocationX == 0 && randomLocationY == 1) {
				return true;
			}
			for (int a = 0; a < i; a++) {
				System.out.println(a);
				if (existingLocations[a][0] == randomLocationX && existingLocations[a][1] == randomLocationY) {
					return true;
				} 
			}
			return false;
		}
		return false;
	}
	
	public LinkedList<Location> getLocations() {
		amountAndLocation();
		return locations;
	}

}
