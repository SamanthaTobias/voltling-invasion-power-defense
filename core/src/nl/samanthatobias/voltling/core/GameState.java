package nl.samanthatobias.voltling.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.level.Path;
import nl.samanthatobias.voltling.screen.GameScreenActions;
import nl.samanthatobias.voltling.tower.Tower;
import nl.samanthatobias.voltling.utils.ArrayUtils;
import nl.samanthatobias.voltling.utils.logger.Logger;
import nl.samanthatobias.voltling.utils.shapes.RectangleUtils;
import nl.samanthatobias.voltling.voltling.Voltling;
import nl.samanthatobias.voltling.wave.Wave;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class GameState implements GameStateActions {

	private static final Logger log = createLogger(GameState.class);

	private final GameScreenActions gameScreenActions;
	private final Array<Wave> waves;
	private final Stage actorStage;
	private final Path path;
	private final Array<Tower> towers;
	private final Array<Voltling> voltlings;

	private int currentWaveIndex = 0;
	private int lives;
	private boolean isPlaying;

	private float timeSinceLastDrainLife = 0f;

	public GameState(GameScreenActions gameScreenActions, Array<Wave> waves, Stage actorStage, Path path,
					 int startingLives) {
		this.gameScreenActions = gameScreenActions;
		this.waves = waves;
		this.actorStage = actorStage;
		this.path = path;
		this.towers = new Array<>();
		this.voltlings = new Array<>();
		this.lives = startingLives;
		this.isPlaying = false;
	}

	public void gameLoop(float delta) {
		if (Config.DEBUG_DRAIN_LIFE) {
			timeSinceLastDrainLife += delta;
			if (timeSinceLastDrainLife >= 1f) {
				log.debug("Draining life.");
				removeLives(Config.DEBUG_DRAIN_LIFE_AMOUNT * (int) timeSinceLastDrainLife);
				timeSinceLastDrainLife = 0f;
			}
		}

		Wave currentWave = waves.get(currentWaveIndex);
		Voltling voltling = currentWave.tic(delta);
		if (voltling != null) {
			placeVoltling(voltling);
		} else if (currentWave.isOver()) {
			currentWaveIndex++;
			gameScreenActions.onChangeWave(currentWaveIndex);
		}

		actorStage.act(delta);
		updateVoltlings();

		gameScreenActions.onChangeLives(lives);

		if (isGameOver()) {
			gameScreenActions.onExitGame();
		}
	}

	@Override
	public void removeLives(int delta) {
		if (delta <= 0) {
			throw new IllegalArgumentException("Must remove positive number of lives");
		}
		log.info("Lost {} lives.", delta);
		lives -= delta;
	}

	@Override
	public boolean isGameOver() {
		return lives <= 0;
	}

	@Override
	public boolean handleTryPlaceTower(float x, float y, Tower placingTower) {
		if (isValidTowerPlacement(placingTower)) {
			towers.add(placingTower);
			actorStage.addActor(placingTower);
			placingTower.toFront();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isValidTowerPlacement(Tower placingTower) {
		log.debug("Checking if Tower={} placement is valid.", placingTower.getName());
		RectangleUtils.logPosition(placingTower.getBoundingRectangle());

		if (isOutOfBounds(placingTower)) {
			log.debug("Invalid: outside of the map.");
			return false;
		}
		if (path.overlapsWith(placingTower)) {
			log.debug("Invalid: overlaps with the path.");
			return false;
		}
		if (collidesWithOtherTower(placingTower)) {
			log.debug("Invalid: overlaps with another Tower.");
			return false;
		}

		log.debug("Valid tower placement.");
		return true;
	}

	private boolean collidesWithOtherTower(Tower placingTower) {
		for (Tower tower : ArrayUtils.iterator(towers)) {
			if (Intersector.overlaps(tower.getBoundingRectangle(), placingTower.getBoundingRectangle())) {
				return true;
			}
		}
		return false;
	}

	private static boolean isOutOfBounds(Tower placingTower) {
		if (placingTower.getX() < 0) {
			log.debug("x<0");
			return true;
		}
		if (placingTower.getY() < 0) {
			log.debug("y<0");
			return true;
		}
		if (placingTower.getX() + placingTower.getWidth() > Gdx.graphics.getWidth()) {
			log.debug("x>bounds");
			return true;
		}
		if (placingTower.getY() + placingTower.getHeight() > Gdx.graphics.getHeight()) {
			log.debug("y>bounds");
			return true;
		}
		return false;
	}

	private void placeVoltling(Voltling voltling) {
		log.debug("Placing Voltling {}.", voltling.getName());
		voltling.place(path);
		voltlings.add(voltling);
		actorStage.addActor(voltling);
		log.debug("actorStage size now {}.", actorStage.getActors().size);
	}

	public void updateVoltlings() {
		for (Voltling voltling : ArrayUtils.iterator(voltlings)) {
			if (voltling.isAtPoint(path.getEndPoint())) {
				log.info(voltling.getName() + " has reached the end of its path.");
				removeLives(voltling.getPower());
				voltling.remove();
				voltlings.removeValue(voltling, true);
			}
		}
	}

	public int getLives() {
		return lives;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public boolean togglePlaying() {
		log.info("Toggling play/pause.");
		isPlaying = !isPlaying;
		return isPlaying;
	}

	public void renderPath() {
		path.render();
	}

}
