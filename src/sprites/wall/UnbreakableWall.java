package sprites.wall;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.base.Sprite;

/**
 * @author Mitchell Barker
 */
public class UnbreakableWall extends Sprite {

	/**
	 * Creates a new Sprite
	 * @param scene The scene the Sprite is in.
	 */
	public UnbreakableWall(Scene scene, double x, double y) {
		super(scene);
		Image image = new Image("/res/unbreakable_wall.png");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
		setLayoutX(x);
		setLayoutY(y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		
	}

}
