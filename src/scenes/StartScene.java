package scenes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class StartScene extends BaseScene {

	public Button startButton;
	
	public StartScene(Pane root, double width, double height) {
		super(root, width, height);
		getPane().setBackground(new Background(new BackgroundImage(new Image("/res/placeholder_image.png", width, height, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		startButton = new Button("START GAME");
		startButton.setBackground(new Background(new BackgroundImage(new Image("/res/placeholder_image.png", 250, 100, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		startButton.relocate((width/2d) - (startButton.getWidth()/2d), height/2d);
		getPane().getChildren().add(startButton);
	}

	@Override
	public void run() {
		
	}

	@Override
	public void userInput() {
		
	}

}
