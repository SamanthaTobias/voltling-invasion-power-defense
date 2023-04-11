package nl.samanthatobias.voltling.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import nl.samanthatobias.voltling.VoltlingGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(1600, 900);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new VoltlingGame();
        }

}
