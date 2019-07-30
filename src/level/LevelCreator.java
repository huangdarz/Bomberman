package level;

import javafx.scene.Scene;

/**
 * Handler for level creation. 
 * @author Darin Huang
 */
public class LevelCreator {
	private int level;
	
	/**
	 * Private constructor to choose level.
	 * @param level The level to create.
	 */
	private LevelCreator(int level) {
		this.level = level;
	}
	
	/**
	 * Static method to create a new LevelCreator object with chosen level
	 * @param level The level to create.
	 * @return A new LevelCreator object
	 */
	public static LevelCreator level(int level) {
		return new LevelCreator(level);
	}
	
	/**
	 * Public handler for level creation. 
	 * @param scene The scene the Sprite is added to.
	 */
	public void create(Scene scene) {
		levelChosen(scene);
	}
	
	/**
	 * Creates a new level based off the chosen level.
	 * @param scene The scene the Sprite is added to.
	 */
	private void levelChosen(Scene scene) {
		switch (level) {
			case 1: new LevelOne().create(scene);
				
			default: break;
		}
	}
}
