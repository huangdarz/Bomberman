package sprites.differentPowerUps;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class InfiniteBombsPowerUp extends Sprite {
	
	public InfiniteBombsPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/sample_blue.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
	}

	@Override
	public void run() {
		
	}
}
