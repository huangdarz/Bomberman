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
	 * Default constructor for PauseScene
	 * @param root {@inheritDoc}
	 * @param width {@inheritDoc}
	 * @param height {@inheritDoc}
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
