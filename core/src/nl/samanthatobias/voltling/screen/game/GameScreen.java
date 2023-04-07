package nl.samanthatobias.voltling.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import nl.samanthatobias.voltling.VoltlingGame;
import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.screen.GameEndScreen;
import nl.samanthatobias.voltling.screen.Screen;

public class GameScreen extends Screen implements GameScreenActions {

	private final Config config;
	private final GameScreenUI gameScreenUI;
	private final GameState gameState;
	private final VoltlingManager voltlingManager;

	private float timeSinceLastDrainLife = 0f;

	public GameScreen(final VoltlingGame game) {
		super(game);
		config = new Config();

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		int lives = config.getStartingLives();
		gameState = new GameState(lives);
		gameScreenUI = new GameScreenUI(this, stage, skin, lives);
		voltlingManager = new VoltlingManager(gameState, stage, skin);

		voltlingManager.spawnLesserVoltling();

		gameScreenUI.getExitButton().addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				endGame();
			}
		});
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (gameState.isPlaying()) {
			progressGameTic(delta);
		}
	}

	@Override
	public void onExitGame() {
		endGame();
	}

	@Override
	public boolean onPlayPauseToggle() {
		return gameState.togglePlaying();
	}

	private void updateLivesLabel(int lives) {
		gameScreenUI.updateLivesLabel(lives);
	}

	private void progressGameTic(float delta) {
		if (config.isDebugDrainLife()) {
			timeSinceLastDrainLife += delta;
			if (timeSinceLastDrainLife >= 1f) {
				gameState.removeLives(config.getDebugDrainLifeAmount() * (int) timeSinceLastDrainLife);
				timeSinceLastDrainLife = 0f;
			}
		}

		voltlingManager.updateVoltlings(delta);

		updateLivesLabel(gameState.getLives());

		if (gameState.isGameOver()) {
			endGame();
		}
	}

	private void endGame() {
		game.setScreen(new GameEndScreen(game, gameState.getLives()));
	}

}
