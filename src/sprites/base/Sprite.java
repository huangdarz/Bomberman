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

/**
 * Base Sprite class for new Sprites to build from
 * @author Darin Huang
 */
public abstract class Sprite extends ImageView implements GameLoop {
	AnimationTimer loop;
	Scene scene;
	private Pane pane;
	public int positionX, positionY;
	
	private boolean isMovingUp = false, 
					isMovingDown = false, 
					isMovingRight = false, 
					isMovingLeft = false;
	
	/**
	 * Absolute values for the direction the sprite is travelling
	 * @author Darin Huang
	 */
	public enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
	
	/**
	 * Default constructor for Sprite
	 * @param scene The scene the sprite is in
	 */
	public Sprite(Scene scene) {
		super();
		this.scene = scene;
		this.pane = (Pane) this.scene.getRoot();
		loop = loop();
		loop.start();
	}
	
	/**
	 * Moves the sprite at a specified speed.
	 * @param velocityX The speed and direction along x
	 * @param velocityY The speed and direction along y
	 */
	public void move(int velocityX, int velocityY) {
		evaluatePosition();
		GameScene.grid[positionX][positionY].remove(this);
		this.relocate(this.getLayoutX() + velocityX, this.getLayoutY() + velocityY);
		evaluatePosition();
		if (this instanceof Enemy && !(((Enemy) this).isDestroyed())) {
			GameScene.grid[positionX][positionY].add(this);
		}
	}
	
	/**
	 * Checks the boolean moving values
	 * @param direction The direction the sprite is moving
	 * @return true if the Sprite is moving in the given direction
	 */
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
	
	/**
	 * Sets the boolean moving values based of the direciton
	 * @param direction The direction that the sprite is moving
	 * @param isMoving The value to set the boolean moving value
	 */
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
	
	// TODO Mitch to comment
	public Sprite getColliding(ArrayList<Sprite> spritesInGrid) {
		for(Sprite s : spritesInGrid) {
			if(this.getLayoutBounds().contains(s.getLayoutBounds()) && !s.equals(this)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Gets the invalid directions around a sprite for movement
	 * @return The invalid directions
	 */
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
	
	/**
	 * Calculates the position of the sprite in terms of GameScene.grid
	 */
	public void evaluatePosition() {
		positionX = (int) Math.round(getLayoutX() / 50d);
		positionY = (int) Math.round(getLayoutY() / 50d);
	}
	
	/**
	 * @return loop
	 */
	public AnimationTimer getLoop() {return loop;}
	
	/**
	 * @return pane
	 */
	public Pane getPane() {return pane;}

}
