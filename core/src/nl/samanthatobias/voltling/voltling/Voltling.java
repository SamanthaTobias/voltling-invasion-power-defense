package nl.samanthatobias.voltling.voltling;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Voltling extends Actor {

	private final Label sprite;
	private final Vector2 position;
	private final int power;
	private final int speed;

	public Voltling(String name, Vector2 position, int power, int speed, Skin skin) {
		this.position = position;
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

		float dx = delta * speed;
		updatePosition(dx, 0f);
	}

	public Label getSprite() {
		return sprite;
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

	public int getSpeed() {
		return speed;
	}

}