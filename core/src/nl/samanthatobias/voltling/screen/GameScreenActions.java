package nl.samanthatobias.voltling.screen;

public interface GameScreenActions {

	void onExitGame();

	boolean onPlayPauseToggle();

	void onChangeLives(int lives);

	void onChangeWave(int currentWaveIndex);

}
