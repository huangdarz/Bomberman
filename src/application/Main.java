package application;
	
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.EndScene;
import scenes.GameScene;
import scenes.PauseScene;
import scenes.StartScene;
import sprites.base.Sprite;

public class Main extends Application {
	
	public static GameScene game;
	public static StartScene start;
	public static PauseScene pause;
	public static EndScene end;
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		game = new GameScene(new Pane(), GameScene.grid.length*50-12, GameScene.grid[0].length*50-12);
		start = new StartScene(new Pane(), GameScene.grid.length*50-12, GameScene.grid[0].length*50-12);
		pause = new PauseScene(new Pane(), GameScene.grid.length*50-12, GameScene.grid[0].length*50-12);
		end = new EndScene(new Pane(), GameScene.grid.length*50-12, GameScene.grid[0].length*50-12);
		
//		primaryStage.setScene(game);
		primaryStage.setScene(start);
		start.startButton.setOnAction(e -> {
			primaryStage.setScene(game);
		});
		pause.resumeButton.setOnAction(e -> {
			primaryStage.setScene(game);
			for(int c = 0; c < GameScene.grid.length; c++) {
				for(int r = 0; r < GameScene.grid[0].length; r++) {
					GameScene.grid[c][r].forEach(x -> {
						if (x instanceof GameLoop && x instanceof Sprite) {
							x.getLoop().start();
						}
					});
				}
			}
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
