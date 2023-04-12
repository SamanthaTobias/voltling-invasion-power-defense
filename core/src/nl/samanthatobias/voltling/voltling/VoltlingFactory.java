package nl.samanthatobias.voltling.voltling;

import com.badlogic.gdx.graphics.Texture;

public class VoltlingFactory {

	public static Voltling createLesserVoltling() {
		String name = "Lesser Voltling";
		Texture lesserVoltlingTexture = new Texture("texture/Goblins/Crazed Goblin Priest-1.png");
		return new Voltling(name, 1, 500, lesserVoltlingTexture, 32);
	}

}
