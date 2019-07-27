package sprites.check;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;

public interface Checker {
	
	public void check(int posX, int posY, Sprite sprite, Pane pane);
}
