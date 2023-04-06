package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.VoltlingGame;
import nl.samanthatobias.voltling.config.Config;
import nl.samanthatobias.voltling.voltling.Voltling;

public class GameScreen extends Screen {

	private final Config config;
	private final Label livesLabel;
	private final TextButton playPauseButton;
	private final TextButton exitButton;
	private final Array<Voltling> voltlings;

	private float timeSinceLastDrainLife = 0f;
	private boolean isPlaying;
	private int lives;

	public GameScreen(final VoltlingGame game) {
		super(game);
		config = new Config();

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		lives = 100;

		livesLabel = setupLivesLabel();
		playPauseButton = setupPlayPauseButton();
		exitButton = setupExitButton();

		voltlings = new Array<>();
		spawnLesserVoltling();
	}

	private Label setupLivesLabel() {
		final Label livesLabel;
		livesLabel = new Label("Lives: " + lives, skin);
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
				endGame();
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

	private void endGame() {
		game.setScreen(new GameEndScreen(game, lives));
	}

	private void spawnLesserVoltling() {
		Voltling lesserVoltling = new Voltling("Lesser Voltling", new Vector2(0, stage.getHeight() / 2), 1, 100, skin);
		voltlings.add(lesserVoltling);
		stage.addActor(lesserVoltling.getSprite());
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (isPlaying) {
			progressGameTic(delta);
		}
	}

	private void progressGameTic(float delta) {
		if (config.isDebugDrainLife()) {
			timeSinceLastDrainLife += delta;
			if (timeSinceLastDrainLife >= 1f) {
				removeLives(config.getDebugDrainLifeAmount() * (int) timeSinceLastDrainLife);
				timeSinceLastDrainLife = 0f;
			}
		}

		for (Voltling voltling : voltlings) {
			float dx = delta * voltling.getSpeed();
			voltling.updatePosition(dx, 0f);
			if (voltling.getX() >= stage.getWidth()) {
				removeLives(voltling.getPower());
				voltling.getSprite().remove();
				voltlings.removeValue(voltling, true);
			}
		}

		if (lives <= 0) {
			endGame();
		}
	}

	private void removeLives(int delta) {
		lives -= delta;
		livesLabel.setText("Lives: " + lives);
	}

}
