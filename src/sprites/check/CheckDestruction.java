package sprites.check;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.base.Sprite;
import sprites.bomb.Explosion;
import sprites.capability.Lives;
import sprites.capability.Points;
import sprites.type.Enemy;

/**
 * Class that provides checking of collision with explosions
 * @author Darin Huang
 */
public class CheckDestruction implements Checker {
	boolean hasExplosion;
	Lives previousLife;
	ArrayList<Points> previous;
	
	/**
	 * Default constructor
	 */
	public CheckDestruction() {
		hasExplosion = false;
		previousLife = null;
		previous = new ArrayList<Points>();
	}
	
	/**
	 * Checks for collision with explosion for immediate destruction
	 * @param posX The x position of the Sprite in terms of GameScene.grid
	 * @param posY The y position of the Sprite in terms of GameScene.grid
	 * @param sprite The sprite its checking for
	 * @param pane The pane the sprite is in
	 */
	@SuppressWarnings("unchecked")
	public void check(int posX, int posY, Sprite s, Pane pane) {
		ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
		grid.forEach(x -> {
			if (x instanceof Explosion) {
				GameScene.grid[posX][posY].remove(s);
				pane.getChildren().remove(s);
			}
		});
	}
	
	/**
	 * Checks for collision with explosion to then deduct lives then eventually destroy
	 * @param posX The x position of the Sprite in terms of GameScene.grid
	 * @param posY The y position of the Sprite in terms of GameScene.grid
	 * @param sprite The sprite that has lives its checking for
	 * @param pane The pane the sprite is in
	 */
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public void check(int posX, int posY, Lives s, Pane pane) {
		hasExplosion = false;
		if (s instanceof Lives) {
			ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
			grid.forEach(x -> {
				if (x instanceof Explosion) {
					if (s.getLives() <= 0) {
						GameScene.grid[posX][posY].remove((Sprite) s);
						pane.getChildren().remove(s);
					} else if (!s.equals(previousLife)){
						previousLife = s;
						s.setLives(s.getLives()-1);
					}
				}
			});
		} 
		GameScene.grid[posX][posY].forEach(a -> {if (a instanceof Explosion) hasExplosion = true;});
		if (!hasExplosion) previousLife = null;
	}
	
	/**
	 * Checks for collision with explosion to then add points and immediately destroy
	 * @param posX The x position of the Sprite in terms of GameScene.grid
	 * @param posY The y position of the Sprite in terms of GameScene.grid
	 * @param sprite The sprite its checking for
	 * @param pane The pane the sprite is in
	 */
	@SuppressWarnings("unchecked")
	public void check(int posX, int posY, Points s, Pane pane) {
		ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
		grid.forEach(x -> {
			if (x instanceof Explosion) {
				if (!previous.contains(s)) {
					previous.add(s);
					GameScene.sumScore(s.getPoints());
					if (s instanceof Enemy) ((Enemy) s).setDestroyed(true);
					GameScene.grid[posX][posY].remove((Sprite) s);
					pane.getChildren().remove((Sprite) s);
				}
			}
		});
	}
}
