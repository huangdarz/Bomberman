package sprites.base;

import java.util.ArrayList;
import java.util.HashSet;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.Player;
import sprites.type.Enemy;
import sprites.type.Power;

public abstract class Sprite extends ImageView implements GameLoop {
	AnimationTimer loop;
	Scene scene;
	private Pane pane;
	public int positionX, positionY;
	private int velocityX, velocityY;
	
	
	private boolean isMovingUp = false, 
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
		evaluatePosition();
		GameScene.grid[positionX][positionY].remove(this);
		this.relocate(this.getLayoutX() + velocityX, this.getLayoutY() + velocityY);
		evaluatePosition();
		if (this instanceof Enemy && !(((Enemy) this).isDestroyed())) {
			GameScene.grid[positionX][positionY].add(this);
		}
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
	
	public HashSet<Direction> getInvalidDirections() {
		CollisionBounds b = new CollisionBounds(getLayoutX(), getLayoutY(), getLayoutBounds().getWidth(), getLayoutBounds().getHeight());
		HashSet<Direction> invalidDirections = new HashSet<Sprite.Direction>();
		for(Sprite s : ((GameScene) scene).getInLocalGrids(positionX, positionY)) {
			if (!(s instanceof Power) && !(s instanceof Player) && !(s instanceof Enemy)) {
				invalidDirections.addAll(b.isTouching(new CollisionBounds(s.getLayoutX(), s.getLayoutY(), s.getLayoutBounds().getWidth(), s.getLayoutBounds().getHeight()), 3));
			} 
		}
		return invalidDirections;
	}
	
	public void evaluatePosition() {
		positionX = (int) Math.round(getLayoutX() / 50d);
		positionY = (int) Math.round(getLayoutY() / 50d);
	}
	
	public AnimationTimer getLoop() {return loop;}
	
	public Pane getPane() {return pane;}

}
