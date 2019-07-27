package sprites.capability;

import javafx.scene.layout.Pane;
import sprites.base.Sprite;
import sprites.check.CheckEnemy;

public interface Killable {
	CheckEnemy checkEnemy = new CheckEnemy();
	
	public default void checkEnemy(int posX, int posY, Sprite sprite, Pane pane) {
		checkEnemy.check(posX, posY, sprite, pane);
	}
}
