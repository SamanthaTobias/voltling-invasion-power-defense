package nl.samanthatobias.voltling.voltling;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Voltling {

	private final Label sprite;
	private final Vector2 position;
	private final int power;
	private final int speed;

	public Voltling(String name, Vector2 position, int power, int speed, Skin skin) {
		this.sprite = new Label(name, skin);
		this.position = position;
		this.power = power;
		this.speed = speed;
		sprite.setPosition(position.x, position.y);
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