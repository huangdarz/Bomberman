package sprites.bomb;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import sprites.Sprite;

public class Bomb extends Sprite {
	private int range = 2;
	
	public Bomb(Scene scene) {
		super(scene);
		Image image = new Image("/res/bomb.png");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
	}

	@Override
	public void run() {
		
	}
	
	public void explode() {
		long startTime = System.currentTimeMillis();
		long finishTime = System.currentTimeMillis();
		long elapsedTime = finishTime = startTime;
		
		do {
			finishTime = System.currentTimeMillis();
			elapsedTime = finishTime - startTime;
		} while (elapsedTime < 1000);
		
		int multiplier = 1;
		ArrayList<Explosion> blasts = new ArrayList<Explosion>();
		for (int x = 0; x < range*4; x++) {
			blasts.add(new Explosion(getScene()));
			if (x < range) {
				blasts.get(x).relocate(getLayoutX()-getFitWidth()*multiplier, getLayoutY());
			} else if (x < range*2) {
				blasts.get(x).relocate(getLayoutX(), getLayoutY()-getFitHeight()*multiplier);
			} else if (x < range*3) {
				blasts.get(x).relocate(getLayoutX()+getFitWidth()*multiplier, getLayoutY());
			} else {
				blasts.get(x).relocate(getLayoutX(), getLayoutY()+getFitHeight()*multiplier);
			}
			blasts.get(x).toBack();
			multiplier++;
			if (multiplier > range) multiplier = 1;
		}
		blasts.add(new Explosion(getScene()));
		blasts.get(range*4).relocate(getLayoutX(), getLayoutY());
		Platform.runLater(() -> {
			getPane().getChildren().addAll(blasts);
			getPane().getChildren().remove(this);
		});
	}

}
