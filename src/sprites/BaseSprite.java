package sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import scenes.GameLoop;

public abstract class BaseSprite extends ImageView implements GameLoop {
	public BaseSprite() {
		super();
		loop();
	}
	
	public BaseSprite(Image image) {
		super(image);
		loop();
	}
	
	public BaseSprite(String url) {
		super(url);
		loop();
	}
}
