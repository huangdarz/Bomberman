package scenes;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class BaseScene extends Scene implements GameLoop {
	
	AnimationTimer loop;
	
	/**
	 * Default constructor for BaseScene
	 * @param root {@inheritDoc}
	 * @param width {@inheritDoc}
	 * @param height {@inheritDoc}
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
