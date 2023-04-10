package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import nl.samanthatobias.voltling.VoltlingGame;

public class GameEndScreen extends Screen {

	private final Label remainingLivesLabel;
	private final TextButton mainMenuButton;

	public GameEndScreen(final VoltlingGame game, int remainingLives) {
		super(game);

		remainingLivesLabel = setupRemainingLivesLabel(remainingLives);
		mainMenuButton = setupMainMenuButton();
	}

	private TextButton setupMainMenuButton() {
		final TextButton button;
		button = new TextButton("Main Menu", buttonSkin);
		button.setPosition(buttonStage.getWidth() / 2 - button.getWidth() / 2, buttonStage.getHeight() / 4);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenuScreen(game));
			}
		});
		buttonStage.addActor(button);
		return button;
	}

	private Label setupRemainingLivesLabel(int remainingLives) {
		final Label label;
		label = new Label("Remaining Lives: " + remainingLives, buttonSkin);
		label.setPosition(buttonStage.getWidth() / 2 - label.getWidth() / 2, buttonStage.getHeight() * 3 / 4);
		buttonStage.addActor(label);
		return label;
	}

}
