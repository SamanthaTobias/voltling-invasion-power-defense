package nl.samanthatobias.voltling.config;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class Config {

	private static final Logger log = createLogger(Config.class);

	public static final int STARTING_LIVES;
	public static final boolean DEBUG_DRAIN_LIFE;
	public static final int DEBUG_DRAIN_LIFE_AMOUNT;
	public static final boolean DEBUG_SMALL_PATH;

	private static final String LOG_LEVEL;

	private static boolean logLevelSet = false;

	static {
		log.info("Reading config.json");
		JsonReader reader = new JsonReader();
		JsonValue json = reader.parse(Gdx.files.internal("config/config.json"));

		STARTING_LIVES = json.getInt("startingLives");
		DEBUG_DRAIN_LIFE = json.getBoolean("debugDrainLife");
		DEBUG_DRAIN_LIFE_AMOUNT = json.getInt("debugDrainLifeAmount");
		DEBUG_SMALL_PATH = json.getBoolean("debugSmallPath");

		LOG_LEVEL = json.getString("logLevel");
	}

	@SuppressWarnings("GDXJavaLogLevel")
	public static void setLogLevel() {
		if (logLevelSet) {
			throw new IllegalStateException("Log level already set.");
		}
		switch (LOG_LEVEL.toLowerCase()) {
			case "debug":
				Gdx.app.setLogLevel(Application.LOG_DEBUG);
				break;
			case "info":
				Gdx.app.setLogLevel(Application.LOG_INFO);
				break;
			case "error":
				Gdx.app.setLogLevel(Application.LOG_ERROR);
				break;
			case "none":
				Gdx.app.setLogLevel(Application.LOG_NONE);
				break;
			default:
				throw new IllegalStateException("Invalid log level: " + LOG_LEVEL);
		}
		logLevelSet = true;
	}

}
