package sprites.randomlyLocated;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class Mob extends RandomLocationGeneration {
	boolean getPosition = true;
	double currentX;
	double currentY;
	int speed = 4;
	int randomDirection;
	int randomLength;

	
	Random rand = new Random();
	
	public Mob(Scene scene) {
		super(scene);
		Image image = new Image("/res/player.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
		relocate(55, 55);
	}

	@Override
	public void run() {
		reroll();
		
		switchRandomLength();
		
		switchRandomDirection();
	}
	
	private void reroll() {
		if (getPosition == true) {
			currentX = getLayoutX();
			currentY = getLayoutY();
			randomLength = rand.nextInt(3);
			randomDirection = rand.nextInt(4);
			getPosition = false;
		}
	}
	
	private void positionChecker(int position) {
		if (Math.abs(getLayoutX()-currentX) >= position || Math.abs(getLayoutY()-currentY) >= position) {
			getPosition = true;
		} 
	}

	private void switchRandomLength() {
		switch (randomLength) {
		case 0:
			positionChecker(50);
			break;
		case 1:
			positionChecker(100);
			break;
		case 2:
			positionChecker(150);
			break;
		}
	}
	
	private void switchRandomDirection() {
		switch (randomDirection) {
		case 0:
			move(0, -speed);
			break;
		case 1:
			move(0, speed);
			break;
		case 2:
			move(speed, 0);
			break;
		case 3:
			move(-speed, 0);
			break;
	}
	}
}
