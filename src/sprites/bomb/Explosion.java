package sprites.bomb;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class Explosion extends Sprite {
	private boolean shouldExplode = true; 
	
	public Explosion(Scene scene) {
		super(scene);
		Image image = new Image("/res/explosion.gif");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
	}

	@Override
	public void run() {
		toFront();
	}

	public void setShouldExplode(boolean shouldExplode) {
		this.shouldExplode = shouldExplode;
	}
	
	public boolean getShouldExplode() {
		return shouldExplode;
	}
}
