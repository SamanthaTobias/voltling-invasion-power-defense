package nl.samanthatobias.voltling.screen.gamescreen;

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

	private Label setupLivesLabel(int startingLives) {
		final Label label;
		label = new Label("Lives: " + startingLives, skin);
		label.setPosition(10, stage.getHeight() - label.getHeight() - 10);
		stage.addActor(label);
		return label;
	}

	private TextButton setupExitButton() {
		final TextButton button;
		button = new TextButton("Exit", skin);
		button.setPosition(stage.getWidth() - button.getWidth() - 10, playPauseButton.getHeight() + 20);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gameScreenActions.onExitGame();
			}
		});
		stage.addActor(button);
		return button;
	}

	private TextButton setupPlayPauseButton() {
		final TextButton button;
		button = new TextButton("Play", skin);
		button.setPosition(stage.getWidth() - button.getWidth() - 10, 10);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				boolean playing = gameScreenActions.onPlayPauseToggle();
				button.setText(playing ? "Pause" : "Play");
			}
		});
		stage.addActor(button);
		return button;
	}

}
