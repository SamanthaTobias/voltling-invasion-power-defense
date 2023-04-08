package nl.samanthatobias.voltling.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Config {

	private static final int startingLives;
	private static final boolean debugDrainLife;
	private static final int debugDrainLifeAmount;

	static {
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
