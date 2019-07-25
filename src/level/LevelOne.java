package level;

import java.util.LinkedList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import scenes.GameScene;
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
		wallRand = new DistributeRandom(80, 95);
		duoRand = new DistributeRandom(0, 5);
		bigRand = new DistributeRandom(0, 5);
		infRand = new DistributeRandom(0, 5);
		mobRand = new DistributeRandom(1, 6, wallRand.getExistingLocations());
	}

	@Override
	public void createWalls(Scene scene, Pane pane) {
		int num = 0;
		for (Location l : wallRand.getLocations()) {
			BreakableWall wall = new BreakableWall(scene);
			wall.relocate(l.getWallX(), l.getWallY());
			GameScene.grid[l.getX()][l.getY()].add(wall);
			num++;
		}
		System.out.println("WALL: " + num);
	}

	@Override
	public void createDuo(Scene scene, Pane pane) {
		for (Location l : duoRand.getLocations()) {
			DoublePointsPowerUp duo = new DoublePointsPowerUp(scene);
			duo.relocate(l.getPowerUpX(), l.getPowerUpY());
			GameScene.grid[l.getX()][l.getY()].add(duo);		
		}
	}

	@Override
	public void createBig(Scene scene, Pane pane) {
		for (Location l : bigRand.getLocations()) {
			BiggerBombsPowerUp big = new BiggerBombsPowerUp(scene);
			big.relocate(l.getPowerUpX(), l.getPowerUpY());
			GameScene.grid[l.getX()][l.getY()].add(big);		
		}
	}

	@Override
	public void createInf(Scene scene, Pane pane) {
		for (Location l : infRand.getLocations()) {
			InfiniteBombsPowerUp inf = new InfiniteBombsPowerUp(scene);
			inf.relocate(l.getPowerUpX(), l.getPowerUpY());
			GameScene.grid[l.getX()][l.getY()].add(inf);		
		}
	}

	@Override
	public void createMobs(Scene scene, Pane pane) {
		LinkedList<Location> mobLoc = mobRand.getLocations();
		for (Location l : mobLoc) {
			Mob mob = new Mob(scene);
			mob.relocate(l.getMobX(), l.getMobY());
			pane.getChildren().add(mob);
		}
		System.out.println("MOb: " + mobLoc.size());
	}
}
