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

	public void info(String format, Object... args) {
		info(format(format, args));
	}

	public void error(String message) {
		Gdx.app.error(tag, message);
	}

	public void error(String format, Object... args) {
		error(format(format, args));
	}

	public void debug(String message) {
		Gdx.app.debug(tag, message);
	}

	public void debug(String format, Object... args) {
		debug(format(format, args));
	}

	private static String format(String message, Object... args) {
		int argIndex = 0;
		while (message.contains("{}") && argIndex < args.length) {
			message = message.replaceFirst("\\{\\}", args[argIndex].toString());
			argIndex++;
		}
		return message;
	}

}
