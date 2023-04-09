package nl.samanthatobias.voltling.utils;

import com.badlogic.gdx.Gdx;

public class GdxLogger {

//	private final static DateFormat format = new SimpleDateFormat("HH:mm:ss");

	private final String className;

	public GdxLogger(Class<?> clazz) {
		this.className = clazz.getSimpleName();
	}

	public void info(String message) {
		Gdx.app.log(tag("LOG"), message);
	}

	public void info(String format, Object... args) {
		info(format(format, args));
	}

	public void error(String message) {
		Gdx.app.error(tag("ERR"), message);
	}

	public void error(String format, Object... args) {
		error(format(format, args));
	}

	public void debug(String message) {
		Gdx.app.debug(tag("DBG"), message);
	}

	public void debug(String format, Object... args) {
		debug(format(format, args));
	}

	private String tag(String logLevel) {
//		String time = format.format(TimeUtils.millis()); // TODO https://www.gwtproject.org/javadoc/latest/com/google/gwt/i18n/client/DateTimeFormat.html
//		return logLevel + " " + time + " " + className;
		return logLevel + " " + className;
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
