package scenes;

import application.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class BaseScene extends Scene implements GameLoop {
	AnimationTimer loop;
	public BaseScene(Pane root, double width, double height) {
		super(root, width, height);
		loop = loop();
		loop.start();
	}

}
