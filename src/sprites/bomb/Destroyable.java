package sprites.bomb;

import javafx.scene.layout.Pane;
import sprites.Lives;
import sprites.Sprite;

public interface Destroyable {
	CheckDestruction checkDestruction = new CheckDestruction();
	
	public default void checkDestruction(int posX, int posY, Sprite sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
	
	public default void checkDestruction(int posX, int posY, Lives sprite, Pane pane) {
		checkDestruction.check(posX, posY, sprite, pane);
	}
	
	public default void checkDestruction(int posX, int posY, int pointsAwarded, Sprite sprite, Pane pane) {
		checkDestruction.check(posX, posY, pointsAwarded, sprite, pane);
	}
}
