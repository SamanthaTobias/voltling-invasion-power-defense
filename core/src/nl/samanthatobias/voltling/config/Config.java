package nl.samanthatobias.voltling.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class Config {

	private final static Logger log = createLogger(Config.class);

	private static final int startingLives;
	private static final boolean debugDrainLife;
	private static final int debugDrainLifeAmount;

	static {
		log.info("Reading config.json");
		JsonReader reader = new JsonReader();
		JsonValue json = reader.parse(Gdx.files.internal("config/config.json"));

		startingLives = json.getInt("startingLives");
		debugDrainLife = json.getBoolean("debugDrainLife");
		debugDrainLifeAmount = json.getInt("debugDrainLifeAmount");
	}

	public static boolean isDebugDrainLife() {
		return debugDrainLife;
	}

	public static int getDebugDrainLifeAmount() {
		return debugDrainLifeAmount;
	}

	public static int getStartingLives() {
		return startingLives;
	}

}
