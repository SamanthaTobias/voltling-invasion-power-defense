package nl.samanthatobias.voltling.screen.game;

public class GameState implements GameStateActions {

	private int lives;
	private boolean isPlaying;

	public GameState(int startingLives) {
		this.lives = startingLives;
		this.isPlaying = false;
	}

	public void removeLives(int delta) {
		if (delta <= 0) {
			throw new IllegalArgumentException("Must remove positive number of lives");
		}
		lives -= delta;
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
