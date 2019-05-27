package sprites;

import application.GameLoop;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public abstract class Sprite extends ImageView implements GameLoop {
	private int velocityX, velocityY;
	private Boolean isMovingUp = false, 
					isMovingDown = false, 
					isMovingRight = false, 
					isMovingLeft = false;
	
	public enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
	
	public Sprite() {
		super();
		loop();
	}
	
	public Sprite(Image image) {
		super(image);
		loop();
	}
	
	public Sprite(String url) {
		super(url);
		loop();
	}

	public void move(int velocityX, int velocityY) {
		this.setTranslateX(this.getTranslateX() + velocityX);
		this.setTranslateY(this.getTranslateY() + velocityY);
	}
	
	public void userMovementDetectARROW(Scene scene) {
		scene.setOnKeyPressed(key -> moveKeyPressedReleased(key.getCode(), true));
		scene.setOnKeyReleased(key -> moveKeyPressedReleased(key.getCode(), false));
	}
	
	public void moveKeyPressedReleased(KeyCode key, Boolean isPressed) {
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
	
	public void userMove(int speed) {
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
	
	public int getVelocityX() {return velocityX;}
	public int getVelocityY() {return velocityY;}
	public void setVelocityX(int velocity) {this.velocityX = velocity;}
	public void setVelocityY(int velocity) {this.velocityY = velocity;}
	
	public Boolean getIsMoving(Direction direction) {
		Boolean isMoving = null;
		switch (direction) {
			case UP: 
				isMoving = isMovingUp;
				break;
			case DOWN:
				isMoving = isMovingDown;
				break;
			case RIGHT:
				isMoving = isMovingRight;
				break;
			case LEFT:
				isMoving = isMovingLeft;
				break;
			default:
				break;
		}
		return isMoving;
	}
	public void setIsMoving(Direction direction, Boolean isMoving) {
		switch(direction) {
			case UP:
				this.isMovingUp = isMoving;
				break;
			case DOWN:
				this.isMovingDown = isMoving;
				break;
			case RIGHT:
				this.isMovingRight = isMoving;
				break;
			case LEFT:
				this.isMovingLeft = isMoving;
				break;
			default:
				break;
		}
	}
	
}
