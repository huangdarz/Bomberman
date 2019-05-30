package sprites;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

/** 
 * The user controlled playable character.  
 * @author Darin Huang 
 * @version 1.0
 */
public class Player extends Sprite {

	/** 
	 * Primary constructor for Player. 
	 * @param scene The scene Player is added to
	 */
	public Player(Scene scene) {
		super(scene);
		Image image = new Image("/res/test.jpg");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
		userMovementDetectARROW();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		userMove(5);
	}

	/**
	 * Detects key presses from the scene to then run movement procedures. 
	 */
	private void userMovementDetectARROW() {
		scene.setOnKeyPressed(key -> {
			moveKeyPressedReleased(key.getCode(), true);
			if (key.getCode() == KeyCode.ESCAPE) {
				loop.stop();
			}
			if (key.getCode() == KeyCode.INSERT) {
				loop.start();
			}
		});
		scene.setOnKeyReleased(key -> moveKeyPressedReleased(key.getCode(), false));
	}
	
	/**
	 * Changes the isMoving values according to the key when pressed or released.
	 * @param key The KeyCode from key event handlers
	 * @param isPressed Whether key event is pressed or released
	 */
	private void moveKeyPressedReleased(KeyCode key, Boolean isPressed) {
			switch (key) {
				case UP:
					this.setIsMoving(Sprite.Direction.UP, isPressed ? true : false);
					break;
				case DOWN:
					this.setIsMoving(Sprite.Direction.DOWN, isPressed ? true : false);
					break;
				case RIGHT:
					this.setIsMoving(Sprite.Direction.RIGHT, isPressed ? true : false);
					break;
				case LEFT:
					this.setIsMoving(Sprite.Direction.LEFT, isPressed ? true : false);
					break;
				default:
					break;
		}
	}
	
	/**
	 * Checks isMoving booleans to determine movement in that given direction. 
	 * @param speed The speed of which the player should move
	 */
	private void userMove(int speed) {
		if (this.getIsMoving(Sprite.Direction.UP)) {
			this.move(0, -speed);
		}
		if (this.getIsMoving(Sprite.Direction.DOWN)) {
			this.move(0, speed);
		}
		if (this.getIsMoving(Sprite.Direction.RIGHT)) {
			this.move(speed, 0);
		}
		if (this.getIsMoving(Sprite.Direction.LEFT)) {
			this.move(-speed, 0);
		}
	}
}
