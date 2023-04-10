package nl.samanthatobias.voltling.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TowerFactory {

	public static Tower createTower() {
		Texture texture = new Texture(Gdx.files.internal("texture/Goblins/Crazed Goblin Priest-1.png"));
		return new Tower("Tower", texture);
	}

}
