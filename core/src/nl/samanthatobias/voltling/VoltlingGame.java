package nl.samanthatobias.voltling;

import com.badlogic.gdx.Game;

import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.screen.MainMenuScreen;

public class VoltlingGame extends Game {
	
	@Override
	public void create () {
		setScreen(new MainMenuScreen(this));
		Config.setLogLevel();
	}

}
