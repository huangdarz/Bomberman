package sprites.powerUps;

import java.util.Random;

public class TestPowerUp {
	int randomLocationX;
	int randomLocationY;
	
	Random rand = new Random();
	
	private void locationGeneration() {
		randomLocationX = rand.nextInt(13);
		randomLocationY = rand.nextInt(11);
	}
	
	private void checkLocationFree() {
		switch (randomLocationX) {
		
		}
	}
}

