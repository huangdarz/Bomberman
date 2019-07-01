package sprites.differentPowerUps;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.RandomLocation;
import sprites.Sprite;

public class BiggerBombsPowerUp extends Sprite {
	static RandomLocation r;
	
	public BiggerBombsPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/sample_red.png");
		setImage(image);
		setFitWidth(40);
		setFitHeight(40);
		r = new RandomLocation(this);
	}

	@Override
	public void run() {
		r.initiateAmountAndLocation();
		spawnNextBigBombPowerUp();
	}

/**
 * Generating a new image for the power up, using the location generated in RandomLocation
 */	
	public void spawnNextBigBombPowerUp() {
		if (r.currentAmountOfPowerUps < r.amountOfPowerUps) {
			BiggerBombsPowerUp big = new BiggerBombsPowerUp(getScene());
			big.relocate(((r.randomLocationX*50)+55), ((r.randomLocationY*50)+55));
			getPane().getChildren().add(big);
			r.currentAmountOfPowerUps++;
			System.out.println(r.currentAmountOfPowerUps);
			System.out.println(r.amountOfPowerUps);
		}
	}		
}
