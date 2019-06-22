package sprites.bomb;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import scenes.GameScene;
import sprites.Sprite;
import sprites.wall.BreakableWall;
import sprites.wall.UnbreakableWall;

public class Bomb extends Sprite {
	private int range = 2;
	private boolean boundExplosion = true;
	
	private static int maxNumBombs = 1;
	private static int bombsPlaced = 0;
	
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
		int multiplier = 1;
		ArrayList<Explosion> blasts = new ArrayList<Explosion>();
		ArrayList<Sprite> activeSprites = new ArrayList<Sprite>();
		for (int x = 0; x < range*4; x++) {
			blasts.add(new Explosion(getScene()));
			if (x < range) {
//				blasts.get(x).relocate(getLayoutX()-getFitWidth()*multiplier, getLayoutY());
				if (boundExplosion) {
					activeSprites = GameScene.getInGrid((int)(getLayoutX()-getFitWidth()*multiplier)/50, (int)getLayoutY()/50);
					boundExplosion = false;
				}
				System.out.println("Grid X: "+(int)(getLayoutX()-getFitWidth()*multiplier)/50);
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				blasts.get(x).relocate(getLayoutX()-getFitWidth()*multiplier, getLayoutY());
				try {
					activeSprites = GameScene.getInGrid((int)(getLayoutX()-getFitWidth()*multiplier)/50, (int)getLayoutY()/50);
				} catch(ArrayIndexOutOfBoundsException e) {
					blasts.get(x).setShouldExplode(false);
				}
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				if (x == range - 1) boundExplosion = true;
			} else if (x < range*2) {
//				blasts.get(x).relocate(getLayoutX(), getLayoutY()-getFitHeight()*multiplier);
				if (boundExplosion) {
					activeSprites = GameScene.getInGrid((int)(getLayoutX())/50, (int)(getLayoutY()-getFitHeight()*multiplier)/50);
					boundExplosion = false;
				}
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				blasts.get(x).relocate(getLayoutX(), getLayoutY()-getFitHeight()*multiplier);
				try {
					activeSprites = GameScene.getInGrid((int)(getLayoutX())/50, (int)(getLayoutY()-getFitHeight()*multiplier)/50);
				} catch(ArrayIndexOutOfBoundsException e) {
					blasts.get(x).setShouldExplode(false);
				}
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				if (x == range*2 - 1) boundExplosion = true;
			} else if (x < range*3) {
//				blasts.get(x).relocate(getLayoutX()+getFitWidth()*multiplier, getLayoutY());
				if (boundExplosion) {
					activeSprites = GameScene.getInGrid((int)(getLayoutX()+getFitWidth()*multiplier)/50, (int)getLayoutY()/50);
					boundExplosion = false;
				}
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				blasts.get(x).relocate(getLayoutX()+getFitWidth()*multiplier, getLayoutY());
				try {
					activeSprites = GameScene.getInGrid((int)(getLayoutX()+getFitWidth()*multiplier)/50, (int)getLayoutY()/50);
				} catch(ArrayIndexOutOfBoundsException e) {
					blasts.get(x).setShouldExplode(false);
				}
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				if (x == range*3 - 1) boundExplosion = true;
			} else {
//				blasts.get(x).relocate(getLayoutX(), getLayoutY()+getFitHeight()*multiplier);
				if (boundExplosion) {
					activeSprites = GameScene.getInGrid((int)(getLayoutX())/50, (int)(getLayoutY()+getFitHeight()*multiplier)/50);
					boundExplosion = false;
				}
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				blasts.get(x).relocate(getLayoutX(), getLayoutY()+getFitHeight()*multiplier);
				try {
					activeSprites = GameScene.getInGrid((int)(getLayoutX())/50, (int)(getLayoutY()+getFitHeight()*multiplier)/50);
				} catch(ArrayIndexOutOfBoundsException e) {
					blasts.get(x).setShouldExplode(false);
				}
				if (!activeSprites.isEmpty()) {
					blasts.get(x).setShouldExplode(false);
				}
				if (x == range*4 - 1) boundExplosion = true;
			}
			blasts.get(x).toBack();
			multiplier++;
			if (multiplier > range) multiplier = 1;
		}
		blasts.add(new Explosion(getScene()));
		blasts.get(range*4).relocate(getLayoutX(), getLayoutY());
		
		LinkedList<Explosion> toExplode = new LinkedList<Explosion>();
		for (Explosion e : blasts) {
			if (e.getShouldExplode()) {
				toExplode.add(e);
			}
		}
		
		Platform.runLater(() -> {
			getPane().getChildren().addAll(toExplode);
			getPane().getChildren().remove(this);
		});
		
		long startTime = System.currentTimeMillis();
		long finishTime = System.currentTimeMillis();
		long elapsedTime = finishTime - startTime;
		
		while(elapsedTime < 750) {
			finishTime = System.currentTimeMillis();
			elapsedTime = finishTime - startTime;
		}
		
		Platform.runLater(() -> getPane().getChildren().removeAll(blasts));
		setBombsPlaced(getBombsPlaced()-1);
	}
	
	public static int getBombsPlaced() {return bombsPlaced;}
	public static void setBombsPlaced(int bombsPlaced) {Bomb.bombsPlaced = bombsPlaced;}
	
	public static int getMaxNumBombs() {return maxNumBombs;}
	public static void setMaxNumBombs(int maxNumBombs) {Bomb.maxNumBombs = maxNumBombs;}

}
