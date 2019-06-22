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
	
	ArrayList<Sprite> activeSprites = new ArrayList<Sprite>();
	
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
		for (int x = 0; x < range*4; x++) {
			blasts.add(new Explosion(getScene()));
			if (x < range) {
				checkBounds((getLayoutX()-getFitWidth()*multiplier)/50, getLayoutY()/50, blasts.get(x));
				blasts.get(x).relocate(getLayoutX()-getFitWidth()*multiplier, getLayoutY());
				checkExplosionGrid((getLayoutX()-getFitWidth()*multiplier)/50, getLayoutY()/50, blasts.get(x));
				checkEmpty(blasts.get(x));
				if (x == range - 1) boundExplosion = true;
			} else if (x < range*2) {
				checkBounds((getLayoutX())/50, (getLayoutY()-getFitHeight()*multiplier)/50, blasts.get(x));
				blasts.get(x).relocate(getLayoutX(), getLayoutY()-getFitHeight()*multiplier);
				checkExplosionGrid((getLayoutX())/50, (getLayoutY()-getFitHeight()*multiplier)/50, blasts.get(x));
				checkEmpty(blasts.get(x));
				if (x == range*2 - 1) boundExplosion = true;
			} else if (x < range*3) {
				checkBounds((getLayoutX()+getFitWidth()*multiplier)/50, getLayoutY()/50, blasts.get(x));
				blasts.get(x).relocate(getLayoutX()+getFitWidth()*multiplier, getLayoutY());
				checkExplosionGrid((getLayoutX()+getFitWidth()*multiplier)/50, getLayoutY()/50, blasts.get(x));
				checkEmpty(blasts.get(x));
				if (x == range*3 - 1) boundExplosion = true;
			} else {
				checkBounds((getLayoutX())/50, (int)(getLayoutY()+getFitHeight()*multiplier)/50, blasts.get(x));
				blasts.get(x).relocate(getLayoutX(), getLayoutY()+getFitHeight()*multiplier);
				checkExplosionGrid((getLayoutX())/50, (getLayoutY()+getFitHeight()*multiplier)/50, blasts.get(x));
				checkEmpty(blasts.get(x));
				if (x == range*4 - 1) boundExplosion = true;
			}
			blasts.get(x).toBack();
			multiplier++;
			if (multiplier > range) multiplier = 1;
		}
		blasts.add(new Explosion(getScene()));
		blasts.get(range*4).relocate(getLayoutX(), getLayoutY());
		
		LinkedList<Explosion> toExplode = createToExplode(blasts);
		
		Platform.runLater(() -> {
			getPane().getChildren().addAll(toExplode);
			getPane().getChildren().remove(this);
		});
		
		timer(750);
		
		Platform.runLater(() -> getPane().getChildren().removeAll(blasts));
		setBombsPlaced(getBombsPlaced()-1);
	}
	
	private void checkBounds(double x, double y, Explosion e) {
		if (boundExplosion) {
			activeSprites = GameScene.getInGrid((int)x, (int)y);
			boundExplosion = false;
		}
		checkEmpty(e);
	}
	
	private void checkEmpty(Explosion e) {
		if (!activeSprites.isEmpty()) {
			e.setShouldExplode(false);
		}
	}
	
	private void checkExplosionGrid(double x, double y, Explosion ex) {
		try {
			activeSprites = GameScene.getInGrid((int)x, (int)y);
		} catch(ArrayIndexOutOfBoundsException e) {
			ex.setShouldExplode(false);
		}
	}
	
	private LinkedList<Explosion> createToExplode(ArrayList<Explosion> b) {
		LinkedList<Explosion> toExplode = new LinkedList<Explosion>();
		for (Explosion e : b) {
			if (e.getShouldExplode()) {
				toExplode.add(e);
			}
		}
		return toExplode;
	}
	
	private void timer(int timeMillis) {
		long startTime = System.currentTimeMillis();
		long finishTime = System.currentTimeMillis();
		long elapsedTime = finishTime - startTime;
		
		while(elapsedTime < timeMillis) {
			finishTime = System.currentTimeMillis();
			elapsedTime = finishTime - startTime;
		}
	}
	
	public static int getBombsPlaced() {return bombsPlaced;}
	public static void setBombsPlaced(int bombsPlaced) {Bomb.bombsPlaced = bombsPlaced;}
	
	public static int getMaxNumBombs() {return maxNumBombs;}
	public static void setMaxNumBombs(int maxNumBombs) {Bomb.maxNumBombs = maxNumBombs;}

}
