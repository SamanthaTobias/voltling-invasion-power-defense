package nl.samanthatobias.voltling.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class Paths {

	private static final Logger log = createLogger(Paths.class);

	public static Path createBasicPath() {
		log.debug("Creating basic path.");

		Array<Vector2> pathPoints = new Array<>();
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		pathPoints.add(new Vector2(0, screenHeight / 2));
		pathPoints.add(new Vector2(screenWidth / 4, screenHeight / 4));
		pathPoints.add(new Vector2(screenWidth / 2, screenHeight / 2));
		pathPoints.add(new Vector2(screenWidth * 3/4, screenHeight / 4));
		pathPoints.add(new Vector2(screenWidth, screenHeight / 2));
		return new Path(pathPoints);
	}

}
