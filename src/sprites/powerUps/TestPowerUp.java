package sprites.powerUps;

import java.util.Random;

import javafx.scene.Scene;
import sprites.Sprite;

public class TestPowerUp extends Sprite {
	boolean locationFreeX = false;
	boolean locationFreeY = false ;
	int amount;
	int randomLocationX;
	int randomLocationY;
	
	Random rand = new Random();
	
	public TestPowerUp(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		choseAmountAndLocation();
	}

	
	private void locationGeneration() {
		if (locationFreeX == false || locationFreeY == false) {
			randomLocationX = rand.nextInt(13);
			randomLocationY = rand.nextInt(11);
			System.out.println(randomLocationX);
		}
	}
	
	private void checkLocation() {
		switch (randomLocationY) {
		case 0:
			locationCheckIfFree();
			break;
		case 1:
			locationCheckIfFree();
			break;
		case 2:
			locationCheckIfFree();
			break;
		case 3:
			locationCheckIfFree();
			break;
		case 4:
			locationCheckIfFree();
			break;
		case 5:
			locationCheckIfFree();
			break;
		case 6:
			locationCheckIfFree();
			break;
		case 7:
			locationCheckIfFree();
			break;
		case 8:
			locationCheckIfFree();
			break;
		case 9:
			locationCheckIfFree();
			break;
		case 10:
			locationCheckIfFree();
			break;
		}
	}
	
	private void locationCheckIfFree() {
		if (randomLocationX % 2 != 0) {
			locationGeneration();
		}
		else {
			locationFreeX = true;
			locationFreeY = true;
		}
	}

	private void choseAmountAndLocation() {
		amount = rand.nextInt(6);
		for (int i = 0; i < amount; i++) {
			locationGeneration();
			checkLocation();
		}
	}
	
}

