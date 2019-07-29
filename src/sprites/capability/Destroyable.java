package sprites.capability;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;
import sprites.check.CheckDestruction;

/**
 * Handler for checking if the Sprite is destroyed by an explosion
 * @author Darin Huang
 */
public interface Destroyable {
	CheckDestruction checkDestruction = new CheckDestruction();
	
	/**
	 * @see CheckDestruction#check(int, int, Sprite, Pane)
	 */
	public default void checkDestruction(int posX, int posY, Sprite sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
	
	/**
	 * @see CheckDestruction#check(int, int, Lives, Pane)
	 */
	public default void checkDestruction(int posX, int posY, Lives sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
	
	/**
	 * @see CheckDestruction#check(int, int, Points, Pane)
	 */
	public default void checkDestruction(int posX, int posY, Points sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
}
