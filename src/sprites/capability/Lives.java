package sprites.capability;

/**
 * Interface to implement lives for polymorphism
 * @author Darin Huang
 */
public interface Lives {

	/**
	 * @return lives
	 */
	public int getLives();
	
	/**
	 * Sets the lives
	 * @param lives The amount of lives left
	 */
	public void setLives(int lives);
	
}
