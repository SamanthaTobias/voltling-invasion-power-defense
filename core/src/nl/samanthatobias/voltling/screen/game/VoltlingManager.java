package nl.samanthatobias.voltling.screen.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.utils.ArrayUtils;
import nl.samanthatobias.voltling.voltling.Voltling;

public class VoltlingManager {

	private final GameStateActions gameStateActions;
	private final Array<Voltling> voltlings;
	private final Stage stage;
	private final Skin skin;

	public VoltlingManager(GameStateActions gameStateActions, Stage stage, Skin skin) {
		this.gameStateActions = gameStateActions;
		this.stage = stage;
		this.skin = skin;
		voltlings = new Array<>();
	}

	public void spawnLesserVoltling() {
		Voltling lesserVoltling = new Voltling("Lesser Voltling", new Vector2(0, stage.getHeight() / 2), 1, 100, skin);
		voltlings.add(lesserVoltling);
		stage.addActor(lesserVoltling.getSprite());
	}

	public void updateVoltlings(float delta) {
		for (Voltling voltling : ArrayUtils.iterator(voltlings)) {
			float dx = delta * voltling.getSpeed();
			voltling.updatePosition(dx, 0f);
			if (voltling.getX() >= stage.getWidth()) {
				gameStateActions.removeLives(voltling.getPower());
				voltling.getSprite().remove();
				voltlings.removeValue(voltling, true);
			}
		}
	}

}
