package level;

import javafx.scene.Scene;

public interface Level {
	
	/**
	 * Main handler for the creation of the Sprites.
	 * @param scene The scene the sprite is in
	 */
	public default void create(Scene scene) {
		createWalls(scene);
		createPowerUps(scene);
		createMobs(scene);
	}
	
	/**
	 * Method to create the walls.
	 * @param scene The scene the sprite is in
	 */
	public void createWalls(Scene scene);
	
	/**
	 * Handler for creating power ups.
	 * @param scene The scene the sprite is in
	 */
	public default void createPowerUps(Scene scene) {
		createDuo(scene);
		createBig(scene);
		createInf(scene);
	}
	
	/**
	 * Method to create DoublePointsPowerUp
	 * @param scene The scene the sprite is in
	 */
	public void createDuo(Scene scene);
	
	/**
	 * Method to create BiggerBombsPowerUp
	 * @param scene The scene the sprite is in
	 */
	public void createBig(Scene scene);
	
	/**
	 * Method to create InfiniteBombsPowerUp
	 * @param scene The scene the sprite is in
	 */
	public void createInf(Scene scene);
	
	/**
	 * Method to create Mobs
	 * @param scene The scene the sprite is in
	 */
	public void createMobs(Scene scene);
}
