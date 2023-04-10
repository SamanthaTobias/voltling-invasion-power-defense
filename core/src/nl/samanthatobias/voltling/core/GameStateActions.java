package nl.samanthatobias.voltling.core;

import nl.samanthatobias.voltling.tower.Tower;

public interface GameStateActions {

	boolean isGameOver();

	void removeLives(int delta);

	boolean isValidTowerPlacement(Tower placingTower);

	boolean handleTryPlaceTower(float x, float y, Tower placingTower);

}
