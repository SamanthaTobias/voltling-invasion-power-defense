package nl.samanthatobias.voltling.level;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.tower.Tower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {
	private Array<Vector2> points;
	private Path path;

	@BeforeEach
	void setUp() {
		points = new Array<>();
		points.add(new Vector2(0, 0));
		points.add(new Vector2(1, 1));
		points.add(new Vector2(2, 2));
		path = new Path(points);
	}

	@Test
	void getPoints_returnsCorrectPoints() {
		Array<Vector2> pathPoints = path.getPoints();
		assertArrayEquals(points.toArray(), pathPoints.toArray());
	}

	@Test
	void getStartPoint_returnsCorrectPoint() {
		Vector2 startPoint = path.getStartPoint();
		assertEquals(points.first(), startPoint);
	}

	@Test
	void getEndPoint_returnsCorrectPoint() {
		Vector2 endPoint = path.getEndPoint();
		assertEquals(points.peek(), endPoint);
	}

	@Test
	void overlapsWith_overlappingTower_returnsTrue() {
		Tower tower = Mockito.mock(Tower.class);
		Mockito.when(tower.getBoundingRectangle()).thenReturn(new Rectangle(0, 0, 1, 1));

		assertTrue(path.overlapsWith(tower));
	}

	@Test
	void overlapsWith_nonOverlappingTower_returnsFalse() {
		Tower tower = Mockito.mock(Tower.class);
		Mockito.when(tower.getBoundingRectangle()).thenReturn(new Rectangle(3, 3, 1, 1));

		assertFalse(path.overlapsWith(tower));
	}
}
