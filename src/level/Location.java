package level;

public class Location {
	private int x, y;
	
	public Location(int posX, int posY) {
		this.x = posX;
		this.y = posY;
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public int getWallX() {return (x*50)+50;}
	
	public int getWallY() {return (y*50)+50;}
	
	public int getPowerUpX() {return (x*50)+55;}
	
	public int getPowerUpY() {return (y*50)+55;}
	
	public int getMobX() {return getPowerUpX();}
	
	public int getMobY() {return getPowerUpY();}
}
