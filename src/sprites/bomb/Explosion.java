package sprites.bomb;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class Explosion extends Sprite {
	public Explosion(Scene scene) {
		super(scene);
		Image image = new Image("/res/explosion_temp.png");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
		getLoop().stop();
	}

	@Override
	public void run() {
		
	}

}
