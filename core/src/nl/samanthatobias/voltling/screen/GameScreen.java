package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import nl.samanthatobias.voltling.VoltlingGame;
import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.core.GameState;
import nl.samanthatobias.voltling.level.Path;
import nl.samanthatobias.voltling.level.Paths;
import nl.samanthatobias.voltling.ui.GameScreenUI;
import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class GameScreen extends Screen implements GameScreenActions {

	private static final Logger log = createLogger(GameScreen.class);

	private final GameState gameState;
	private final GameScreenUI gameScreenUI;

	private final OrthographicCamera camera;
	private final Stage actorStage;

	public GameScreen(VoltlingGame game) {
		super(game);

		Gdx.gl.glClearColor(0, 0.1f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		int lives = Config.STARTING_LIVES;
		Path path = Paths.createBasicPath();
		actorStage = new Stage();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Skin voltlingSkin = buttonSkin; // TODO
		gameState = new GameState(this, actorStage, path, lives, voltlingSkin);
		gameScreenUI = new GameScreenUI(this, gameState, buttonStage, buttonSkin, lives);

		Gdx.input.setInputProcessor(new InputMultiplexer(buttonStage, actorStage));
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 0.1f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameState.renderPath();

		if (gameState.isPlaying()) {
			gameState.gameLoop(delta);
		}

		actorStage.draw();

		if (gameScreenUI.isPlacingTower()) {
			updateAndDrawPlacingTower();
		}

		buttonStage.act(delta);
		buttonStage.draw();
	}

	@Override
	public void dispose() {
		if (gameScreenUI.isPlacingTower()) {
			gameScreenUI.dispose();
		}
	}

	@Override
	public void show() {
		// TODO avoid triggering when clicking UI element. implement InputProcessor?
		buttonStage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (button == Input.Buttons.LEFT && gameScreenUI.isPlacingTower()) {
					gameScreenUI.handlePlaceTowerClick(x, y);
					return true;
				}
				return false;
			}
		});
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

	private void updateAndDrawPlacingTower() {
		Vector3 cursorPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		gameScreenUI.updatePlacingTowerPosition(cursorPosition);
		camera.unproject(cursorPosition);
	}

	private void endGame() {
		log.info("Ending game.");
		game.setScreen(new GameEndScreen(game, gameState.getLives()));
	}

}
