package sprites;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite extends ImageView implements GameLoop {
	private AnimationTimer loop;
	private Scene scene;
	private Pane pane;
	
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
	
	public Sprite(Scene scene) {
		super();
		this.scene = scene;
		this.pane = (Pane) this.scene.getRoot();
		loop = loop();
		loop.start();
	}
	
	public void move(int velocityX, int velocityY) {
		this.relocate(this.getLayoutX() + velocityX, this.getLayoutY() + velocityY);
	}
	
	public int getVelocityX() {return this.velocityX;}
	public void setVelocityX(int velocity) {this.velocityX = velocity;}
	
	public int getVelocityY() {return this.velocityY;}
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
	
	public AnimationTimer getLoop() {return loop;}
	
	public Pane getPane() {return pane;}
}
