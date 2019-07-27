package application;
	
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.GameScene;
import scenes.PauseScene;
import scenes.StartScene;

public class Main extends Application {
	
	public static GameScene game;
	public static StartScene start;
	public static PauseScene pause;
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		game = new GameScene(new Pane(), GameScene.grid.length*50-12, GameScene.grid[0].length*50-12);
		start = new StartScene(new Pane(), GameScene.grid.length*50-12, GameScene.grid[0].length*50-12);
		pause = new PauseScene(new Pane(), GameScene.grid.length*50-12, GameScene.grid[0].length*50-12);
		
//		primaryStage.setScene(game);
		primaryStage.setScene(start);
		start.startButton.setOnAction(e -> {
			primaryStage.setScene(game);
		});
		pause.resumeButton.setOnAction(e -> {
			primaryStage.setScene(game);
		});
		primaryStage.setTitle("Bomberman");
		primaryStage.setResizable(false);
		primaryStage.show();
		Main.primaryStage = primaryStage;
	}
	
	@Override
	public void stop() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
