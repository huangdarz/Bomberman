package level;

import java.util.LinkedList;
import java.util.Random;

public class DistributeRandom {
	private Random rand;
	private int randAmount;
	
	private int
		amount,
		randomLocationX,
		randomLocationY,
		existingLocations[][];
		
	private LinkedList<Location> locations;
	
	public DistributeRandom(int min, int max) {
		this.rand = new Random();
		this.randAmount = max - min + 1;
		amount = rand.nextInt(randAmount) + min;
		existingLocations = new int[amount+1][2];
		locations = new LinkedList<Location>();
		generate();
	}
	
	public DistributeRandom(int min, int max, int[][] existingLocations) {
		this.rand = new Random();
		this.randAmount = max - min + 1;
		amount = rand.nextInt(randAmount) + min;
		this.existingLocations = new int[existingLocations.length+amount+1][2];
		for (int a = 0; a < existingLocations.length; a++) {
			this.existingLocations[this.existingLocations.length-a-1][0] = existingLocations[a][0];
			this.existingLocations[this.existingLocations.length-a-1][1] = existingLocations[a][1];
		}
		locations = new LinkedList<Location>();
		generate();
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
		if (randomLocationX == 0 && randomLocationY == 0) {
			return true;
		}
		if (randomLocationX == 1 && randomLocationY == 0) {
			return true;
		}
		if (randomLocationX == 0 && randomLocationY == 1) {
			return true;
		}
		for (int a = 0; a < existingLocations.length; a++) {
			if (existingLocations[a][0] == randomLocationX && existingLocations[a][1] == randomLocationY) {
				return true;
			} 
		}
		return false;
	}
	
	public LinkedList<Location> getLocations() {
		return locations;
	}
	
	public void generate() {
		amountAndLocation();
	}
	
	public int[][] getExistingLocations() {return existingLocations;}

}
