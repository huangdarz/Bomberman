package sprites.bomb;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class Explosion extends Sprite {
	public Explosion(Scene scene) {
		super(scene);
		Image image = new Image("/res/explosion.gif");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
	}

	@Override
	public void run() {
		toFront();
	}

}
