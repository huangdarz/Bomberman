package sprites.wall;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.base.Sprite;
import sprites.capability.Destroyable;
import sprites.capability.Points;

/**
 * @author Darin Huang
 */
public class BreakableWall extends Sprite implements Destroyable, Points {
	
	public static int points;
	
	/**
	 * Creates a new Sprite
	 * @param scene The scene the Sprite is in.
	 */
	public BreakableWall(Scene scene) {
		super(scene);
		Image image = new Image("/res/breakable_walls.png");
		setImage(image);
		points = 1;
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
		checkDestruction(positionX, positionY, (Points) this, getPane());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPoints() {
		return points;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPoints(int points) {
		BreakableWall.points = points;
	}
	
}