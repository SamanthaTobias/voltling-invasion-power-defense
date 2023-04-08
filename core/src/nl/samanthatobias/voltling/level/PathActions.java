package nl.samanthatobias.voltling.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public interface PathActions {

	Array<Vector2> getPoints();

	Vector2 getStartPoint();

	Vector2 getEndPoint();

}
