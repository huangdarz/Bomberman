package scenes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sprites.TestSprite;
import sprites.Sprite;

public class GameScene extends BaseScene {
	Rectangle rect = new Rectangle(50, 50, Paint.valueOf("Red"));
	TestSprite s = new TestSprite(1, 0);
	TestSprite t = new TestSprite(-1, 0);
	
	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
		root.getChildren().addAll(rect, s, t);
		t.relocate(1200, 100);
		movement(s);
	}

	@Override
	public void run() {
//		System.out.println("LOOP");
		rect.setTranslateX(rect.getTranslateX()+1);
		System.out.printf("Rectangle X: %.1f \n", rect.getTranslateX());
		moving(s);
	}
	
	public void movement(TestSprite s) {
		this.setOnKeyPressed(key -> {
			switch(key.getCode()) {
				case UP:
					s.setIsMoving(Sprite.Direction.UP, true);
					break;
				case DOWN:
					s.setIsMoving(Sprite.Direction.DOWN, true);
					break;
				case RIGHT:
					s.setIsMoving(Sprite.Direction.RIGHT, true);
					break;
				case LEFT:
					s.setIsMoving(Sprite.Direction.LEFT, true);
					break;
				default: 
					break;
			}
		});
		this.setOnKeyReleased(key -> {
			switch(key.getCode()) {
				case UP:
					s.setIsMoving(Sprite.Direction.UP, false);
					break;
				case DOWN:
					s.setIsMoving(Sprite.Direction.DOWN, false);
					break;
				case RIGHT:
					s.setIsMoving(Sprite.Direction.RIGHT, false);
					break;
				case LEFT:
					s.setIsMoving(Sprite.Direction.LEFT, false);
					break;
				default: 
					break;
			}
		});
	}
	
	public void moving(TestSprite s) {
		if (s.getIsMoving(Sprite.Direction.UP)) {
			s.move(0, -1);
		}
		if (s.getIsMoving(Sprite.Direction.DOWN)) {
			s.move(0, 1);
		}
		if (s.getIsMoving(Sprite.Direction.RIGHT)) {
			s.move(1, 0);
		}
		if (s.getIsMoving(Sprite.Direction.LEFT)) {
			s.move(-1, 0);
		}
	}

}
