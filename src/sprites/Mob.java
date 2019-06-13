package sprites;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Mob extends Sprite {
	boolean getPosition = true;
	double currentX;
	double currentY;
	int speed = 5;
	int randomLength;
	int randomDirection;
	
	Random rand = new Random();
	
	public Mob(Scene scene) {
		super(scene);
		Image image = new Image("/res/player.png");
		setImage(image);
		setFitWidth(50);
		setFitHeight(50);
		relocate(50, 50);
	}

	@Override
	public void run() {
		if (getPosition == true) {
			currentX = getLayoutX();
			currentY = getLayoutY();
			randomLength = rand.nextInt(3);
			randomDirection = rand.nextInt(4);
			getPosition = false;
			System.out.println("getPosition!!!");
		}
		
		switch (randomLength) {
		case 0:
			if (Math.abs(getLayoutX()-currentX) >= 50 || Math.abs(getLayoutY()-currentY) >= 50) {
				getPosition = true;
			} 
			System.out.println("randomLength: 0");
			break;
		case 1:
			if (Math.abs(getLayoutX()-currentX) >= 100 || Math.abs(getLayoutY()-currentY) >= 100) {
				getPosition = true;
			}
			System.out.println("randomLength: 1"); 
			break;
		case 2:
			if (Math.abs(getLayoutX()-currentX) >= 150 || Math.abs(getLayoutY()-currentY) >= 150) {
				getPosition = true;
			} 
			System.out.println("randomLength: 2");
			break;
}
		
		switch (randomDirection) {
			case 0:
				move(0, -speed);
				break;
			case 1:
				move(0, speed);
				break;
			case 2:
				move(speed, 0);
				break;
			case 3:
				move(-speed, 0);
				break;
		}
	}

}
