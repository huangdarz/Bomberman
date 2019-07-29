package sprites.capability;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;
import sprites.check.CheckPower;

/**
 * Handler for checking collision with power ups
 * @author Darin Huang
 */
public interface Powerable {
	CheckPower checkPower = new CheckPower();
	
	/**
	 * @see CheckPower#check(int, int, Sprite, Pane)
	 */
	public default void checkPower(int posX, int posY, Sprite sprite, Pane pane) {
		checkPower.check(posX, posY, sprite, pane);
	}
}
