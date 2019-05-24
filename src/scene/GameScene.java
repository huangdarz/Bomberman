package scene;

import javafx.scene.layout.Pane;

public class GameScene extends BaseScene{

	public GameScene(Pane root, double width, double height) {
		super(root, width, height);
	}

	@Override
	public void run() {
		System.out.println("LOOP");
	}

}
