package nl.samantha.voltling.voltling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.level.PathActions;
import nl.samanthatobias.voltling.voltling.Voltling;

import nl.samantha.test.TestApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VoltlingTest {

	private PathActions mockPathActions;
	private Label mockLabel;

	@BeforeAll
	public static void setupGdx() {
		Gdx.app = new TestApplication();
	}

	@BeforeEach
	public void setup() {
		mockPathActions = mock(PathActions.class);
		mockLabel = mock(Label.class);
		Vector2 startPoint = new Vector2(0, 0);
		when(mockPathActions.getStartPoint()).thenReturn(startPoint);
	}

	@Test
	public void testVoltlingCreation() {
		Voltling voltling = new Voltling(mockPathActions, "TestVoltling", 10, 5, mockLabel);
		assertEquals("TestVoltling", voltling.getName());
		assertEquals(10, voltling.getPower());
	}

	@Test
	public void testVoltlingMovement() {
		Array<Vector2> pathPoints = new Array<>();
		pathPoints.add(new Vector2(0, 0));
		pathPoints.add(new Vector2(100, 0));
		when(mockPathActions.getPoints()).thenReturn(pathPoints);

		Voltling voltling = new Voltling(mockPathActions, "TestVoltling", 1, 4, mockLabel);

		voltling.act(3); // Simulate 1 second of time passing

		assertEquals(3*4, voltling.getX());
		assertEquals(0, voltling.getY());
	}

	@Test
	public void testIsAtPoint() {
		Voltling voltling = new Voltling(mockPathActions, "TestVoltling", 10, 5, mockLabel);

		Vector2 point1 = new Vector2(0, 0);
		Vector2 point2 = new Vector2(10, 10);

		assertTrue(voltling.isAtPoint(point1));
		assertFalse(voltling.isAtPoint(point2));
	}

	@Test
	public void testUpdatePosition() {
		Voltling voltling = new Voltling(mockPathActions, "TestVoltling", 10, 5, mockLabel);

		float dx = 3.0f;
		float dy = 4.0f;

		voltling.updatePosition(dx, dy);

		assertEquals(dx, voltling.getX());
		assertEquals(dy, voltling.getY());
	}

}