package sprites.capability;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;
import sprites.check.CheckDestruction;

public interface Destroyable {
	CheckDestruction checkDestruction = new CheckDestruction();
	
	public default void checkDestruction(int posX, int posY, Sprite sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
	
	public default void checkDestruction(int posX, int posY, Lives sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
	
	public default void checkDestruction(int posX, int posY, Points sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
}
