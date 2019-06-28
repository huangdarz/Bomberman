package sprites.randomlyLocated;

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
		setFitWidth(50);
		setFitHeight(50);
		r = new RandomLocation(this);
	}

	@Override
	public void run() {
		r.initiateAmountAndLocation();
		spawnNextInfBombPowerUp();
	}

/**
 * Generating a new image for the mob, using the location generated in RandomLocationGeneration
 */
//	@Override
//	protected void createNextPowerUp() {
//		if (currentAmountOfPowerUps < amountOfPowerUps) {
//			InfiniteBombsPowerUp nextInfPowUp = new InfiniteBombsPowerUp(getScene());
//			nextInfPowUp.relocate(((randomLocationX*50)+55), ((randomLocationY*50)+55));
//			getPane().getChildren().add(nextInfPowUp);
//			currentAmountOfPowerUps++;
//		}
//	}
	
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
