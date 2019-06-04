package sprites.bomb;

import java.util.ArrayList;

public class ExplosionThread extends Thread {
	ArrayList<Bomb> bombs;
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
	}
	
}
