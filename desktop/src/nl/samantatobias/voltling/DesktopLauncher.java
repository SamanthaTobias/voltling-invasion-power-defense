package nl.samantatobias.voltling;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import nl.samantatobias.voltling.VoltlingGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Voltling Invasion: Power Defense");
		new Lwjgl3Application(new VoltlingGame(), config);
	}

}
