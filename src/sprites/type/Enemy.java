package sprites.type;

public interface Enemy {
	
	public default boolean isEnemy() {
		return true;
	}
	
	public boolean isDestroyed();
	
	public void setDestroyed(boolean isDestroyed);
}
