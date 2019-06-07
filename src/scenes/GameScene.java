package scenes;

import java.util.ArrayList;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sprites.Sprite;
import sprites.TestSprite;

public class GameScene extends BaseScene {
	
	Dimension2D spriteDimension = new Dimension2D(50d, 50d);
	
	public static ArrayList<Sprite>[][] grid;
	
	@SuppressWarnings("unchecked")
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		grid = new ArrayList[(int) (width / spriteDimension.getWidth())][(int) (height / spriteDimension.getHeight())];
		
	}

	@Override
	public void run() {
		
	}

	@Override
	public void userInput() {
		
	}
	
	private ArrayList<Sprite>[][] create(int gridWidth, int gridHeight) {
		ArrayList<Sprite>[][] grid = new ArrayList[gridWidth][gridHeight];
		for(int i = 0; i < gridWidth*gridHeight; i++) {
			if(i / gridWidth == 0) {
				
			}
		}
	}
	
	public static ArrayList<Sprite> getInGrid(int x, int y) {
		return grid[x][y];
	}
	
	public static ArrayList<Sprite> getInLocalGrids(int x, int y) { 
		ArrayList<Sprite> local = new ArrayList<Sprite>();
		for(int i = 0; i < 9; i++) {
			try {
				local.addAll(grid[((int) i / 3) +x -1][(i % 3) +y -1]);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				
			}
		}
		return local;
	}
	
	public static Point2D TransferNearestGrid(Sprite s) {
		if((int)(s.getLayoutBounds().getCenterX() / 50d) != s.positionX || (int)(s.getLayoutBounds().getCenterY()) != s.positionY) {
			grid[s.positionX][s.positionY].remove(s);
			grid[(int)(s.getLayoutBounds().getCenterX() / 50d)][(int)(s.getLayoutBounds().getCenterY())].add(s);
			return new Point2D((int)(s.getLayoutBounds().getCenterX() / 50d), (int)(s.getLayoutBounds().getCenterY()));
		}
		return null;
	}
}
