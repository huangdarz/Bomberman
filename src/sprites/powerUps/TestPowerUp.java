package sprites.powerUps;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

/** 
 * The Location generation and checking if free for a power-up  
 * @author Michael Legovich 
 */
public class TestPowerUp extends Sprite {
	static Random rand = new Random();
	
	boolean locationFreeX = false;
	boolean locationFreeY = false ;
	static boolean ifSet = false;
	static int amount = rand.nextInt(5);
	static int currentAmount = 0;
	static int randomLocationX;
	static int randomLocationY;
	
	static int existingLoc[][] = new int[amount][1];
	
	public TestPowerUp(Scene scene) {
		super(scene);
		Image image = new Image("/res/player.png");
		setImage(image);
		setFitWidth(50);
		setFitHeight(50);
	}
	
	@Override
	public void run() {
		initiateAmountAndLocation();
	}

	
	private void locationGeneration() {
		if (locationFreeX == false || locationFreeY == false) {
			randomLocationX = rand.nextInt(13);
			randomLocationY = rand.nextInt(11);
			System.out.println("Random Location X: "+((randomLocationX*50)+50));
			System.out.println("Random Location Y: "+((randomLocationY*50)+50));
			System.out.println("Amount: "+amount);
		}
	}
	
	private void checkLocationFree() {
		while (randomLocationX % 2 != 0 && randomLocationY % 2 != 0) {
			locationGeneration();
		}
		
		locationFreeX = true;
		locationFreeY = true;
	}	

	private void amountAndLocation() {
		for (int i = 0; i <= amount; i++) {
			locationGeneration();
			checkLocationFree();
			createNextPowerUp();
			
			locationFreeX = false;
			locationFreeY = false;
		}
	}
	
	private void initiateAmountAndLocation() {
		if (ifSet == false) {
			amountAndLocation();
			locationSetting();
			ifSet = true;
			System.out.println(ifSet);
		}
	}
	
	private void locationSetting() {
		relocate(((randomLocationX*50)+50), ((randomLocationY*50)+50));
		System.out.println("Yes!!");
	}
	
	private void createNextPowerUp() {
		if (currentAmount < amount) {
			TestPowerUp nextPowerUp = new TestPowerUp(getScene());
			nextPowerUp.relocate(((randomLocationX*50)+50), ((randomLocationY*50)+50));
			getPane().getChildren().add(nextPowerUp);
			System.out.println("--------------------------");
			currentAmount++;
		}
		
	}
	
}

