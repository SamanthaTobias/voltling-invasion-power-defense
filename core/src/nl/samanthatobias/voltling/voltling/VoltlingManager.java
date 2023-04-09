package nl.samanthatobias.voltling.voltling;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.core.GameStateActions;
import nl.samanthatobias.voltling.factory.VoltlingFactory;
import nl.samanthatobias.voltling.level.PathActions;
import nl.samanthatobias.voltling.utils.ArrayUtils;
import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class VoltlingManager {

	private final static Logger log = createLogger(VoltlingManager.class);

	private final GameStateActions gameStateActions;
	private final PathActions pathActions;
	private final Array<Voltling> voltlings;
	private final Stage stage;
	private final Skin skin;

	public VoltlingManager(GameStateActions gameStateActions, PathActions pathActions, Stage stage, Skin skin) {
		this.gameStateActions = gameStateActions;
		this.pathActions = pathActions;
		this.stage = stage;
		this.skin = skin;
		voltlings = new Array<>();
	}

	public void spawnLesserVoltling() {
		log.debug("Spawning Lesser Voltling.");
		Voltling lesserVoltling = VoltlingFactory.createLesserVoltling(pathActions, skin);
		voltlings.add(lesserVoltling);
		stage.addActor(lesserVoltling);
	}

	public void updateVoltlings() {
		for (Voltling voltling : ArrayUtils.iterator(voltlings)) {
			if (voltling.isAtPoint(pathActions.getEndPoint())) {
				log.info(voltling.getName() + " has reached the end of its path.");
				gameStateActions.removeLives(voltling.getPower());
				voltling.remove();
				voltlings.removeValue(voltling, true);
			}
		}
	}

}
