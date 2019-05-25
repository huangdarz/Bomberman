package sprites;

import javafx.scene.image.Image;

public class TestSprite extends BaseSprite{
	public TestSprite() {
		Image image = new Image("https://images.pexels.com/photos/414612/"
				+ "pexels-photo-414612.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
		this.setImage(image);
		this.setPreserveRatio(true);
		this.relocate(640 - image.getWidth()/2, 360 - image.getHeight()/2);
	}
	
	@Override
	public void run() {
		this.setTranslateX(this.getTranslateX()-1);
	}

}
