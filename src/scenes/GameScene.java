package scenes;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sprites.Mob;
import sprites.Sprite;
import sprites.TestSprite;
import sprites.wall.UnbreakableWall;

public class GameScene extends BaseScene {
	
	Mob mob = new Mob(this);

	public ArrayList<Sprite>[][] grid;
	Dimension2D spriteDimension = new Dimension2D(50d, 50d);
	
	Player player = new Player(this);
	
	@SuppressWarnings("unchecked")
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		grid = new ArrayList[15][13]; // 15, 13
		System.out.println("X: "+grid.length+" / Y: "+grid[0].length);
		System.out.println("###########################################");
		createGridArrays();
		
		player.positionX = 1;
		player.positionY = 1;
		getPane().getChildren().add(player);
	}

	@Override
	public void run() {
		
	}

	@Override
	public void userInput() {
		setOnKeyPressed(key -> {
			player.moveKeyPressedReleased(key.getCode(), true);
			player.placeBomb(key.getCode());
		});
		setOnKeyReleased(key -> {
			player.moveKeyPressedReleased(key.getCode(), false);
		});
		
	}
	
	public void createGridArrays() {		
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				grid[c][r] = new ArrayList<Sprite>();
				if(c == 0 || c == grid.length - 1) {
					grid[c][r].add(new UnbreakableWall(this, c * spriteDimension.getWidth(), r * spriteDimension.getHeight()));
				}
				else if(r == 0 || r == grid[0].length - 1) {
					grid[c][r].add(new UnbreakableWall(this, c * spriteDimension.getWidth(), r * spriteDimension.getHeight()));
				}
				else {
					if(r % 2 == 0 && c % 2 == 0) {
						grid[c][r].add(new UnbreakableWall(this, c * spriteDimension.getWidth(), r * spriteDimension.getHeight()));
					}
				}
				getPane().getChildren().addAll(grid[c][r]);
			}
		}
  }
	
	public ArrayList<Sprite> getInGrid(int x, int y) {
		return grid[x][y];
	}
	
	public synchronized ArrayList<Sprite> getInLocalGrids(int x, int y) { 
		int detectionDiameter = 7;
		ArrayList<Sprite> local = new ArrayList<Sprite>();
//		getPane().getChildren().removeIf(e -> { return (e instanceof Rectangle); });
		for(int c = 0; c < detectionDiameter; c++) {
			for(int r = 0; r < detectionDiameter; r++) {
				try {
					local.addAll(grid[c +x - 3][r +y - 3]);
//					getPane().getChildren().add(new Rectangle((c+x-3)*50, (r+y-3)*50, 50, 50));
				}
				catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		return local;
	}
	
	/**
	 * used mainly for bomb and mob placement
	 * 
	 * @param s
	 * @return
	 */
	public Point2D TransferNearestGrid(Sprite s) {
		if((int)(s.getLayoutBounds().getCenterX() / 50) != s.positionX || (int)(s.getLayoutBounds().getCenterY() / 50) != s.positionY) {
			grid[s.positionX][s.positionY].remove(s);
			grid[(int)(s.getLayoutBounds().getCenterX() / 50)][(int)(s.getLayoutBounds().getCenterY() / 50)].add(s);
			System.out.println("Position: "+(int)(s.getLayoutBounds().getCenterX() / 50)+" : "+(int)(s.getLayoutBounds().getCenterY() / 50));
			return new Point2D((int)(s.getLayoutBounds().getCenterX() / 50), (int)(s.getLayoutBounds().getCenterY())/ 50);
		}
		return null;
	}
}
