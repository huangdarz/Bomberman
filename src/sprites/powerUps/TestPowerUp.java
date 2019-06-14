package sprites.powerUps;

import java.util.Random;

import javafx.scene.Scene;
import sprites.Sprite;

public class TestPowerUp extends Sprite {
	public TestPowerUp(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}
	
	boolean locationFreeX = false;
	boolean locationFreeY = false ;
	int randomLocationX;
	int randomLocationY;
	
	Random rand = new Random();
	
	private void locationGeneration() {
		if (locationFreeX == false || locationFreeY == false) {
			randomLocationX = rand.nextInt(13);
			randomLocationY = rand.nextInt(11);
			System.out.println(randomLocationX);
		}
	}
	
	private void checkLocationFree() {
		switch (randomLocationX) {
		case 0:
			locationFreeX = true;
			locationFreeY = true;
		case 1:
			if (randomLocationY % 2 == 0) {
				locationFreeX = true;
				locationFreeY = true;
			}
			else {
				locationGeneration();
			}
		case 2:
			locationFreeX = true;
			locationFreeY = true;
		case 3:
		}
	}

	@Override
	public void run() {
		
		locationGeneration();
		checkLocationFree();
	}
}

