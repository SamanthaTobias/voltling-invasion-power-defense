package nl.samanthatobias.voltling.voltling;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import nl.samanthatobias.voltling.level.PathActions;

public class VoltlingFactory {

	public static Voltling createLesserVoltling(PathActions pathActions, Skin skin) {
		String name = "Lesser Voltling";
		return new Voltling(pathActions, name, 1, 100, createSprite(name, skin));
	}

	private static Label createSprite(String name, Skin skin) {
		return new Label(name, skin);
	}

}
