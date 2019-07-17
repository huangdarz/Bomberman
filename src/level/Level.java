package level;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface Level {
	public default void create(Scene scene, Pane pane) {
		createWalls(scene, pane);
		createPowerUps(scene, pane);
		createMobs(scene, pane);
	}
	
	public void createWalls(Scene scene, Pane pane);
	
	public default void createPowerUps(Scene scene, Pane pane) {
		createDuo(scene, pane);
		createBig(scene, pane);
		createInf(scene, pane);
	}
	
	public void createDuo(Scene scene, Pane pane);
	
	public void createBig(Scene scene, Pane pane);
	
	public void createInf(Scene scene, Pane pane);
	
	public void createMobs(Scene scene, Pane pane);
}
