package nl.samanthatobias.voltling.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.tower.Tower;
import nl.samanthatobias.voltling.utils.logger.Logger;

public class Path implements PathActions {

	private static final Logger log = Logger.createLogger(Path.class);

	private final Array<Vector2> points;
	private final float width;

	Path(Array<Vector2> points) {
		this.points = points;
		this.width = 1f;
	}

	@Override
	public Array<Vector2> getPoints() {
		return new Array<>(points);
	}

	public void render() {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.PINK);
		for (int i = 0; i < points.size - 1; i++) {
			Vector2 point1 = points.get(i);
			Vector2 point2 = points.get(i + 1);
			shapeRenderer.line(point1.x, point1.y, point2.x, point2.y);
		}
		shapeRenderer.end();
	}

	@Override
	public Vector2 getStartPoint() {
		return new Vector2(points.first());
	}

	@Override
	public Vector2 getEndPoint() {
		return new Vector2(points.peek());
	}

	public boolean overlapsWith(Tower tower) { // TODO does not really work
		Rectangle towerRectangle = tower.getBoundingRectangle();

		// Inflate the tower's bounding rectangle by adding half of the path width to each side
		Rectangle inflatedTowerRectangle = new Rectangle(towerRectangle.x - width / 2, towerRectangle.y - width / 2,
				towerRectangle.width + width, towerRectangle.height + width);

		for (int i = 0; i < points.size; i++) {
			Vector2 point = points.get(i);

			if (inflatedTowerRectangle.contains(point)) {
				log.debug("Contains point x={}, y={}.", point.x, point.y);
				return true;
			}
		}
		return false;
	}

}
