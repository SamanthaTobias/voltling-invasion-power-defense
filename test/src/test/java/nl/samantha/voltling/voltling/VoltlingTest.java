package nl.samantha.voltling.voltling;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.level.PathActions;
import nl.samanthatobias.voltling.voltling.Voltling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VoltlingTest {

	private PathActions mockPathActions;
	private Texture mockTexture;

	@BeforeEach
	public void setup() {
		mockPathActions = mock(PathActions.class);
		mockTexture = mock(Texture.class);
		Gdx.app = Mockito.mock(Application.class);
		Vector2 startPoint = new Vector2(0, 0);
		when(mockPathActions.getStartPoint()).thenReturn(startPoint);
	}

	@Test
	void testVoltlingCreation() {
		Voltling voltling = createVoltling();
		assertEquals("TestVoltling", voltling.getName());
		assertEquals(10, voltling.getPower());
	}

	@Test
	void testVoltlingMovement() {
		Array<Vector2> pathPoints = new Array<>();
		pathPoints.add(new Vector2(0, 0));
		pathPoints.add(new Vector2(100, 0));
		when(mockPathActions.getPoints()).thenReturn(pathPoints);

		Voltling voltling = createVoltling();

		voltling.act(3);

		assertEquals(3*5, voltling.getX());
		assertEquals(0, voltling.getY());
	}

	@Test
	void testIsAtPoint() {
		Voltling voltling = createVoltling();

		Vector2 point1 = new Vector2(0, 0);
		Vector2 point2 = new Vector2(10, 10);

		assertTrue(voltling.isAtPoint(point1));
		assertFalse(voltling.isAtPoint(point2));
	}

	@Test
	void testUpdatePosition() {
		Voltling voltling = createVoltling();

		float dx = 3.0f;
		float dy = 4.0f;

		voltling.updatePosition(dx, dy);

		assertEquals(dx, voltling.getX());
		assertEquals(dy, voltling.getY());
	}

	private Voltling createVoltling() {
		Voltling voltling = new Voltling("TestVoltling", 10, 5, mockTexture, 32);
		voltling.place(mockPathActions);
		return voltling;
	}

}
