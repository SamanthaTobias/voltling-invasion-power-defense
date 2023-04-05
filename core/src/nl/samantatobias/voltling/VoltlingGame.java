package nl.samantatobias.voltling;

import com.badlogic.gdx.Game;
import nl.samantatobias.voltling.screen.MainMenuScreen;

public class VoltlingGame extends Game {
	
	@Override
	public void create () {
		setScreen(new MainMenuScreen(this));
	}

}
