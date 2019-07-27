package sprites.differentPowerUps;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.base.Sprite;
import sprites.type.Power;

public class InfiniteBombsPowerUp extends Sprite implements Power {
	
	public InfiniteBombsPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/inf_bombs.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
	}

	@Override
	public void run() {
		toBack();
	}
}
