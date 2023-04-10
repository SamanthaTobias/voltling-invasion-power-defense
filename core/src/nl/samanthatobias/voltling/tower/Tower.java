package nl.samanthatobias.voltling.tower;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import nl.samanthatobias.voltling.utils.logger.Logger;

public class Tower extends Actor {

	private static final Logger log = Logger.createLogger(Tower.class);

	private final Texture texture;

	Tower(String name, Texture texture) {
		setName(name + new Random().nextInt());
		log.debug("Creating Tower with name {}.", getName());
		this.texture = texture;
		setSize(texture.getWidth(), texture.getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}

	public void setPositionFromCursor(float x, float y ) {
		x = x - getWidth() / 2;
		y = Gdx.graphics.getHeight() - y - getHeight() / 2;
		super.setPosition(x, y);
	}

	public Rectangle getBoundingRectangle() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public void dispose() {
		texture.dispose();
	}

}
