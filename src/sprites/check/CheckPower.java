package sprites.check;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.Mob;
import sprites.base.Sprite;
import sprites.bomb.Bomb;
import sprites.differentPowerUps.BiggerBombsPowerUp;
import sprites.differentPowerUps.DoublePointsPowerUp;
import sprites.differentPowerUps.InfiniteBombsPowerUp;
import sprites.type.Power;

/**
 * Class that provides checking of collision with power ups
 * @author Darin Huang
 */
public class CheckPower implements Checker {

	private ArrayList<Sprite> previousPowers;
	
	public CheckPower() {
		previousPowers = new ArrayList<Sprite>();
	}
	
	/**
	 * Checks collision with power up
	 * @param posX The x position of the Sprite in terms of GameScene.grid
	 * @param posY The y position of the Sprite in terms of GameScene.grid
	 * @param sprite The sprite colliding with the power ups
	 * @param pane The pane the Sprite is in.
	 */
	@SuppressWarnings("unchecked")
	public void check(int posX, int posY, Sprite sprite, Pane pane) {
		ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
		grid.forEach(x -> {
			if (x instanceof Power) {
				if (x instanceof BiggerBombsPowerUp && !previousPowers.contains(x)) {
					previousPowers.add(x);
					Bomb.setRange(2);
					GameScene.grid[posX][posY].remove(x);
					pane.getChildren().remove(x);
					System.out.println("Found Bigger");
				} else if (x instanceof DoublePointsPowerUp && !previousPowers.contains(x)) {
					previousPowers.add(x);
					Mob.setScorePotential(Mob.getScorePotential()*2);
					GameScene.grid[posX][posY].remove(x);
					pane.getChildren().remove(x);
					System.out.println("Found Double");
				} else if (x instanceof InfiniteBombsPowerUp && !previousPowers.contains(x)) {
					previousPowers.add(x);
					Bomb.setMaxNumBombs(200);
					GameScene.grid[posX][posY].remove(x);
					pane.getChildren().remove(x);
					System.out.println("Found Infinite");
				}
			}
		});
	}
}
