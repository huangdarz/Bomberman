package sprites.differentPowerUps;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.RandomLocation;
import sprites.Sprite;

public class InfiniteBombsPowerUp extends Sprite {
	static RandomLocation r;
	
	public InfiniteBombsPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/sample_blue.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
		r = new RandomLocation(this);
	}

	@Override
	public void run() {
		r.initiateAmountAndLocation();
		spawnNextInfBombPowerUp();
	}

/**
 * Generating a new image for the power up, using the location generated in RandomLocation
 */	
	public void spawnNextInfBombPowerUp() {
		if (r.currentAmountOfPowerUps < r.amountOfPowerUps) {
			InfiniteBombsPowerUp inf = new InfiniteBombsPowerUp(getScene());
			inf.relocate(((r.randomLocationX*50)+55), ((r.randomLocationY*50)+55));
			getPane().getChildren().add(inf);
			r.currentAmountOfPowerUps++;
			System.out.println(r.currentAmountOfPowerUps);
			System.out.println(r.amountOfPowerUps);
		}
	}		
}
