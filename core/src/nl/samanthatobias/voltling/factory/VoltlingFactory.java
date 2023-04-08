package nl.samanthatobias.voltling.factory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import nl.samanthatobias.voltling.level.PathActions;
import nl.samanthatobias.voltling.voltling.Voltling;

public class VoltlingFactory {

	public static Voltling createLesserVoltling(PathActions pathActions, Skin skin) {
		return new Voltling(pathActions, "Lesser Voltling", 1, 100, skin);
	}

}
