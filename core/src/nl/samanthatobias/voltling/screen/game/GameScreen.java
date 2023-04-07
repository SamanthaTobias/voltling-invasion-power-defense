package nl.samanthatobias.voltling.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	private final Stage voltlingStage;

	private float timeSinceLastDrainLife = 0f;

	public GameScreen(VoltlingGame game) {
		super(game);
		config = new Config();

		Gdx.gl.glClearColor(0, 0.1f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		int lives = config.getStartingLives();
		gameState = new GameState(lives);
		gameScreenUI = new GameScreenUI(this, uiStage, uiSkin, lives);
		voltlingStage = new Stage();
		voltlingManager = new VoltlingManager(gameState, voltlingStage, uiSkin);

		voltlingManager.spawnLesserVoltling();

		gameScreenUI.getExitButton().addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				endGame();
			}
		});

		Gdx.input.setInputProcessor(new InputMultiplexer(uiStage, voltlingStage));
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 0.1f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameState.renderPath();

		if (gameState.isPlaying()) {
			gameLoop(delta);
		}
		voltlingStage.draw();

		uiStage.act(delta);
		uiStage.draw();
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

	private void gameLoop(float delta) {
		if (config.isDebugDrainLife()) {
			timeSinceLastDrainLife += delta;
			if (timeSinceLastDrainLife >= 1f) {
				gameState.removeLives(config.getDebugDrainLifeAmount() * (int) timeSinceLastDrainLife);
				timeSinceLastDrainLife = 0f;
			}
		}

		voltlingStage.act(delta);
		voltlingManager.updateVoltlings();

		updateLivesLabel(gameState.getLives());

		if (gameState.isGameOver()) {
			endGame();
		}
	}

	private void endGame() {
		game.setScreen(new GameEndScreen(game, gameState.getLives()));
	}

}
