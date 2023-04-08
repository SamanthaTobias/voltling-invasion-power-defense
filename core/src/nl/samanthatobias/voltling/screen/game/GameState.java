package nl.samanthatobias.voltling.screen.game;

import nl.samanthatobias.voltling.level.Path;

public class GameState implements GameStateActions {

	private final Path path;

	private int lives;
	private boolean isPlaying;

	public GameState(Path path, int startingLives) {
		this.path = path;
		this.lives = startingLives;
		this.isPlaying = false;
	}

	public void removeLives(int delta) {
		if (delta <= 0) {
			throw new IllegalArgumentException("Must remove positive number of lives");
		}
		System.out.println("removing lives by " + delta);
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
