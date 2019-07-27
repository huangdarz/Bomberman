package sprites.bomb;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import scenes.GameScene;
import sprites.base.Sprite;
import sprites.wall.UnbreakableWall;

/**
 * Bomb class that calculates its own explosion
 * @author Darin Huang
 */
public class Bomb extends Sprite {
	private static int range = 1;
	private boolean boundExplosion = true;
	
	private static int maxNumBombs = 1;
	private static int bombsPlaced = 0;
	
	ArrayList<Sprite> activeSprites = new ArrayList<Sprite>();
	
	/**
	 * Default constructor 
	 * @param scene The scene this object is added to
	 */
	public Bomb(Scene scene) {
		super(scene);
		Image image = new Image("/res/bomb.png");
		this.setImage(image);
		this.setFitWidth(50);
		this.setFitHeight(50);
		getLoop().stop();		
	}

	@Override
	public void run() {
		
	}
	
	/**
	 * Main handler for the bomb's explosion that interacts with the Application thread to add
	 * the required explosions. 
	 */
	public void explode() {
		int multiplier = 1;
		LinkedList<Explosion> blasts = new LinkedList<Explosion>();
		blastLoop(blasts, multiplier);
		blasts.add(new Explosion(getScene()));
		blasts.getLast().relocate(getLayoutX(), getLayoutY());
		GameScene.grid[(int) getLayoutX()/50][(int) getLayoutY()/50].add(blasts.getLast());
		LinkedList<Explosion> toExplode = createToExplode(blasts);
		Platform.runLater(() -> {
			getPane().getChildren().addAll(toExplode);
			getPane().getChildren().remove(this);
		});
		timer(750);
		Platform.runLater(() -> getPane().getChildren().removeAll(blasts));
		removeGridPos((int) getLayoutX()/50, (int) getLayoutY()/50, toExplode.getLast());
		removeGridPos(toExplode);
		setBombsPlaced(getBombsPlaced()-1);
	}
	
	/**
	 * Main loop handler for calculating the required explosions. 
	 * @param blasts The linked list that the explosions are added to and accessed.
	 * @param multiplier The multiplier to main equal distance between explosions. 
	 */
	private void blastLoop(LinkedList<Explosion> blasts, int multiplier) {
		for (int x = 0; x < range*4; x++) {
			blasts.add(new Explosion(getScene()));
			if (x < range) {
				calcExplosion(getLayoutX()-getFitWidth()*multiplier, getLayoutY(), blasts.getLast());
				if (x == range - 1) boundExplosion = true;
			} else if (x < range*2) {
				calcExplosion(getLayoutX(), getLayoutY()-getFitHeight()*multiplier, blasts.getLast());
				if (x == range*2 - 1) boundExplosion = true;
			} else if (x < range*3) {
				calcExplosion(getLayoutX()+getFitWidth()*multiplier, getLayoutY(), blasts.getLast());
				if (x == range*3 - 1) boundExplosion = true;
			} else {
				calcExplosion(getLayoutX(), getLayoutY()+getFitHeight()*multiplier, blasts.getLast());
				if (x == range*4 - 1) boundExplosion = true;
			}
			blasts.getLast().toBack();
			multiplier++;
			if (multiplier > range) multiplier = 1;
		}
	}
	
	/**
	 * Handler for each separate functions to calculate the explosion's position
	 * @param x The explosion's corresponding x position
	 * @param y The explosion's corresponding y position
	 * @param e The explosion that the calculations are for
	 */
	private void calcExplosion(double x, double y, Explosion e) {
		checkBounds(x/50, y/50, e);
		e.relocate(x, y);
		getExplosionGrid(x/50, y/50, e);
		checkGridForWall(e);
	}
	
	/**
	 * Checks one grid away from the bombs location for a wall. 
	 * @param x The explosion's corresponding x position
	 * @param y The explosion's corresponding y position
	 * @param e The explosion that the calculations are for
	 */
	private void checkBounds(double x, double y, Explosion e) {
		if (boundExplosion) {
			activeSprites = GameScene.getInGrid((int)x, (int)y);
			boundExplosion = false;
		}
		checkGridForWall(e);
	}
	
	/**
	 * Checks the grid for any instance of an UnbreakableWall to then stop the explosion
	 * @param e The explosion that the calculations are for
	 */
	private void checkGridForWall(Explosion e) {
		for (Sprite s : activeSprites) {
			if (s instanceof UnbreakableWall) {
				e.setShouldExplode(false);
			}
		}
	}
	
	/**
	 * Gets the corresponding grids in the GameScene 
	 * @param x The explosion's corresponding x position
	 * @param y The explosion's corresponding y position
	 * @param ex The explosion that the calculations are for
	 */
	private void getExplosionGrid(double x, double y, Explosion ex) {
		try {
			activeSprites = GameScene.getInGrid((int)x, (int)y);
		} catch(ArrayIndexOutOfBoundsException e) {
			ex.setShouldExplode(false);
		}
	}
	
	/**
	 * Creates new list of the explosions that will explode
	 * @param b The pre-existing explosions list
	 * @return Linked list of explosions that will explode
	 */
	private LinkedList<Explosion> createToExplode(LinkedList<Explosion> b) {
		LinkedList<Explosion> toExplode = new LinkedList<Explosion>();
		for (Explosion e : b) {
			if (e.getShouldExplode()) {
				toExplode.add(e);
				addGridPos((int) e.getLayoutX()/50, (int) e.getLayoutY()/50, e);
			}
		}
		return toExplode;
	}
	
	private void addGridPos(int x, int y, Explosion e) {
		GameScene.grid[x][y].add(e);
	}
	
	private void removeGridPos(LinkedList<Explosion> b) {
		for (Explosion e : b) {
			GameScene.grid[(int) e.getLayoutX()/50][(int) e.getLayoutY()/50].remove(e);
		}
	}
	
	private void removeGridPos(int x, int y, Explosion e) {
		GameScene.grid[x][y].remove(e);
	}
	
	/**
	 * Sleeps for the given amount of time
	 * @param timeMillis The time to sleep in millisenconds
	 */
	private void timer(int timeMillis) {
		long startTime = System.currentTimeMillis();
		long finishTime = System.currentTimeMillis();
		long elapsedTime = finishTime - startTime;
		
		while(elapsedTime < timeMillis) {
			finishTime = System.currentTimeMillis();
			elapsedTime = finishTime - startTime;
		}
	}
	
	public static int getRange() {return range;}
	public static void setRange(int range) {Bomb.range = range;}
	
	public static int getBombsPlaced() {return bombsPlaced;}
	public static void setBombsPlaced(int bombsPlaced) {Bomb.bombsPlaced = bombsPlaced;}
	
	public static int getMaxNumBombs() {return maxNumBombs;}
	public static void setMaxNumBombs(int maxNumBombs) {Bomb.maxNumBombs = maxNumBombs;}

}
