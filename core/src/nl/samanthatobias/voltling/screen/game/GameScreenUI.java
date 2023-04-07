package nl.samanthatobias.voltling.screen.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreenUI {

	private final GameScreenActions gameScreenActions;
	private final Stage stage;
	private final Skin skin;
	private final Label livesLabel;
	private final TextButton playPauseButton;
	private final TextButton exitButton;

	public GameScreenUI(GameScreenActions gameScreenActions, Stage stage, Skin skin, int startingLives) {
		this.gameScreenActions = gameScreenActions;
		this.stage = stage;
		this.skin = skin;
		livesLabel = setupLivesLabel(startingLives);
		playPauseButton = setupPlayPauseButton();
		exitButton = setupExitButton();
	}

	public void updateLivesLabel(int lives) {
		livesLabel.setText("Lives: " + lives);
	}

	public Label getLivesLabel() {
		return livesLabel;
	}

	public TextButton getPlayPauseButton() {
		return playPauseButton;
	}

	public TextButton getExitButton() {
		return exitButton;
	}

	private Label setupLivesLabel(int startingLives) {
		final Label livesLabel;
		livesLabel = new Label("Lives: " + startingLives, skin);
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
				gameScreenActions.onExitGame();
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
				boolean playing = gameScreenActions.onPlayPauseToggle();
				playPauseButton.setText(playing ? "Pause" : "Play");
			}
		});
		stage.addActor(playPauseButton);
		return playPauseButton;
	}

}
