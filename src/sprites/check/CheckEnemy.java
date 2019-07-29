package sprites.check;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.Player;
import sprites.base.Sprite;
import sprites.capability.Lives;
import sprites.type.Enemy;

/**
 * Class that provides check for enemy collision
 * @author Darin Huang
 */
public class CheckEnemy implements Checker {

	boolean hasEnemy;
	Player previousPlayer;
	
	/**
	 * Default constructor
	 */
	public CheckEnemy() {
		hasEnemy = false;
		previousPlayer = null;
	}

	/**
	 * Checks for enemy collision and deducts lives.
	 * @param posX The x position of the Sprite in terms of GameScene.grid
	 * @param posY The y position of the Sprite in terms of GameScene.grid
	 * @param sprite The sprite that has lives its checking for and will eventually destroy
	 * @param pane The pane the Sprite is in
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void check(int posX, int posY, Sprite sprite, Pane pane) {
		hasEnemy = false;
		ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
		grid.forEach(x -> {
			if (x instanceof Enemy) {
				if (((Lives) sprite).getLives() <= 0) {
					GameScene.grid[posX][posY].remove(sprite);
					pane.getChildren().remove(sprite);
				} else if (!sprite.equals((Player)previousPlayer)){
					previousPlayer = (Player) sprite;
					((Lives) sprite).setLives(((Lives) sprite).getLives()-1);
				}
			}
		});
		GameScene.grid[posX][posY].forEach(a -> {if (a instanceof Enemy) hasEnemy = true;});
		if (!hasEnemy) previousPlayer = null;
	}
}
