package sprites.type;

/**
 * Interface to implement to classify a Sprite as a power up
 * @author Darin Huang
 */
public interface Power {
	
	/**
	 * @return True to confirm the Sprite is a power up
	 */
	public default boolean isPower() {return true;}
	
}
