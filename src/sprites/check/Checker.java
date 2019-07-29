package sprites.check;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;

/**
 * Interface to implement default method for checking collision
 * @author Darin Huang
 */
public interface Checker {
	
	/**
	 * Method for default checking for a Sprite
	 * @param posX The x position of the Sprite in terms of GameScene.grid
	 * @param posY The y position of the Sprite in terms of GameScene.grid
	 * @param sprite The sprite its checking for
	 * @param pane The pane the sprite is in
	 */
	public void check(int posX, int posY, Sprite sprite, Pane pane);
}
