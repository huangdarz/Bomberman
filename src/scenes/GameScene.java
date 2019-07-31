package scenes;

import java.util.ArrayList;
import java.util.HashSet;

import application.GameLoop;
import application.Main;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import level.LevelCreator;
import sprites.Player;
import sprites.base.Sprite;
import sprites.type.Enemy;
import sprites.wall.UnbreakableWall;

/**
 * Main scene which initializes all sprites on constructor call, listens for key presses <br>
 * as well as provides methods for sprites to use when using collision detection
 * 
 * @author Mitchell Barker
 *
 */
public class GameScene extends BaseScene {
 
	@SuppressWarnings("unchecked")
	public static ArrayList<Sprite>[][] grid = new ArrayList[15][13];
	public HashSet<String> buttonsPressed = new HashSet<String>();
	public static int score, lives;
	private static Text scoreText, livesText;
	double startTime;

	Player player = new Player(this);
	boolean hasEnemies = true, ended = false;
	
	/**
	 * Creates a scene for a specific root Node with a specific size
	 * @param root The root node of the scene graph
	 * @param width The width of the scene
	 * @param height The height of the scene
	 */
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		System.out.println("Grid-Width: "+grid.length+" / Grid-Height: "+grid[0].length);
		createGridArrays();
		lives = player.getLives();
		startTime = System.currentTimeMillis()/1000;
		
		scoreText = new Text(width-180, 30, "Score: "+score);
		livesText = new Text(15, 30, "Lives: "+lives);
		scoreText.setFont(Font.font("Verdana", 24));
		scoreText.setFill(Color.WHITE);
		livesText.setFont(Font.font("Verdana", 24));
		livesText.setFill(Color.WHITE);
		
