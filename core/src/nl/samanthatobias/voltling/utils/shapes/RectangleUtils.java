package nl.samanthatobias.voltling.utils.shapes;

import com.badlogic.gdx.math.Rectangle;

import nl.samanthatobias.voltling.utils.logger.Logger;

public class RectangleUtils {

	private static final Logger log = Logger.createLogger(RectangleUtils.class);

	public static void logPosition(Rectangle rectangle) {
		log.debug("x: {}", rectangle.x);
		log.debug("y: {}", rectangle.y);
		log.debug("width: {}", rectangle.width);
		log.debug("height: {}", rectangle.height);
	}

}
