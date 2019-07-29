package sprites.differentPowerUps;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.base.Sprite;
import sprites.capability.Destroyable;
import sprites.type.Power;

/**
 * @author Michael Legovich
 */
public class InfiniteBombsPowerUp extends Sprite implements Power, Destroyable {
	
	/**
	 * {@inheritDoc}
	 */
	public InfiniteBombsPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/inf_bombs.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		toBack();
		evaluatePosition();
		checkDestruction(positionX, positionY, this, getPane());
	}
}
