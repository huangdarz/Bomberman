package sprites.capability;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;
import sprites.check.CheckPower;

public interface Powerable {
	CheckPower checkPower = new CheckPower();
	
	public default void checkPower(int posX, int posY, Sprite sprite, Pane pane) {
		checkPower.check(posX, posY, sprite, pane);
	}
}
