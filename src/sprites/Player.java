package sprites;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import scenes.GameScene;
import sprites.bomb.Bomb;
import sprites.bomb.Explodable;
import sprites.bomb.ExplosionThread;

/** 
 * The user controlled playable character.  
 * @author Darin Huang 
 */
public class Player extends Sprite implements Explodable {
	private static int lives = 6;
	private static int bombIndex = 0;
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	
	/** 
	 * Primary constructor for Player. 
	 * @param scene The scene Player is added to
	 */
	public Player(Scene scene) {
		super(scene);
		Image image = new Image("/res/player.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
		setLayoutX(50);
		setLayoutY(50);
		
		setSmooth(true);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		userMove(4);
		this.toFront();
	}
	
	/**
	 * Changes the isMoving values according to the key when pressed or released.
	 * @param key The KeyCode from key event handlers
	 * @param isPressed Whether key event is pressed or released
	 */
	public synchronized void moveKeyPressedReleased(KeyCode key, boolean isPressed) {
			switch (key) {
				case UP:
					this.setIsMoving(Sprite.Direction.UP, isPressed);
					break;
				case DOWN:
					this.setIsMoving(Sprite.Direction.DOWN, isPressed);
					break;
				case RIGHT:	
					this.setIsMoving(Sprite.Direction.RIGHT, isPressed);
					break;
				case LEFT:
					this.setIsMoving(Sprite.Direction.LEFT, isPressed);
					break;
				default:
					break;
		}
	}
	
	/**
	 * Checks isMoving booleans to determine movement in that given direction. 
	 * @param speed The speed of which the player should move
	 */
	private synchronized void userMove(int speed) {
		HashSet<Direction> invalidDirections = getInvalidDirections();
		if (this.getIsMoving(Sprite.Direction.UP) && !invalidDirections.contains(Sprite.Direction.UP)) {
			this.move(0, -speed);
		}
		if (this.getIsMoving(Sprite.Direction.DOWN) && !invalidDirections.contains(Sprite.Direction.DOWN)) {
			this.move(0, speed);
		}
		if (this.getIsMoving(Sprite.Direction.RIGHT) && !invalidDirections.contains(Sprite.Direction.RIGHT)) {
			this.move(speed, 0);
		}
		if (this.getIsMoving(Sprite.Direction.LEFT) && !invalidDirections.contains(Sprite.Direction.LEFT)) {
			this.move(-speed, 0);
		}
	}
	
	public void placeBomb(KeyCode key) {
		if (key == KeyCode.SPACE) {
			if (Bomb.getBombsPlaced() < Bomb.getMaxNumBombs()) {
				getBombs().add(getBombIndex(), new Bomb(getScene()));
				getPane().getChildren().add(getBombs().get(getBombIndex()));
				getBombs().get(getBombIndex()).relocate(positionX*50, positionY*50);
				getBombs().get(getBombIndex()).toBack();
				createExplosionThread(getBombs().get(getBombIndex())).start();
				setBombIndex(getBombIndex()+1);
				Bomb.setBombsPlaced(Bomb.getBombsPlaced()+1);
			}
		}
	}
	
	private ExplosionThread createExplosionThread(Bomb b) {return new ExplosionThread(b);}
	
	public static int getLives() {return lives;}
	public static void setLives(int lives) {Player.lives = lives;}
	
	public static int getBombIndex() {return bombIndex;}
	public static void setBombIndex(int bombIndex) {Player.bombIndex = bombIndex;}
	
	public ArrayList<Bomb> getBombs() {return bombs;}
	public void setBombs(ArrayList<Bomb> bombs) {this.bombs = bombs;}

}
