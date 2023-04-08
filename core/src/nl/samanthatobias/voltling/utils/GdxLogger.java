package nl.samanthatobias.voltling.utils;

import com.badlogic.gdx.Gdx;

public class GdxLogger {

	// TODO: log the time
	// TODO: log the log level

	private final String tag;

	public GdxLogger(Class<?> clazz) {
		this.tag = clazz.getSimpleName();
	}

	public void info(String message) {
		Gdx.app.log(tag, message);
	}

	public void error(String message) {
		Gdx.app.error(tag, message);
	}

	public void debug(String message) {
		Gdx.app.debug(tag, message);
	}

}
