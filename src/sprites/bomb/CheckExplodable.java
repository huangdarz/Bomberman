package sprites.bomb;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import scenes.GameScene;
import sprites.Sprite;

public class CheckExplodable {
	
	ArrayList<Sprite>[][] grid;
	
	LinkedList<Sprite> explodables;
	
	boolean hasExplosion;
	
	Pane pane;
	
	public CheckExplodable(Pane pane) {
		grid = GameScene.grid;
		explodables = new LinkedList<Sprite>();
		hasExplosion = false;
		this.pane = pane;
	}
	
	public void check() {
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				for (Sprite s : grid[c][r]) {
					if (s instanceof Explosion) {
						hasExplosion = true;
					}
				}
				for (Sprite s : grid[c][r]) {
					if (s instanceof Explodable && hasExplosion) {
						explodables.add(s);
					}
				}
				hasExplosion = false;
				grid[c][r].removeAll(explodables);
				pane.getChildren().removeAll(explodables);
				explodables.clear();
				
			}
		}
	}
}