		player.positionX = 1;
		player.positionY = 1;
		grid[player.positionX][player.positionY].add(player);
		getPane().setBackground(new Background(new BackgroundImage(new Image("/res/map.png", width, height, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		getPane().getChildren().addAll(player);
		getPane().getChildren().add(scoreText);
		getPane().getChildren().add(livesText);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		scoreText.setText("Score: "+score);
		livesText.setText("Lives: "+player.getLives());
		
		hasEnemies = false;
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				grid[c][r].forEach(x -> {
					if (x instanceof Enemy) hasEnemies = true;
				});
			}
		}
		
		double endTime = System.currentTimeMillis()/1000;
		if (!hasEnemies) {
			if ((endTime - startTime) % 5 == 0 && !ended) {
				ended = false;
				getPane().setEffect(new javafx.scene.effect.GaussianBlur());
				double centerXPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth()/2d;
                double centerYPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight()/2d;
                Main.end.setOnShowing(e -> Main.end.hide());
                Main.end.setOnShown(e -> {
                	Main.end.setX(centerXPosition - Main.end.getWidth()/2d);
                	Main.end.setY(centerYPosition - Main.end.getHeight()/2d);
                	Main.end.show();
                });
                Main.end.show();
			}
		}
		if (player.getLives() <= 0) {
			getPane().setEffect(new javafx.scene.effect.GaussianBlur());
			player.setVisible(false);
			double centerXPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth()/2d;
            double centerYPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight()/2d;
            Main.end.setOnShowing(e -> Main.end.hide());
            Main.end.setOnShown(e -> {
            	Main.end.setX(centerXPosition - Main.end.getWidth()/2d);
            	Main.end.setY(centerYPosition - Main.end.getHeight()/2d);
            	Main.end.show();
            });
            Main.end.show();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void userInput() {
		setOnKeyPressed(key -> {
			buttonsPressed.add(key.getCode().toString());
			player.moveKeyPressedReleased(key.getCode(), true);
			player.placeBomb(key.getCode());
			if(key.getCode() == KeyCode.ESCAPE) {
				Main.primaryStage.setScene(Main.pause);
				for(int c = 0; c < grid.length; c++) {
					for(int r = 0; r < grid[0].length; r++) {
						grid[c][r].forEach(x -> {
							if (x instanceof GameLoop) {
								x.getLoop().stop();
							}
						});
					}
				}
				System.out.println(Main.primaryStage.getScene());
			}
			if (key.getCode() == KeyCode.A) {
				getPane().setEffect(new javafx.scene.effect.GaussianBlur());
				double centerXPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth()/2d;
                double centerYPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight()/2d;
                Main.end.setOnShowing(e -> Main.end.hide());
                Main.end.setOnShown(e -> {
                	Main.end.setX(centerXPosition - Main.end.getWidth()/2d);
                	Main.end.setY(centerYPosition - Main.end.getHeight()/2d);
                	Main.end.show();
                });
                Main.end.show();
			}
		});
		setOnKeyReleased(key -> {
			buttonsPressed.remove(key.getCode().toString());
			player.moveKeyPressedReleased(key.getCode(), false);
		});
	}

	/**
	 * creates the set map of unbreakable walls, both boundary and inner pattern, <br>
	 * then randomly distributes breakable walls, mobs and power ups <br>
	 * <br>
	 * method will then finally iterate through all grid array indexes and add all objects created to the pane
	 * @author Mitchell Barker
	 */
	public void createGridArrays() {
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				grid[c][r] = new ArrayList<Sprite>();
				if(c == 0 || c == grid.length - 1) {
					grid[c][r].add(new UnbreakableWall(this, c * 50, r * 50));
				}
				else if(r == 0 || r == grid[0].length - 1) {
					grid[c][r].add(new UnbreakableWall(this, c * 50, r * 50));
				}
				else if(r % 2 == 0 && c % 2 == 0) {
					grid[c][r].add(new UnbreakableWall(this, c * 50, r * 50));
				}
			}
		}
		LevelCreator.level(1).create(this);
		for(int c = 0; c < grid.length; c++) {
			for(int r = 0; r < grid[0].length; r++) {
				getPane().getChildren().addAll(grid[c][r]);
			}
		}
	}

	/**
	 * gets the ArrayList of all Sprites in the specified grid position 
	 * 
	 * @param x coordinate of ArrayList in grid
	 * @param y coordinate of ArrayList in grid
	 * @return ArrayList of all objects in grid
	 * @author Mitchell Barker
	 */
	public static ArrayList<Sprite> getInGrid(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Calculates and retrieves all Sprites in all grids around a set diameter, returns all Sprite objects in an ArrayList
	 * 
	 * @param x coordinate of origin of diameter 
	 * @param y coordinate of origin of diameter
	 * @return ArrayList of all objects in local grids
	 * @author Mitchell Barker
	 */
	public synchronized ArrayList<Sprite> getInLocalGrids(int x, int y) {
		final int detectionDiameter = 3; // must be odd, includes center grid cell
		ArrayList<Sprite> local = new ArrayList<Sprite>();
		for(int c = 0; c < detectionDiameter; c++) {
			for(int r = 0; r < detectionDiameter; r++) {
				local.addAll(grid[Math.min(Math.max(c+x-(int)(detectionDiameter/2), 0), grid.length-1)][Math.min(Math.max(r+y-(int)(detectionDiameter/2), 0), grid[0].length - 1)]);
			}
		}
		return local;
	}

	/**
	 * used mainly for bomb and mob placement
	 *
	 * @param s Sprite to transfer
	 * @return Point2D new position in grid
	 * @author Mitchell Barker
	 */
	public Point2D TransferNearestGrid(Sprite sprite) {
		if((int)(sprite.getLayoutBounds().getCenterX() / 50) != sprite.positionX || (int)(sprite.getLayoutBounds().getCenterY() / 50) != sprite.positionY) {
			grid[sprite.positionX][sprite.positionY].remove(sprite);
			grid[(int)(sprite.getLayoutBounds().getCenterX() / 50)][(int)(sprite.getLayoutBounds().getCenterY() / 50)].add(sprite);
			System.out.println("Position: "+(int)(sprite.getLayoutBounds().getCenterX() / 50)+" : "+(int)(sprite.getLayoutBounds().getCenterY() / 50));
			return new Point2D((int)(sprite.getLayoutBounds().getCenterX() / 50), (int)(sprite.getLayoutBounds().getCenterY())/ 50);
		}
		return null;
	}

	/**
	 * adds amount to score
	 * 
	 * @param sumScore any positive or negative integer
	 */
	public static synchronized void sumScore(int sumScore) {
		score += sumScore;
	}

	/**
	 * adds amount to lives <br>
	 * game will display end screen when lives reaches 0 
	 * 
	 * @param sumLives any positive or negative integer
	 */
	public static synchronized void sumLives(int sumLives) {
		lives += sumLives;
	}
}
