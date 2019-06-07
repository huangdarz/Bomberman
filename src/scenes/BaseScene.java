package scenes;

import java.util.ArrayList;
import java.util.HashMap;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sprites.Sprite;

public abstract class BaseScene extends Scene implements GameLoop {
	
	AnimationTimer loop;
	
	public BaseScene(Pane root, double width, double height) {
		super(root, width, height);
		loop = loop();
		loop.start();
		userInput();
	}
	
	public abstract void userInput();
	
	public Pane getPane() {
		return (Pane) getRoot();
	}
	
}
