package nl.samanthatobias.voltling;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import nl.samanthatobias.voltling.screen.MainMenuScreen;

public class VoltlingGame extends Game {
	
	@Override
	public void create () {
		setScreen(new MainMenuScreen(this));
		Gdx.app.setLogLevel(Application.LOG_DEBUG); // TODO make configurable in config.json
	}

}
