package scenes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class StartScene extends BaseScene {

	public Button startButton;
	
	/**
	 * Default constructor for StartScene
	 * @param root {@inheritDoc}
	 * @param width {@inheritDoc}
	 * @param height {@inheritDoc}
	 */
	public StartScene(Pane root, double width, double height) {
		super(root, width, height);
		getPane().setBackground(new Background(new BackgroundImage(new Image("/res/start_screen.png", width, height, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		startButton = new Button();
		startButton.setPrefWidth(227);
		startButton.setPrefHeight(56);
		startButton.setBackground(new Background(new BackgroundImage(new Image("/res/start_button.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		startButton.relocate((width/2d) - (startButton.getWidth()/2d)-113.5, height/2d+70);
		getPane().getChildren().add(startButton);
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
