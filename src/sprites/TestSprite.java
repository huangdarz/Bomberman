package sprites;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public class TestSprite extends Sprite {

	public TestSprite(Scene scene) {
		super(scene);
		Image image = new Image("/res/test.jpg");
		this.setImage(image);
		this.setPreserveRatio(true);
		this.relocate(640 - image.getWidth()/2, 360 - image.getHeight()/2);
	}
	
	@Override
	public void run() {
		
	}
	
	
}
