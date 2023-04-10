package nl.samanthatobias.voltling.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import nl.samanthatobias.voltling.core.GameStateActions;
import nl.samanthatobias.voltling.tower.Tower;
import nl.samanthatobias.voltling.tower.TowerFactory;

public class TowerPlacementController {

	private final GameStateActions gameStateActions;

	private final Stage stage;
	private final SpriteBatch batch;

	private boolean isPlacingTower;
	private Tower placingTower;

	public TowerPlacementController(GameStateActions gameStateActions) {
		this.gameStateActions = gameStateActions;
		this.stage = new Stage();
		this.batch = new SpriteBatch();
		this.isPlacingTower = false;
		this.placingTower = null;
	}

	public void startPlacingTower() {
		if (isPlacingTower) {
			throw new IllegalStateException("Should not be able to click Tower Button when placing tower.");
		}
		isPlacingTower = true;
		placingTower = TowerFactory.createTower();
		stage.addActor(placingTower);
	}

	public void cancelPlacingTower() {
		if (!isPlacingTower) {
			throw new IllegalStateException("Should not be able to click Cancel Tower Button when not placing tower.");
		}
		isPlacingTower = false;
		placingTower = null;
	}

	public boolean handleTryPlaceTower(float x, float y) {
		if (!isPlacingTower) {
			throw new IllegalStateException("Not in placing tower mode.");
		}

		if (gameStateActions.handleTryPlaceTower(x, y, placingTower)) {
			cancelPlacingTower();
			return true;
		}

		return false;
	}

	public boolean isPlacingTower() {
		return isPlacingTower;
	}

	public void updatePlacingTowerPosition(Vector3 cursorPosition) {
		placingTower.setPositionFromCursor(cursorPosition.x, cursorPosition.y);

		batch.begin();
		placingTower.draw(batch, 0.5f); // TODO add to stage when staring placement?
		batch.end();
	}

	public void disposeOfPlacingTower() {
		placingTower.dispose();
	}

}
