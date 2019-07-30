package scenes;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class BaseScene extends Scene implements GameLoop {
	
	AnimationTimer loop;
	
	/**
	 * Creates a scene for a specific root Node with a specific size
	 * @param root The root node of the scene graph
	 * @param width The width of the scene
	 * @param height The height of the scene
	 */
	public BaseScene(Pane root, double width, double height) {
		super(root, width, height);
		loop = loop();
		loop.start();
		userInput();
	}
	
	/**
	 * Abstract method for setting user inputs based off key presses
	 */
	public abstract void userInput();
	
	/**
	 * @return root
	 */
	public Pane getPane() {return (Pane) getRoot();}
	
	
}
