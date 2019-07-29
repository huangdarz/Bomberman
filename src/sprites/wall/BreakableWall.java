package sprites.wall;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.base.Sprite;
import sprites.capability.Destroyable;

/**
 * @author Darin Huang
 */
public class BreakableWall extends Sprite implements Destroyable {

	/**
	 * {@inheritDoc}
	 */
	public BreakableWall(Scene scene) {
		super(scene);
		Image image = new Image("/res/breakable_walls.png");
		setImage(image);
		setFitWidth(50);
		setFitHeight(50);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		toFront();
		evaluatePosition();
		checkDestruction(positionX, positionY, this, getPane());
	}
	
}