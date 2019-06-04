package sprites.bomb;

import java.util.ArrayList;

public class ExplosionThread extends Thread {
	ArrayList<Bomb> bombs;
	
	public ExplosionThread(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}
	
	@Override
	public synchronized void run() {
		boolean shouldRun = true;
		while (shouldRun) {
			try {
				wait();
			} catch (InterruptedException e) {
				shouldRun = false;
			}
			for (Bomb b : bombs) {
				b.explode();
			}
			bombs.clear();
		}
	}
	
}
