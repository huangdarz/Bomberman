package sprites.bomb;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import scenes.GameScene;
import sprites.Sprite;
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
		LinkedList<Explosion> blasts = new LinkedList<Explosion>();
		blastLoop(blasts, multiplier);
		blasts.add(new Explosion(getScene()));
		blasts.getLast().relocate(getLayoutX(), getLayoutY());
		LinkedList<Explosion> toExplode = createToExplode(blasts);
		Platform.runLater(() -> {
			getPane().getChildren().addAll(toExplode);
			getPane().getChildren().remove(this);
		});
		timer(750);
		Platform.runLater(() -> getPane().getChildren().removeAll(blasts));
		setBombsPlaced(getBombsPlaced()-1);
	}
	
	private void blastLoop(LinkedList<Explosion> blasts, int multiplier) {
		for (int x = 0; x < range*4; x++) {
			blasts.add(new Explosion(getScene()));
			if (x < range) {
				calcBomb(getLayoutX()-getFitWidth()*multiplier, getLayoutY(), blasts.getLast());
				if (x == range - 1) boundExplosion = true;
			} else if (x < range*2) {
				calcBomb(getLayoutX(), getLayoutY()-getFitHeight()*multiplier, blasts.getLast());
				if (x == range*2 - 1) boundExplosion = true;
			} else if (x < range*3) {
				calcBomb(getLayoutX()+getFitWidth()*multiplier, getLayoutY(), blasts.getLast());
				if (x == range*3 - 1) boundExplosion = true;
			} else {
				calcBomb(getLayoutX(), getLayoutY()+getFitHeight()*multiplier, blasts.getLast());
				if (x == range*4 - 1) boundExplosion = true;
			}
			blasts.getLast().toBack();
			multiplier++;
			if (multiplier > range) multiplier = 1;
		}
	}
	
	private void calcBomb(double x, double y, Explosion e) {
		checkBounds(x/50, y/50, e);
		e.relocate(x, y);
		checkExplosionGrid(x/50, y/50, e);
		checkGridForWall(e);
	}
	
	private void checkBounds(double x, double y, Explosion e) {
		if (boundExplosion) {
			activeSprites = GameScene.getInGrid((int)x, (int)y);
			boundExplosion = false;
		}
		checkGridForWall(e);
	}
	
	private void checkGridForWall(Explosion e) {
		for (Sprite s : activeSprites) {
			if (s instanceof UnbreakableWall) {
				e.setShouldExplode(false);
			}
		}
	}
	
	private void checkExplosionGrid(double x, double y, Explosion ex) {
		try {
			activeSprites = GameScene.getInGrid((int)x, (int)y);
		} catch(ArrayIndexOutOfBoundsException e) {
			ex.setShouldExplode(false);
		}
	}
	
	private LinkedList<Explosion> createToExplode(LinkedList<Explosion> b) {
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
