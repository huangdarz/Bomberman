package sprites;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.bomb.Explodable;

public class Mob extends Sprite implements Explodable {
	Random rand = new Random();
	
	boolean getPosition = true;
	double currentX;
	double currentY;
	int speed = 4;
	int randomDirection;
	int randomLength;
	

/**
 * Setting the objects size and location
 * @param scene
 */
	public Mob(Scene scene) {
		super(scene);
		Image image = new Image("/res/player.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
		relocate(55, 55);
	}

/**
 * Repeats the methods infinitely
 */
	@Override
	public void run() {
		reroll();

		switchRandomLength();

		switchRandomDirection();
	}

/**
 * Checking if the movement position has been reached and resting the variables
 */
	private void reroll() {
		if (getPosition == true) {
			currentX = getLayoutX();
			currentY = getLayoutY();
			randomLength = rand.nextInt(4);
			randomDirection = rand.nextInt(4);
			getPosition = false;
		}
	}

/**
 * Checks that the position of the mob has move the desired amount, and if yes
 * allows the variables to be reset
 * @param position
 */
	private void positionChecker(int position) {
		if (Math.abs(getLayoutX()-currentX) >= position || Math.abs(getLayoutY()-currentY) >= position) {
			getPosition = true;
		}
	}

/**
 * Deciding how far to travel in a set direction, based on the number randomly found
 */
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

/**
 * Checking if the mob is colliding with another object, mainly walls or boundaries, if it isn't
 * make it move, otherwise rest the variables
 */
	private void checkCollision(Direction directionChosen, int speedX, int speedY) {
		if(!getInvalidDirections().contains(directionChosen)) {
			move(speedX, speedY);
		}
		else {
			getPosition = true;
		}
	}

/**
 * Deciding which direction to travel in
 */
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
