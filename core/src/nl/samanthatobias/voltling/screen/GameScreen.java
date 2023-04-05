package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import nl.samanthatobias.voltling.VoltlingGame;

public class GameScreen extends Screen {

	private final Label livesLabel;
	private final TextButton playPauseButton;
	private final TextButton exitButton;

	private boolean isPlaying;
	private int totalLives;

	public GameScreen(final VoltlingGame game) {
		super(game);

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		totalLives = 100;

		livesLabel = setupLivesLabel();
		playPauseButton = setupPlayPauseButton();
		exitButton = setupExitButton();
	}

	private Label setupLivesLabel() {
		final Label livesLabel;
		livesLabel = new Label("Lives: " + totalLives, skin);
		livesLabel.setPosition(10, stage.getHeight() - livesLabel.getHeight() - 10);
		stage.addActor(livesLabel);
		return livesLabel;
	}

	private TextButton setupExitButton() {
		final TextButton exitButton;
		exitButton = new TextButton("Exit", skin);
		exitButton.setPosition(stage.getWidth() - exitButton.getWidth() - 10, playPauseButton.getHeight() + 20);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new GameEndScreen(game, totalLives));
			}
		});
		stage.addActor(exitButton);
		return exitButton;
	}

	private TextButton setupPlayPauseButton() {
		final TextButton playPauseButton;
		playPauseButton = new TextButton("Play", skin);
		playPauseButton.setPosition(stage.getWidth() - playPauseButton.getWidth() - 10, 10);
		playPauseButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				isPlaying = !isPlaying;
				playPauseButton.setText(isPlaying ? "Pause" : "Play");
			}
		});
		stage.addActor(playPauseButton);
		return playPauseButton;
	}

}
