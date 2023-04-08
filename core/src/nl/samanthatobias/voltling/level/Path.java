package nl.samanthatobias.voltling.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Path {

	private final Array<Vector2> points;

	Path(Array<Vector2> points) {
		this.points = points;
	}

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

}
