package sprites;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import sprites.bomb.Bomb;
import sprites.bomb.ExplosionThread;

/** 
 * The user controlled playable character.  
 * @author Darin Huang 
 */
public class Player extends Sprite {
	private int lives;
	private int bombIndex = 0;
	private Thread explosionThread;
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	
	/** 
	 * Primary constructor for Player. 
	 * @param scene The scene Player is added to
	 */
	public Player(Scene scene) {
		super(scene);
		Image image = new Image("/res/player.png");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
		this.setSmooth(true);
		
		setExplosionThread(new ExplosionThread(getBombs()));
		getExplosionThread().start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		userMove(5);
		this.toFront();
	}
	
	/**
	 * Changes the isMoving values according to the key when pressed or released.
	 * @param key The KeyCode from key event handlers
	 * @param isPressed Whether key event is pressed or released
	 */
	public void moveKeyPressedReleased(KeyCode key, Boolean isPressed) {
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
	
	public void placeBomb(KeyCode key) {
		if (key == KeyCode.SPACE) {
			if (getBombs().isEmpty()) setBombIndex(0);
			getBombs().add(getBombIndex(), new Bomb(getScene()));
			getPane().getChildren().add(getBombs().get(getBombIndex()));
			getBombs().get(getBombIndex()).relocate(this.getLayoutX(), this.getLayoutY());
			getBombs().get(getBombIndex()).toBack();
			setBombIndex(getBombIndex()+1);
			bombExplode();
		}
	}
	
	private void bombExplode() {
		synchronized(getExplosionThread()) {
			getExplosionThread().notify();
		}
	}
	
	public void explodeBomb(KeyCode key) {
		if (key == KeyCode.A) {
			synchronized(getExplosionThread()) {
				getExplosionThread().notify();
			}
		}
	}
	
	public int getLives() {return lives;}
	public void setLives(int lives) {this.lives = lives;}

	public int getBombIndex() {return bombIndex;}
	public void setBombIndex(int bombIndex) {this.bombIndex = bombIndex;}
	
	public Thread getExplosionThread() {return explosionThread;}
	public void setExplosionThread(Thread explosionThread) {this.explosionThread = explosionThread;}
	
	public ArrayList<Bomb> getBombs() {return bombs;}
	public void setBombs(ArrayList<Bomb> bombs) {this.bombs = bombs;}
}
