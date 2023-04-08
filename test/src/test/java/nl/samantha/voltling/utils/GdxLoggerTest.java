package nl.samantha.voltling.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import nl.samanthatobias.voltling.utils.GdxLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GdxLoggerTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void testInfoLogging() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = new GdxLogger(GdxLoggerTest.class);
		logger.info("Info message");

		verify(app, times(1)).log(Mockito.contains("LOG"), Mockito.contains("Info message"));
	}

	@Test
	public void testErrorLogging() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = new GdxLogger(GdxLoggerTest.class);
		logger.error("Error message");

		verify(app, times(1)).error(Mockito.contains("ERR"), Mockito.contains("Error message"));
	}

	@Test
	public void testDebugLogging() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = new GdxLogger(GdxLoggerTest.class);
		logger.debug("Debug message");

		verify(app, times(1)).debug(Mockito.contains("DBG"), Mockito.contains("Debug message"));
	}

	@Test
	public void testFormattedMessage() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = new GdxLogger(GdxLoggerTest.class);
		logger.info("Hello, {}!", "World");

		verify(app, times(1)).log(Mockito.contains("LOG"), Mockito.contains("Hello, World!"));
	}

}