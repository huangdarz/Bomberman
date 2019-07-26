package sprites.bomb;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.Mob;
import sprites.Player;
import sprites.Sprite;

public class CheckExplodable {
	
	ArrayList<Sprite>[][] grid;
	
	ArrayList<Sprite> explodables;
	
	ArrayList<Sprite> previous;
	
	boolean hasExplosion;
	
	Pane pane;
	
	Player previousPlayer;
	
	public CheckExplodable(Pane pane) {
		grid = GameScene.grid;
		explodables = new ArrayList<Sprite>();
		previous = new ArrayList<Sprite>();
		hasExplosion = false;
		this.pane = pane;
	}
	
	public void check() {
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				grid[c][r].forEach(s -> {if (s instanceof Explosion) hasExplosion = true;});
				grid[c][r].forEach(s -> {if (s instanceof Explodable && hasExplosion) explodables.add(s);});
				explodables.forEach(e -> {
					if (e instanceof Mob && hasExplosion) {
						if (!previous.contains(e)) {
							previous.add(e);
							GameScene.sumScore(10);
						}
					}
					if (e instanceof Player && !e.equals(previousPlayer)) {
						previousPlayer = (Player) e;
						Player.setLives(Player.getLives()-1);
					}
				});
				explodables.removeIf(e -> (e instanceof Player && Player.getLives() > 0));
				grid[c][r].removeAll(explodables);
				hasExplosion = false;
			}
		}
		if (!explodables.isEmpty()) {
			Platform.runLater(() -> {
				pane.getChildren().removeAll(explodables);
				explodables.clear();
			});
		}
		
	}
}
