package sprites.capability;

/**
 * Interface for implementing points for polymorphism
 * @author Darin Huang
 */
public interface Points {
	
	/**
	 * @return points
	 */
	public int getPoints();
	
	/**
	 * Sets the amount of points
	 * @param points The amount of points the Sprite is worth
	 */
	public void setPoints(int points);
}
