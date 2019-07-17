package sprites.wall;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class BreakableWall extends Sprite {

	public BreakableWall(Scene scene) {
		super(scene);
		Image image = new Image("/res/cobble.png");
		setImage(image);
		setFitWidth(50);
		setFitHeight(50);
	}

	@Override
	public void run() {
		toFront();
	}

}
