package level;

public class Location {
	private int x, y;
	
	/**
	 * Default constructor for Location
	 * @param posX The game grid x position
	 * @param posY The game grid y position
	 */
	public Location(int posX, int posY) {
		this.x = posX;
		this.y = posY;
	}
	
	/**
	 * @return x position
	 */
	public int getX() {return x;}
	
	/**
	 * @return y position
	 */
	public int getY() {return y;}
	
	/**
	 * @return x position in terms of GameScene.grid
	 */
	public int getGridX() {return x+1;}
	
	/**
	 * @return y position in terms of GameScene.grid
	 */
	public int getGridY() {return y+1;}
	
	/**
	 * @return x position in terms of the Pane for walls
	 */
	public int getWallX() {return (x*50)+50;}
	
	/**
	 * @return y position in terms of the Pane for walls
	 */
	public int getWallY() {return (y*50)+50;}
	
	/**
	 * @return x position in terms of the Pane for power ups
	 */
	public int getPowerUpX() {return (x*50)+55;}
	
	/**
	 * @return y position in terms of the Pane for power ups
	 */
	public int getPowerUpY() {return (y*50)+55;}
	
	/**
	 * @return x position in terms of the Pane for mobs
	 */
	public int getMobX() {return getPowerUpX();}
	
	/**
	 * @return y position in terms of the Pane for mobs
	 */
	public int getMobY() {return getPowerUpY();}
}
