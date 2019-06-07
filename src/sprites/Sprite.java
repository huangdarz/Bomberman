package sprites;

import java.util.ArrayList;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import scenes.GameScene;

public abstract class Sprite extends ImageView implements GameLoop {
	AnimationTimer loop;
	Scene scene;
	public int positionX, positionY;
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
		loop = loop();
		loop.start();
	}
	
	public void move(int velocityX, int velocityY) {
		this.setTranslateX(this.getTranslateX() + velocityX);
		this.velocityX = velocityX;
		this.setTranslateY(this.getTranslateY() + velocityY);
		this.velocityY = velocityY;
	}
	
	public void userMovementDetectARROW(Scene scene, AnimationTimer timerLoop) {
		scene.setOnKeyPressed(key -> {
			moveKeyPressedReleased(key.getCode(), true);
			if (key.getCode() == KeyCode.ESCAPE) {
				timerLoop.stop();
			}
			if (key.getCode() == KeyCode.INSERT) {
				timerLoop.start();
			}
		});
		scene.setOnKeyReleased(key -> moveKeyPressedReleased(key.getCode(), false));
	}
	
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
	
	public int getVelocityX() {return this.velocityX;}
	public int getVelocityY() {return this.velocityY;}
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
	
	public Sprite getColliding(ArrayList<Sprite> spritesInGrid) {
		for(Sprite s : spritesInGrid) {
			if(this.getLayoutBounds().contains(s.getLayoutBounds()) && !s.equals(this)) {
				return s;
			}
		}
		return null;
	}
	
	public Thread getStayInBoundThread() {
		return new Thread(() -> {
			boolean shouldRun = true;
			while(shouldRun) {
				for(Sprite s : GameScene.getInLocalGrids(positionX, positionY)) {
					double distanceX = Math.min(s.getLayoutBounds().getMaxX() - getLayoutBounds().getMinX(), s.getLayoutBounds().getMinX() - getLayoutBounds().getMaxX());
					double distanceY = Math.min(s.getLayoutBounds().getMaxY() - getLayoutBounds().getMinY(), s.getLayoutBounds().getMinY() - getLayoutBounds().getMaxY());
					Direction directionX = s.getLayoutBounds().getMaxX() - getLayoutBounds().getMinX() > s.getLayoutBounds().getMinX() - getLayoutBounds().getMaxX() ? Direction.LEFT : Direction.RIGHT;
					Direction directionY = s.getLayoutBounds().getMaxY() - getLayoutBounds().getMinY() > s.getLayoutBounds().getMinY() - getLayoutBounds().getMaxY() ? Direction.UP : Direction.DOWN;
					if((distanceX < 5) /*&& !(s instanceof Explosion, Mob)*/) {
						if(directionX == Direction.RIGHT) isMovingRight = false; 
						else isMovingLeft = false;
					}
					if((distanceY < 5) /*&& !(s instanceof Explostion, Mob)*/) {
						if(directionY == Direction.UP) isMovingUp = false; 
						else isMovingDown = false; 
					}
				}
			}
		});
	}
}
