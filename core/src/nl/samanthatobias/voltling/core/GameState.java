package nl.samanthatobias.voltling.core;

public class GameState implements GameStateActions {

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
		System.out.println("removing lives by " + delta);
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
		isPlaying = !isPlaying;
		return isPlaying;
	}

}
