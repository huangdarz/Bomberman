package level;

import java.util.LinkedList;
import java.util.Random;

import scenes.GameScene;

/**
 * This class calculates the random locations for a Sprite.
 * @author Michael Legovich and Darin Huang
 */
public class DistributeRandom {
	private Random rand;
	private int randAmount;
	
	private int
		amount,
		randomLocationX,
		randomLocationY,
		existingLocations[][];
		
	private LinkedList<Location> locations;
	
	/**
	 * Constructor that generates default values to calculate location values.
	 * Generate() is also called on creation of this object. 
	 * @param min The minimum amount of Sprites.
	 * @param max The maximum amount of Sprites.
	 */
	public DistributeRandom(int min, int max) {
		this.rand = new Random();
		this.randAmount = max - min + 1;
		amount = rand.nextInt(randAmount) + min;
		existingLocations = new int[amount+1][2];
		locations = new LinkedList<Location>();
		generate();
	}
	
	/**
	 * Constructor that can add pre-existing locations of another Sprite to ensure no overlap.
	 * @param min The minimum amount of Sprites.
	 * @param max The maximum amount of Sprites.
	 * @param existingLocations The pre-existing locations of another Sprite
	 */
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
	
	/**
	 * Generates a random x and y coordinate for the map.
	 * (Based off grid position 13 x 11)
	 */
	private void locationGeneration() {
		randomLocationX = rand.nextInt(GameScene.grid.length - 2);
		randomLocationY = rand.nextInt(GameScene.grid[0].length - 2);
	}

	/**
	 * Checks if the generated location is free of unbreakable walls.
	 */
	private void checkLocationFree() {
		while (randomLocationX % 2 != 0 && randomLocationY % 2 != 0) {
			locationGeneration();
		}
	}
	
	/**
	 * Handler to generate values based off the amount.
	 */
	private void amountAndLocation() {
		for (int i = 0; i <= amount; i++) {
			locationGeneration();
			
			checkLocationFree();
			
			while (checkPreviousLocations() == true) {
				locationGeneration();
				
				checkLocationFree();
			}
						
			existingLocations[i][0] = randomLocationX;
			existingLocations[i][1] = randomLocationY;
			
			locations.add(new Location(randomLocationX, randomLocationY));
			
		}
	}
	
	/**
	 * Checks if the location generated already exists and ensures no creation in the top left corner
	 * @return true if the location already exists.
	 */
	private boolean checkPreviousLocations() {
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
	
	/**
	 * @return The locations generated.
	 */
	public LinkedList<Location> getLocations() {
		return locations;
	}
	
	/**
	 * Public handler and call for amountAndLocation()
	 */
	public void generate() {
		amountAndLocation();
	}
	
	/**
	 * @return The existing locations
	 */
	public int[][] getExistingLocations() {return existingLocations;}

}
