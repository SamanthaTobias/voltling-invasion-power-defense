package nl.samanthatobias.voltling.core;

public interface GameStateActions {

	boolean isGameOver();

	void removeLives(int delta);

}
