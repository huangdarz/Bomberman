package sprites.differentPowerUps;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class BiggerBombsPowerUp extends Sprite {
	
	public BiggerBombsPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/sample_red.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
	}

	@Override
	public void run() {
		
	}

}
