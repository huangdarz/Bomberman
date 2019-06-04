package scenes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sprites.Player;

public class GameScene extends BaseScene {
	Rectangle rect = new Rectangle(50, 50, Paint.valueOf("Red"));
	Player player = new Player(this);
	
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		getPane().getChildren().addAll(rect, player);
	}

	@Override
	public void run() {
		rect.setTranslateX(rect.getTranslateX()+1);
		rect.toFront();
	}

	@Override
	public void userInput() {
		this.setOnKeyPressed(key -> {
			player.moveKeyPressedReleased(key.getCode(), true);
			player.placeBomb(key.getCode());
			player.explodeBomb(key.getCode());
		});
		this.setOnKeyReleased(key -> {
			player.moveKeyPressedReleased(key.getCode(), false);
		});
	}
}
