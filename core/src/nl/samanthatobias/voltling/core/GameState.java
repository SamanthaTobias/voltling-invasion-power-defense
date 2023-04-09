package nl.samanthatobias.voltling.core;

import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class GameState implements GameStateActions {

	private static final Logger log = createLogger(GameState.class);

	private int lives;
	private boolean isPlaying;

	public GameState(int startingLives) {
		this.lives = startingLives;
		this.isPlaying = false;
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

}
