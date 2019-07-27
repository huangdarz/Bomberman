package scenes;

import java.util.ArrayList;
import java.util.HashSet;

import application.Main;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import level.LevelCreator;
import sprites.Mob;
import sprites.Player;
import sprites.Sprite;
import sprites.TestSprite;
import sprites.bomb.CheckExplodable;
import sprites.differentPowerUps.BiggerBombsPowerUp;
import sprites.differentPowerUps.DoublePointsPowerUp;
import sprites.differentPowerUps.InfiniteBombsPowerUp;
import sprites.wall.UnbreakableWall;

public class GameScene extends BaseScene {

//	Mob mob = new Mob(this);
//	InfiniteBombsPowerUp infPowUp = new InfiniteBombsPowerUp(this);
//	BiggerBombsPowerUp bigPowUp= new BiggerBombsPowerUp(this);
//	DoublePointsPowerUp duoPowUp = new DoublePointsPowerUp(this);

	public static boolean debugger = false;
	public static ArrayList<Sprite>[][] grid = new ArrayList[15][13];
	Dimension2D spriteDimension = new Dimension2D(50d, 50d);
	Text buttonsText = new Text(8, 640, "");
	public HashSet<String> buttonsPressed = new HashSet<String>();
	public static int score, lives;
	private static Text scoreText, livesText;

	Player player = new Player(this);
	
	@SuppressWarnings("unchecked")
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		System.out.println("Grid-Width: "+grid.length+" / Grid-Height: "+grid[0].length);
		createGridArrays();
		lives = player.getLives();
		
		scoreText = new Text(width-85, 25, "Score: "+score);
		livesText = new Text(15, 25, "Lives: "+lives);
		
		player.positionX = 1;
		player.positionY = 1;
		grid[player.positionX][player.positionY].add(player);
//		getPane().setBackground(new Background(new BackgroundImage(new Image(""), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

		getPane().getChildren().addAll(player);
		getPane().getChildren().add(scoreText);
		getPane().getChildren().add(livesText);
	}

	@Override
	public void run() {
		buttonsText.setText("PRESSED: "+buttonsPressed.toString());
		if(debugger && !getPane().getChildren().contains(buttonsText)) getPane().getChildren().add(buttonsText);
		if(!debugger && getPane().getChildren().contains(buttonsText)) getPane().getChildren().remove(buttonsText);
		
		scoreText.setText("Score: "+score);
		livesText.setText("Lives: "+player.getLives());
	}

	@Override
	public void userInput() {
		setOnKeyPressed(key -> {
			buttonsPressed.add(key.getCode().toString());
			player.moveKeyPressedReleased(key.getCode(), true);
			player.placeBomb(key.getCode());
			if(key.getCode() == KeyCode.BACK_QUOTE) {
				debugger = !debugger;
			}
			if(key.getCode() == KeyCode.ESCAPE) {
				Main.primaryStage.setScene(Main.pause);
				System.out.println(Main.primaryStage.getScene());
			}
		});
		setOnKeyReleased(key -> {
			buttonsPressed.remove(key.getCode().toString());
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
				else if(r % 2 == 0 && c % 2 == 0) {
					grid[c][r].add(new UnbreakableWall(this, c * spriteDimension.getWidth(), r * spriteDimension.getHeight()));
				}
			}
		}
		LevelCreator.level(1).create(this, getPane());
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				getPane().getChildren().addAll(grid[c][r]);
			}
		}
  }

	public static ArrayList<Sprite> getInGrid(int x, int y) {
		return grid[x][y];
	}

	public synchronized ArrayList<Sprite> getInLocalGrids(int x, int y) {
		final int detectionDiameter = 3; // must be odd, includes center grid cell
		ArrayList<Sprite> local = new ArrayList<Sprite>();

		getPane().getChildren().removeIf(e -> { return (e instanceof Rectangle); });

		for(int c = 0; c < detectionDiameter; c++) {
			for(int r = 0; r < detectionDiameter; r++) {
				try {
					local.addAll(grid[c+x-(int)(detectionDiameter/2)][r+y-(int)(detectionDiameter/2)]);
					if(debugger) {
						Rectangle debug = new Rectangle((c+x-(int)(detectionDiameter/2))*50, (r+y-(int)(detectionDiameter/2))*50, 50, 50);
						debug.setFill(new Color(0.3, 0.3, c == (int)(detectionDiameter/2) && r == (int)(detectionDiameter/2) ? 1.0 : 0.3, 0.7));
						getPane().getChildren().add(debug);
					}
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

	public static synchronized void sumScore(int sumScore) {
		score += sumScore;
	}

	public static synchronized void sumLives(int sumLives) {
		lives += sumLives;
	}
}
