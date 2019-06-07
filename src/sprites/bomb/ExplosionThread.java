package sprites.bomb;

public class ExplosionThread extends Thread {
	Bomb b;
	
	public ExplosionThread(Bomb b) {
		this.b = b;
	}
	
	@Override
	public synchronized void run() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b.explode();
		System.out.println(currentThread().getName());
		interrupt();
	}
	
}
