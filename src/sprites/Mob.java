package sprites;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Mob extends Sprite {
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
		relocate(50, 50);
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
			randomLength = rand.nextInt(4);
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
		case 3:
			positionChecker(200);
			break;
		}
	}
	
	private void checkCollision(Direction directionChosen, int speedX, int speedY) {
		if(!getInvalidDirections().contains(directionChosen)) {
			move(speedX, speedY);
		}
		else {
			getPosition = true;
		}
	}
	
	private void switchRandomDirection() {
		switch (randomDirection) {
		case 0:
			checkCollision(Direction.UP, 0, -speed);
			break;
			
		case 1:
			checkCollision(Direction.DOWN, 0, speed);
			break;
			
		case 2:
			checkCollision(Direction.RIGHT, speed, 0);
			break;
			
		case 3:
			checkCollision(Direction.LEFT, -speed, 0);
			break;
		}
	}
}
