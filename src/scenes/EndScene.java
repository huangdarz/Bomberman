package scenes;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndScene extends BaseScene {

	private Button quitBtn;
	private Text gameOver, score;
	
	/**
	 * Creates a scene for a specific root Node with a specific size
	 * @param root The root node of the scene graph
	 * @param width The width of the scene
	 * @param height The height of the scene
	 */
	public EndScene(Pane root, double width, double height) {
		super(root, width, height);
		
		quitBtn = new Button("Quit");
		gameOver = new Text("GAME OVER");
		score = new Text();
		
		quitBtn.setPrefWidth(150);
		quitBtn.setPrefHeight(50);
		quitBtn.relocate(width/2 - 75, height*0.6);
		
		gameOver.setFont(Font.font("Verdana", 72));
		gameOver.setFill(Color.WHITE);
		gameOver.relocate(width/2d - gameOver.getBoundsInLocal().getWidth()/2d, height*0.3);
		
		score.setFont(Font.font("Verdana", 36));
		score.setFill(Color.WHITE);
		
		getPane().setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		setFill(null);
		
		getPane().getChildren().addAll(quitBtn, gameOver, score);
		
		quitBtn.setOnAction(e -> {
			Platform.exit();
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		score.setText("Score: " + GameScene.score);
		score.relocate(getWidth()/2d - score.getBoundsInParent().getWidth()/2d, getHeight()*0.45);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void userInput() {
		
	}

}
