package scenes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sprites.TestSprite;

public class GameScene extends BaseScene {
	Rectangle rect = new Rectangle(50, 50, Paint.valueOf("Red"));
	TestSprite s = new TestSprite(this);
	TestSprite t = new TestSprite(this);
	
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		root.getChildren().addAll(rect, s, t);
		t.relocate(1200, 100);
		s.userMovementDetectARROW(this, loop);
//		t.userMovementDetectARROW(this, loop);
	}

	@Override
	public void run() {
		s.userMove(10);
		rect.setTranslateX(rect.getTranslateX()+1);
		System.out.printf("Rectangle X: %.1f \n", rect.getTranslateX());
		System.out.println(s.getVelocityX());
	}
}
