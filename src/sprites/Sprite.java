package sprites;

import application.GameLoop;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
