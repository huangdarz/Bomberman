package sprites;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.base.Sprite;
import sprites.capability.Destroyable;
import sprites.capability.Points;
import sprites.type.Enemy;

public class Mob extends Sprite implements Destroyable, Points, Enemy {
	private static int scorePotential;
	
	Random rand = new Random();
	
	boolean getPosition = true;
	boolean isDestroyed = false;
	double currentX;
	double currentY;
	int speed = 2;
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
		
		scorePotential = 10;
	}

/**
 * Repeats the methods infinitely
 */
	@Override
	public void run() {
		evaluatePosition();
		
		reroll();

		switchRandomLength();

		switchRandomDirection();
		
		checkDestruction(positionX, positionY, (Points) this, getPane());
		
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

	@Override
	public int getPoints() {
		return scorePotential;
	}

	@Override
	public void setPoints(int points) {
		Mob.scorePotential = points;
	}
	
	public static int getScorePotential() {
		return scorePotential;
	}
	
	public static void setScorePotential(int scorePotential) {
		Mob.scorePotential = scorePotential;
	}

	@Override
	public boolean isDestroyed() {
		return isDestroyed;
	}

	@Override
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

}
