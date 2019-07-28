package scenes;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class EndScene extends BaseScene {

	private Button quitBtn;
	
	public EndScene(Pane root, double width, double height) {
		super(root, width, height);
		
		quitBtn = new Button("Quit");
		getPane().setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		setFill(null);
		
		getPane().getChildren().add(quitBtn);
		
		quitBtn.setOnAction(e -> {
			Main.end.close();
			Main.primaryStage.close();
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void userInput() {
		
	}

}
