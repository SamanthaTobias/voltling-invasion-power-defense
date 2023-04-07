package nl.samanthatobias.voltling.screen.game;

import nl.samanthatobias.voltling.level.Path;
import nl.samanthatobias.voltling.level.Paths;

public class GameState implements GameStateActions {

	private final Path path;

	private int lives;
	private boolean isPlaying;

	public GameState(int startingLives) {
		this.path = Paths.createBasicPath();
		this.lives = startingLives;
		this.isPlaying = false;
	}

	public void removeLives(int delta) {
		if (delta <= 0) {
			throw new IllegalArgumentException("Must remove positive number of lives");
		}
		lives -= delta;
	}

	public void renderPath() {
		path.render();
	}

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
		isPlaying = !isPlaying;
		return isPlaying;
	}

}
