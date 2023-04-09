package nl.samanthatobias.voltling.screen.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import nl.samanthatobias.voltling.VoltlingGame;
import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.core.GameLogic;
import nl.samanthatobias.voltling.core.GameState;
import nl.samanthatobias.voltling.level.Path;
import nl.samanthatobias.voltling.level.Paths;
import nl.samanthatobias.voltling.screen.GameEndScreen;
import nl.samanthatobias.voltling.screen.Screen;
import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class GameScreen extends Screen implements GameScreenActions {

	private final static Logger log = createLogger(GameScreen.class);

	private final GameState gameState;
	private final GameLogic gameLogic;
	private final GameScreenUI gameScreenUI;

	private final Stage voltlingStage;

	public GameScreen(VoltlingGame game) {
		super(game);

		Gdx.gl.glClearColor(0, 0.1f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		int lives = Config.getStartingLives();
		Path path = Paths.createBasicPath();
		gameState = new GameState(lives);
		voltlingStage = new Stage();
		Skin voltlingSkin = uiSkin; // TODO
		gameLogic = new GameLogic(this, gameState, path, voltlingStage, voltlingSkin);
		gameScreenUI = new GameScreenUI(this, uiStage, uiSkin, lives);

		Gdx.input.setInputProcessor(new InputMultiplexer(uiStage, voltlingStage));
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 0.1f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameLogic.renderPath();

		if (gameState.isPlaying()) {
			gameLogic.gameLoop(delta);
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

	@Override
	public void onChangeLives() {
		gameScreenUI.updateLivesLabel(gameState.getLives());
	}

	private void endGame() {
		log.info("Ending game.");
		game.setScreen(new GameEndScreen(game, gameState.getLives()));
	}

}
