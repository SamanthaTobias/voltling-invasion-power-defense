package nl.samanthatobias.voltling.voltling;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.level.PathActions;
import nl.samanthatobias.voltling.utils.GdxLogger;

public class Voltling extends Actor {

	private final static GdxLogger log = new GdxLogger(Voltling.class);

	private final PathActions pathActions;
	private final Label sprite;
	private final Vector2 position;
	private final int power;
	private final int speed;

	private int pathIndex = 0;

	public Voltling(PathActions pathActions, String name, int power, int speed, Skin skin) {
		this.pathActions = pathActions;
		setName(name);
		this.position = pathActions.getStartPoint();
		this.sprite = createSprite(name, skin);
		this.power = power;
		this.speed = speed;
	}

	private Label createSprite(String name, Skin skin) {
		Label sprite = new Label(name, skin);
		sprite.setPosition(position.x, position.y);
		return sprite;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		Array<Vector2> pathPoints = pathActions.getPoints();
		if (pathIndex < pathPoints.size - 1) {
			Vector2 nextPoint = pathPoints.get(pathIndex + 1);
			Vector2 direction = new Vector2(nextPoint).sub(position).nor();
			float dx = direction.x * delta * speed;
			float dy = direction.y * delta * speed;

			updatePosition(dx, dy);

			if (position.dst2(nextPoint) < (dx * dx + dy * dy)) {
				log.debug("Voltling reached next point.");
				position.set(nextPoint);
				pathIndex++;
			}
		}
	}

	@Override
	public boolean remove() {
		sprite.remove();
		return super.remove();
	}

	public boolean isAtPoint(Vector2 otherPosition) {
		return position.epsilonEquals(otherPosition, 0.1f);
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public void updatePosition(float dx, float dy) {
		position.x += dx;
		position.y += dy;
		sprite.setPosition(position.x, position.y);
	}

	public int getPower() {
		return power;
	}

}