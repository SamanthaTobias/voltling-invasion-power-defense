package nl.samanthatobias.voltling.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Config {

	private final int startingLives;
	private final boolean debugDrainLife;
	private final int debugDrainLifeAmount;

	public Config() {
		JsonReader reader = new JsonReader();
		JsonValue json = reader.parse(Gdx.files.internal("config/config.json"));

		startingLives = json.getInt("startingLives");
		debugDrainLife = json.getBoolean("debugDrainLife");
		debugDrainLifeAmount = json.getInt("debugDrainLifeAmount");
	}

	public boolean isDebugDrainLife() {
		return debugDrainLife;
	}

	public int getDebugDrainLifeAmount() {
		return debugDrainLifeAmount;
	}

	public int getStartingLives() {
		return startingLives;
	}

}
