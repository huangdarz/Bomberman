package application;
	
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.GameScene;

public class Main extends Application {
	Pane pane;
	GameScene game;
	
	@Override
	public void start(Stage primaryStage) {
		pane = new Pane();
		game = new GameScene(pane, 1280, 720);
		
		primaryStage.setScene(game);
		primaryStage.setTitle("Bomberman");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	@Override
	public void stop() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
