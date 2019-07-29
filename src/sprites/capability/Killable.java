package sprites.capability;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;
import sprites.check.CheckEnemy;

/**
 * Handler for checking if there was a collision with an enemy.
 * @author Darin Huang
 */
public interface Killable {
	CheckEnemy checkEnemy = new CheckEnemy();
	
	/**
	 * @see CheckEnemy#check(int, int, Sprite, Pane)
	 */
	public default void checkEnemy(int posX, int posY, Sprite sprite, Pane pane) {
		checkEnemy.check(posX, posY, sprite, pane);
	}
}
