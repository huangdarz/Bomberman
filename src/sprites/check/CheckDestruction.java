package sprites.check;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.Player;
import sprites.base.Sprite;
import sprites.bomb.Explosion;
import sprites.capability.Lives;
import sprites.capability.Points;
import sprites.type.Enemy;

public class CheckDestruction implements Checker {
	boolean hasExplosion;
	Player previousPlayer;
	ArrayList<Points> previous;
	
	public CheckDestruction() {
		hasExplosion = false;
		previousPlayer = null;
		previous = new ArrayList<Points>();
	}
	
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
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public void check(int posX, int posY, Lives s, Pane pane) {
		hasExplosion = false;
		if (s instanceof Lives) {
			ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
			grid.forEach(x -> {
				if (x instanceof Explosion) {
					if (s.getLives() < 0) {
						GameScene.grid[posX][posY].remove((Sprite) s);
						pane.getChildren().remove(s);
					} else if (!s.equals(previousPlayer)){
						previousPlayer = (Player) s;
						s.setLives(s.getLives()-1);
					}
				}
			});
		} 
		GameScene.grid[posX][posY].forEach(a -> {if (a instanceof Explosion) hasExplosion = true;});
		if (!hasExplosion) previousPlayer = null;
	}
	
	@SuppressWarnings("unchecked")
	public void check(int posX, int posY, Points s, Pane pane) {
		ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
		grid.forEach(x -> {
			if (x instanceof Explosion) {
				if (!previous.contains(s)) {
					previous.add(s);
					GameScene.sumScore(s.getPoints());
					((Enemy) s).setDestroyed(true);
					GameScene.grid[posX][posY].remove((Sprite) s);
					pane.getChildren().remove((Sprite) s);
				}
			}
		});
	}
}
