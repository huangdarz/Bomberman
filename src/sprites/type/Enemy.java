package sprites.type;

/**
 * Interface to implement to classify a Sprite as a hostile enemy
 * @author Darin Huang
 */
public interface Enemy {
	
	/**
	 * @return True to confirm it is an enemy
	 */
	public default boolean isEnemy() {
		return true;
	}
	
	/**
	 * @return True if the this has been destroyed
	 */
	public boolean isDestroyed();
	
	/**
	 * Sets whether it has been destroyed or not
	 * @param isDestroyed Value to changed isDestroyed to.
	 */
	public void setDestroyed(boolean isDestroyed);
}
