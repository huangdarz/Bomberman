package level;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sprites.Mob;
import sprites.differentPowerUps.BiggerBombsPowerUp;
import sprites.differentPowerUps.DoublePointsPowerUp;
import sprites.differentPowerUps.InfiniteBombsPowerUp;
import sprites.wall.BreakableWall;

public class LevelOne implements Level {
	private DistributeRandom 
		wallRand, 
		duoRand, 
		bigRand, 
		infRand,
		mobRand;
	
	public LevelOne() {
		wallRand = new DistributeRandom(new Random(), 80, 95);
		duoRand = new DistributeRandom(new Random(), 0, 5);
		bigRand = new DistributeRandom(new Random(), 0, 5);
		infRand = new DistributeRandom(new Random(), 0, 5);
		mobRand = new DistributeRandom(new Random(), 1, 6);
	}

	@Override
	public void createWalls(Scene scene, Pane pane) {
		for (Location l : wallRand.getLocations()) {
			BreakableWall wall = new BreakableWall(scene);
			wall.relocate(l.getWallX(), l.getWallY());
			pane.getChildren().add(wall);
		}
	}

	@Override
	public void createDuo(Scene scene, Pane pane) {
		for (Location l : duoRand.getLocations()) {
			DoublePointsPowerUp duo = new DoublePointsPowerUp(scene);
			duo.relocate(l.getPowerUpX(), l.getPowerUpY());
			pane.getChildren().add(duo);
		}
	}

	@Override
	public void createBig(Scene scene, Pane pane) {
		for (Location l : bigRand.getLocations()) {
			BiggerBombsPowerUp big = new BiggerBombsPowerUp(scene);
			big.relocate(l.getPowerUpX(), l.getPowerUpY());
			pane.getChildren().add(big);
		}
	}

	@Override
	public void createInf(Scene scene, Pane pane) {
		for (Location l : infRand.getLocations()) {
			InfiniteBombsPowerUp inf = new InfiniteBombsPowerUp(scene);
			inf.relocate(l.getPowerUpX(), l.getPowerUpY());
			pane.getChildren().add(inf);
		}
	}

	@Override
	public void createMobs(Scene scene, Pane pane) {
		for (Location l : mobRand.getLocations()) {
			Mob mob = new Mob(scene);
			mob.relocate(l.getMobX(), l.getMobY());
			pane.getChildren().add(mob);
		}
	}
}
