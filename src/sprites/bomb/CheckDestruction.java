package sprites.bomb;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.Lives;
import sprites.Player;
import sprites.Points;
import sprites.Sprite;

public class CheckDestruction {
	boolean hasExplosion;
	Player previousPlayer;
	ArrayList<Sprite> previous;
	
	public CheckDestruction() {
		hasExplosion = false;
		previousPlayer = null;
		previous = new ArrayList<Sprite>();
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
	public void check(int posX, int posY, int pointsAwarded, Sprite s, Pane pane) {
		ArrayList<Sprite> grid = (ArrayList<Sprite>) GameScene.grid[posX][posY].clone();
		grid.forEach(x -> {
			if (x instanceof Explosion) {
				if (!previous.contains(s)) {
					previous.add(s);
					GameScene.sumScore(((Points) s).getPoints());
					GameScene.grid[posX][posY].remove(s);
					pane.getChildren().remove(s);
				}
				
			}
		});
	}
	
}
