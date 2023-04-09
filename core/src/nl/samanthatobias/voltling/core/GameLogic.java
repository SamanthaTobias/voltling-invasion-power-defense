package nl.samanthatobias.voltling.core;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.level.Path;
import nl.samanthatobias.voltling.screen.gamescreen.GameScreenActions;
import nl.samanthatobias.voltling.utils.logger.Logger;
import nl.samanthatobias.voltling.voltling.VoltlingManager;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class GameLogic {

	private static final Logger log = createLogger(GameLogic.class);

	private final GameScreenActions gameScreenActions;
	private final GameStateActions gameStateActions;
	private final VoltlingManager voltlingManager;

	private final Path path;
	private final Stage stage;

	private float timeSinceLastDrainLife = 0f;

	public GameLogic(GameScreenActions gameScreenActions, GameStateActions gameStateActions, Path path, Stage stage,
					 Skin skin) {
		this.gameScreenActions = gameScreenActions;
		this.gameStateActions = gameStateActions;
		this.path = path;
		this.stage = stage;
		this.voltlingManager = new VoltlingManager(gameStateActions, path, stage, skin);

		voltlingManager.spawnLesserVoltling();
	}

	public void gameLoop(float delta) {
		if (Config.isDebugDrainLife()) {
			timeSinceLastDrainLife += delta;
			if (timeSinceLastDrainLife >= 1f) {
				log.debug("Draining life.");
				gameStateActions.removeLives(Config.getDebugDrainLifeAmount() * (int) timeSinceLastDrainLife);
				timeSinceLastDrainLife = 0f;
			}
		}

		stage.act(delta);
		voltlingManager.updateVoltlings();

		gameScreenActions.onChangeLives();

		if (gameStateActions.isGameOver()) {
			gameScreenActions.onExitGame();
		}
	}

	public void renderPath() {
		path.render();
	}

}
