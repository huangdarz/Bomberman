package sprites.bomb;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.base.Sprite;

/**
 * The explosion sprite
 * @author Darin Huang
 */
public class Explosion extends Sprite {
	private boolean shouldExplode = true; 
	
	/**
	 * Creates a new Sprite
	 * @param scene The scene the Sprite is in.
	 */
	public Explosion(Scene scene) {
		super(scene);
		Image image = new Image("/res/explosion.gif");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		toFront();
	}
	
	/**
	 * Sets the value of shouldExplode
	 * @param shouldExplode The value shouldExplode should be
	 */
	public void setShouldExplode(boolean shouldExplode) {
		this.shouldExplode = shouldExplode;
	}
	
	/**
	 * @return shouldExplode
	 */
	public boolean getShouldExplode() {
		return shouldExplode;
	}
}
