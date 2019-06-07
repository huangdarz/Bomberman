package sprites;

import java.util.ArrayList;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import scenes.GameScene;

public abstract class Sprite extends ImageView implements GameLoop {
	AnimationTimer loop;
	Scene scene;
	private Pane pane;
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
	public AnimationTimer getLoop() {return loop;}
	
	public Pane getPane() {return pane;}

}
