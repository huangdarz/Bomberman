package application;
	
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.GameScene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		GameScene game = new GameScene(pane, 1280, 720);
		primaryStage.setScene(game);
		primaryStage.setTitle("Bomberman");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
