package nl.samantha.voltling.utils.screen;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import nl.samanthatobias.voltling.utils.logger.GdxLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static nl.samanthatobias.voltling.utils.logger.Logger.createLogger;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GdxLoggerTest {

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
	void testInfoLogging() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = (GdxLogger) createLogger(GdxLoggerTest.class);
		logger.info("Info message");

		verify(app, times(1)).log(Mockito.contains("LOG"), Mockito.contains("Info message"));
	}

	@Test
	void testErrorLogging() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = (GdxLogger) createLogger(GdxLoggerTest.class);
		logger.error("Error message");

		verify(app, times(1)).error(Mockito.contains("ERR"), Mockito.contains("Error message"));
	}

	@Test
	void testDebugLogging() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = (GdxLogger) createLogger(GdxLoggerTest.class);
		logger.debug("Debug message");

		verify(app, times(1)).debug(Mockito.contains("DBG"), Mockito.contains("Debug message"));
	}

	@Test
	void testFormattedMessage() {
		Application app = Mockito.mock(Application.class);
		Gdx.app = app;

		GdxLogger logger = (GdxLogger) createLogger(GdxLoggerTest.class);
		logger.info("Hello, {}!", "World");

		verify(app, times(1)).log(Mockito.contains("LOG"), Mockito.contains("Hello, World!"));
	}

}
