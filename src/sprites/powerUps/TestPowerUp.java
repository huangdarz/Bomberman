package sprites.powerUps;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class TestPowerUp extends Sprite {
	Random rand = new Random();
	
	boolean locationFreeX = false;
	boolean locationFreeY = false ;
	boolean ifSet = false;
	int amount = rand.nextInt(5);
	int randomLocationX;
	int randomLocationY;
	
	public TestPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/player.png");
		setImage(image);
		setFitWidth(50);
		setFitHeight(50);
	}
	
	@Override
	public void run() {
		if (ifSet == false) {
			choseAmountAndLocation();
			ifSet = true;
			System.out.println(ifSet);
		}
	}

	
	private void locationGeneration() {
		if (locationFreeX == false || locationFreeY == false) {
			randomLocationX = rand.nextInt(13);
			randomLocationY = rand.nextInt(11);
			System.out.println(randomLocationX);
			System.out.println(randomLocationY);
			System.out.println(amount);
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
	
	private void checkXEven() {
		while (randomLocationX % 2 != 0 && randomLocationY % 2 != 0) {
			locationGeneration();
		}
		locationFreeX = true;
		locationFreeY = true;
	}
	
	private void locationCheckIfFree() {
		if (randomLocationY == 0 || randomLocationY == 10) {
			locationFreeX = true;
			locationFreeY = true;
		}
		else {
			checkXEven();
		}
	}

	private void choseAmountAndLocation() {
		for (int i = 0; i <= amount; ++i) {
			locationGeneration();
			checkLocation();
			locationSetting();
			
			locationFreeX = false;
			locationFreeY = false;
		}
	}
	
	private void locationSetting() {
		relocate(((randomLocationX*50)+50), ((randomLocationY*50)+50));
		System.out.println("Yes!!");
	}
	
}

