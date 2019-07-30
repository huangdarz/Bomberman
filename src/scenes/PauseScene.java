package scenes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class PauseScene extends BaseScene {

	public Button resumeButton;
	
	/**
	 * Creates a scene for a specific root Node with a specific size
	 * @param root The root node of the scene graph
	 * @param width The width of the scene
	 * @param height The height of the scene
	 */
	public PauseScene(Pane root, double width, double height) {
		super(root, width, height);
		getPane().setBackground(new Background(new BackgroundImage(new Image("/res/placeholder_image.png", width, height, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		resumeButton = new Button("RESUME");
		resumeButton.setBackground(new Background(new BackgroundImage(new Image("/res/placeholder_image.png", 250, 100, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		resumeButton.relocate((width/2d) - (resumeButton.getWidth()/2d), height/2d);
		getPane().getChildren().add(resumeButton);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void userInput() {
		
	}

}
