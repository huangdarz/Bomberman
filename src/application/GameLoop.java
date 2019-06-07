package application;

import javafx.animation.AnimationTimer;

public interface GameLoop {
	/**
	 * Method that is run by the Animation Timer (loop)
	 */
	public void run();
	
	/**
	 * Creates a new AnimationTimer object
	 * @return AnimationTimer The newly created AnimationTimer
	 */
	public default AnimationTimer loop() {
		AnimationTimer loop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				run();
			}
		};
		return loop;
	}
	
}
