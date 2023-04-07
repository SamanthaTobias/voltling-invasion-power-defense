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
		final TextButton mainMenuButton;
		mainMenuButton = new TextButton("Main Menu", uiSkin);
		mainMenuButton.setPosition(uiStage.getWidth() / 2 - mainMenuButton.getWidth() / 2, uiStage.getHeight() / 4);
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenuScreen(game));
			}
		});
		uiStage.addActor(mainMenuButton);
		return mainMenuButton;
	}

	private Label setupRemainingLivesLabel(int remainingLives) {
		final Label remainingLivesLabel;
		remainingLivesLabel = new Label("Remaining Lives: " + remainingLives, uiSkin);
		remainingLivesLabel.setPosition(uiStage.getWidth() / 2 - remainingLivesLabel.getWidth() / 2, uiStage.getHeight() * 3 / 4);
		uiStage.addActor(remainingLivesLabel);
		return remainingLivesLabel;
	}

}
