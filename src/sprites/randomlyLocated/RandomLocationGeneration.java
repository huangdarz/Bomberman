package sprites.randomlyLocated;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

/** 
 * The Location generation and checking if free 
 * @author Michael Legovich 
 */
public class RandomLocationGeneration extends Sprite {
/** 
 * Declares all variables and arrays for all the methods
 */
	static Random rand = new Random();
	
	boolean existingPowerUpLocation = false;
	boolean ifSet = false;
    int amountOfPowerUps = rand.nextInt(5);
	int currentAmountOfPowerUps = 0;
	int randomLocationX;
	int randomLocationY;
	int existingLocations[][] = new int[amountOfPowerUps+1][2];
	
	public RandomLocationGeneration(Scene scene) {
		super(scene);
	}
	
	Sprite sprite;
	
	public RandomLocationGeneration(Scene scene, Sprite s) {
		super(scene);
		this.sprite = s;
	}

/**
 * 	Randomly generates location variables, and resets other variables
 */
	private void locationGeneration() {
		randomLocationX = rand.nextInt(13);
		randomLocationY = rand.nextInt(11);
		existingPowerUpLocation = false;
	}
	
/**
 * Checks if the generated location is applicable, as both location can't be odd
 */
	private void checkLocationFree() {
		while (randomLocationX % 2 != 0 && randomLocationY % 2 != 0) {
			locationGeneration();
		}
	}	

/**
 * Main loop for all methods made, these being used for each individual generation of a power up
 * It also sets the good location to the array, and use to check if another power might be 
 * placed on top
 */
	private void amountAndLocation() {
		for (int i = 0; i <= amountOfPowerUps; i++) {
			locationGeneration();
			
			checkLocationFree();
			
			if(checkPreviousLocations(i)) {
				while (checkPreviousLocations(i) == true) {
					locationGeneration();
					
					checkLocationFree();
				}
			}

			existingLocations[i][0] = randomLocationX;
			existingLocations[i][1] = randomLocationY;
			
			createNextPowerUp();
		}
	}
	
/**
 * Only runs the amountAndLocation method and initialLocationSetting once
 */
	protected void initiateAmountAndLocation() {
		if (ifSet == false) {
			amountAndLocation();
			
			initialLocationSetting();
			
			ifSet = true;
		}
	}
	
/**
 * Checking if a given location has a power up already placed
 * @param i
 * @return
 */
	private boolean checkPreviousLocations(int i) {
		if (i != 0) {
			for (int a = 0; a < i; a++) {
				if (existingLocations[a][0] == randomLocationX && existingLocations[a][1] == randomLocationY) {
					existingPowerUpLocation = true;
				}
			}
		}
		return existingPowerUpLocation;
	}
	
/**
 * Placing on the map the first power up
 */
	private void initialLocationSetting() {
		sprite.relocate(((randomLocationX*50)+55), ((randomLocationY*50)+55));
	}
	
/**
 * Placing on the map of the other power ups
 */
	protected void createNextPowerUp() {
		if (currentAmountOfPowerUps < amountOfPowerUps) {
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}

