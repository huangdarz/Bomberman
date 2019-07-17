package level;

import java.util.LinkedList;
import java.util.Random;

public class DistributeRandom {
	private Random rand;
	private int randAmount;
	
	private boolean 
		shouldRun,
		existingPowerUpLocation;
	
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
		shouldRun = true;
		existingPowerUpLocation = false;
		currentAmount = 0;
		amount = rand.nextInt(randAmount) + min;
		existingLocations = new int[amount+1][2];
		locations = new LinkedList<Location>();
	}
	
	private void generate() {
		while (shouldRun) {
			if (currentAmount < amount) {
				initiateAmountAndLocation();
				locations.add(new Location(randomLocationX, randomLocationY));
//				System.out.println("-----[Level.run()]-----");
				System.out.println("Current amount: " + currentAmount + " " + amount);
//				System.out.println("Locations: " + locations + " " + amount);
//				System.out.printf("X: %d - Y: %d\n", randomLocationX, randomLocationY);
				currentAmount++;
			} else {
				for(int i = 0; i < amount+1; i++)
				   {
				      for(int j = 0; j < 2; j++)
				      {
				         System.out.printf(" Location %d ", existingLocations[i][j]);
				      }
				      System.out.println();
				   }
				shouldRun = false;
			}
		}
	}
	
	private void locationGeneration() {
		randomLocationX = rand.nextInt(13);
		randomLocationY = rand.nextInt(11);
		existingPowerUpLocation = false;
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
			
			if(checkPreviousLocations(i)) {
				while (checkPreviousLocations(i) == true) {
					locationGeneration();
					
					checkLocationFree();
				}
			}

			existingLocations[i][0] = randomLocationX;
			existingLocations[i][1] = randomLocationY;
			
		}
	}
	
	private void initiateAmountAndLocation() {
		amountAndLocation();
	}
	
	private boolean checkPreviousLocations(int i) {
		if (i != 0) {
			for (int a = 0; a < i; a++) {
				if (existingLocations[a][0] == randomLocationX && existingLocations[a][1] == randomLocationY) {
					existingPowerUpLocation = true;
				} else {
					existingPowerUpLocation = false;
				}
			}
		}
		return existingPowerUpLocation;
	}
	
	public LinkedList<Location> getLocations() {
		generate();
		return locations;
	}

}
