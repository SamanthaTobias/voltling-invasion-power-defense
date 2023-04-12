package nl.samanthatobias.voltling.voltling;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.level.PathActions;
import nl.samanthatobias.voltling.utils.logger.Logger;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;

public class Voltling extends Actor {

	private static final Logger log = createLogger(Voltling.class);

	private final Sprite sprite;
	private final Texture texture;
	private final int power;
	private final int speed;

	private boolean placed = false;
	private boolean alive = true;
	private PathActions pathActions;
	private Vector2 position;
	private int pathIndex = 0;

	public Voltling(String name, int power, int speed, Texture texture, int size) {
		setName(name);
		this.sprite = new Sprite(texture);
		sprite.setSize(size, size);
		this.texture = texture;
		this.power = power;
		this.speed = speed;
	}

	public void place(PathActions path) {
		if (placed) {
			throw new IllegalStateException("Already placed.");
		}
		pathActions = path;
		position = pathActions.getStartPoint();
		sprite.setPosition(position.x, position.y);
		placed = true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
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
				position.set(nextPoint);
				pathIndex++;
			}
		}
	}

	@Override
	public boolean remove() {
		alive = false;
		return super.remove();
	}

	public boolean isAtPoint(Vector2 otherPosition) {
		return position.epsilonEquals(otherPosition, 0.1f);
	}

	@Override
	public float getX() {
		return position.x;
	}

	@Override
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

	public boolean isAlive() {
		if (alive && !placed) {
			throw new IllegalStateException();
		}
		return alive;
	}

	public Voltling clone() {
		return new Voltling(getName(), power, speed, texture, 32);
	}

}