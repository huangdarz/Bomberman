package level;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LevelCreator {
	private int level;
	
	private LevelCreator(int level) {
		this.level = level;
	}
	
	public static LevelCreator level(int level){return new LevelCreator(level);}
	
	public void create(Scene scene, Pane pane) {
		levelChosen(scene, pane);
	}
	
	private void levelChosen(Scene scene, Pane pane) {
		switch (level) {
			case 1: new LevelOne().create(scene, pane);
				
			default: break;
		}
	}
}
