package sprites.wall;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class UnbreakableWall extends Sprite {

	public UnbreakableWall(Scene scene, double x, double y) {
		super(scene);
		Image image = new Image(/*"/res/bomb.png"*/"/res/sample_red.png");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
		setLayoutX(x);
		setLayoutY(y);
	}

	@Override
	public void run() {
		
	}

}
