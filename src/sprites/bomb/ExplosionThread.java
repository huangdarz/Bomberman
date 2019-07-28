package sprites.bomb;

/**
 * A thread class to handle explosions
 * @author Darin Huang
 */
public class ExplosionThread extends Thread {
	Bomb b;
	
	/**
	 * Default constructor
	 * @param b The bomb whose explosions should be exploded
	 */
	public ExplosionThread(Bomb b) {
		this.b = b;
	}
	
	/**
	 * Waits for a second then explode
	 */
	@Override
	public synchronized void run() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b.explode();
		interrupt();
	}
	
}
