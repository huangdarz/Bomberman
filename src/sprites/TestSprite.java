package sprites;

import javafx.scene.image.Image;

public class TestSprite extends Sprite {

	private TestSprite() {
		Image image = new Image("/res/test.jpg");
		this.setImage(image);
		this.setPreserveRatio(true);
		this.relocate(640 - image.getWidth()/2, 360 - image.getHeight()/2);
	}
	
	public TestSprite(int velocityX, int velocityY) {
		this();
		setVelocityX(velocityX);
		setVelocityY(velocityY);
	}
	
	@Override
	public void run() {
		
	}
	
}
