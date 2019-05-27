package application;

import javafx.animation.AnimationTimer;

public interface GameLoop {
	public void run();
	
	public default void loop() {
		AnimationTimer loop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				run();
			}
		};
		loop.start();
	}
	
}
