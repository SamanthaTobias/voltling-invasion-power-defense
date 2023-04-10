package nl.samanthatobias.voltling.core;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.level.PathActions;
import nl.samanthatobias.voltling.screen.GameScreenActions;
import nl.samanthatobias.voltling.utils.logger.Logger;
import nl.samanthatobias.voltling.voltling.VoltlingManager;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class GameLogic {

	private static final Logger log = createLogger(GameLogic.class);

	private final GameScreenActions gameScreenActions;
	private final GameStateActions gameStateActions;
	private final VoltlingManager voltlingManager;

	private final PathActions pathActions;
	private final Stage stage;

	private float timeSinceLastDrainLife = 0f;

	public GameLogic(GameScreenActions gameScreenActions, GameStateActions gameStateActions, PathActions pathActions,
					 Stage stage,
					 Skin skin) { // TODO merge this class with GameState,  add TowerManager? make VoltlingManager and TowerManager fields in this class?
		this.gameScreenActions = gameScreenActions;
		this.gameStateActions = gameStateActions;
		this.pathActions = pathActions;
		this.stage = stage;
		this.voltlingManager = new VoltlingManager(gameStateActions, pathActions, stage, skin);

		voltlingManager.spawnLesserVoltling();
	}

	public void gameLoop(float delta) {
		if (Config.DEBUG_DRAIN_LIFE) {
			timeSinceLastDrainLife += delta;
			if (timeSinceLastDrainLife >= 1f) {
				log.debug("Draining life.");
				gameStateActions.removeLives(Config.DEBUG_DRAIN_LIFE_AMOUNT * (int) timeSinceLastDrainLife);
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

}
