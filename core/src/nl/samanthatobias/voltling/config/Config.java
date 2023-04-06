package nl.samanthatobias.voltling.config;

import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Config {

	private final Properties properties;

	public Config() {
		properties = new Properties();
		try {
			properties.load(Gdx.files.internal("config/config.properties").reader());
		} catch (IOException e) {
			throw new GdxRuntimeException("Error loading config.properties", e);
		}
	}

	public boolean isDebugDrainLife() {
		return Boolean.parseBoolean(properties.getProperty("debug.drainlife", "false"));
	}

	public int getDebugDrainLifeAmount() {
		return Integer.parseInt(properties.getProperty("debug.drainlife.amount", "1"));
	}

}
