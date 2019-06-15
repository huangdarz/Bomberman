package scenes;

import java.util.ArrayList;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sprites.Mob;
import sprites.Sprite;
import sprites.TestSprite;
import sprites.powerUps.TestPowerUp;
import sprites.wall.UnbreakableWall;

public class GameScene extends BaseScene {
	
	Mob mob = new Mob(this);
	TestPowerUp power = new TestPowerUp(this);

	public ArrayList<Sprite>[][] grid;
	Dimension2D spriteDimension = new Dimension2D(50d, 50d);
	
	@SuppressWarnings("unchecked")
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		grid = new ArrayList[(int) (width / spriteDimension.getWidth())][(int) (height / spriteDimension.getHeight())];
		
		getPane().getChildren().add(mob);
		getPane().getChildren().add(power);
	}

	@Override
	public void run() {
		
	}

	@Override
	public void userInput() {
		
	}
	
	public void createGridArrays() {
//		for(int i = 0; i < grid.length*grid[0].length; i++) {
//			grid[(int) (i / getWidth())][(int) (i % getWidth())] = new ArrayList<Sprite>();
//			if((int)(i % getWidth()) == 0 || (int)(i % getWidth()) == getHeight()) {
//				grid[(int) (i / getWidth())][(int) (i % getWidth())].add(new UnbreakableWall(this));
//			}
//			else {
//				if(i % getWidth() == 0) {
//					grid[(int) (i / getWidth())][(int) (i % getWidth())].add(new UnbreakableWall(this));
//				}
//				else if(i % 2 == 1) {
//					grid[(int) (i / getWidth())][(int) (i % getWidth())].add(new UnbreakableWall(this));
//				}
//			}
//		}
		
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				grid[c][r] = new ArrayList<Sprite>();
				if(c == 0 || c == grid.length - 1) {
					grid[c][r].add(new UnbreakableWall(this));
				}
				else if(r == 0 || r == grid[0].length - 1) {
					grid[c][r].add(new UnbreakableWall(this));
				}
				else {
					if(r % 2 == 0 && c % 2 == 0) {
						grid[c][r].add(new UnbreakableWall(this));
					}
				}
			}
		}
  }
	
	public ArrayList<Sprite> getInGrid(int x, int y) {
		return grid[x][y];
	}
	
	public ArrayList<Sprite> getInLocalGrids(int x, int y) { 
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

	public Point2D TransferNearestGrid(Sprite s) {
		if((int)(s.getLayoutBounds().getCenterX() / 50d) != s.positionX || (int)(s.getLayoutBounds().getCenterY()) != s.positionY) {
			grid[s.positionX][s.positionY].remove(s);
			grid[(int)(s.getLayoutBounds().getCenterX() / 50d)][(int)(s.getLayoutBounds().getCenterY())].add(s);
			return new Point2D((int)(s.getLayoutBounds().getCenterX() / 50d), (int)(s.getLayoutBounds().getCenterY()));
		}
		return null;
	}
}
