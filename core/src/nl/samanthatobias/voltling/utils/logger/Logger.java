package nl.samanthatobias.voltling.utils.logger;

public interface Logger {

	static Logger createLogger(Class<?> clazz) {
		return new GdxLogger(clazz);
	}

	void info(String message);

	void info(String format, Object... args);

	void error(String message);

	void error(String format, Object... args);

	void debug(String message);

	void debug(String format, Object... args);

}
