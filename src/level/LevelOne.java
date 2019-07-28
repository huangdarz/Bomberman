package level;

import java.util.LinkedList;

import javafx.scene.Scene;
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
	
	/**
	 * Default constructor for the level.
	 */
	public LevelOne() {
		wallRand = new DistributeRandom(80, 95);
		duoRand = new DistributeRandom(0, 5);
		bigRand = new DistributeRandom(0, 5);
		infRand = new DistributeRandom(0, 5);
		mobRand = new DistributeRandom(1, 6, wallRand.getExistingLocations());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createWalls(Scene scene) {
		int num = 0;
		for (Location l : wallRand.getLocations()) {
			BreakableWall wall = new BreakableWall(scene);
			wall.relocate(l.getWallX(), l.getWallY());
			GameScene.grid[l.getGridX()][l.getGridY()].add(wall);
			num++;
		}
		System.out.println("WALL: " + num);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createDuo(Scene scene) {
		for (Location l : duoRand.getLocations()) {
			DoublePointsPowerUp duo = new DoublePointsPowerUp(scene);
			duo.relocate(l.getPowerUpX(), l.getPowerUpY());
			GameScene.grid[l.getGridX()][l.getGridY()].add(duo);		
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createBig(Scene scene) {
		for (Location l : bigRand.getLocations()) {
			BiggerBombsPowerUp big = new BiggerBombsPowerUp(scene);
			big.relocate(l.getPowerUpX(), l.getPowerUpY());
			GameScene.grid[l.getGridX()][l.getGridY()].add(big);		
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createInf(Scene scene) {
		for (Location l : infRand.getLocations()) {
			InfiniteBombsPowerUp inf = new InfiniteBombsPowerUp(scene);
			inf.relocate(l.getPowerUpX(), l.getPowerUpY());
			GameScene.grid[l.getGridX()][l.getGridY()].add(inf);		
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createMobs(Scene scene) {
		LinkedList<Location> mobLoc = mobRand.getLocations();
		for (Location l : mobLoc) {
			Mob mob = new Mob(scene);
			mob.relocate(l.getMobX(), l.getMobY());
			GameScene.grid[l.getGridX()][l.getGridY()].add(mob);		
		}
		System.out.println("MOb: " + mobLoc.size());
	}
}
