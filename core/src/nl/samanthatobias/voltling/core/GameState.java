package nl.samanthatobias.voltling.core;

import nl.samanthatobias.voltling.utils.GdxLogger;

public class GameState implements GameStateActions {

	private final static GdxLogger log = new GdxLogger(GameState.class);

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
		log.info("Lost " + delta + " lives.");
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
