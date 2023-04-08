package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import nl.samanthatobias.voltling.VoltlingGame;
import nl.samanthatobias.voltling.screen.gamescreen.GameScreen;

public class LevelSelectScreen extends Screen {

	public LevelSelectScreen(final VoltlingGame game) {
		super(game);

		TextButton level1Button = new TextButton("Level 1", uiSkin);
		level1Button.setPosition(uiStage.getWidth() / 2 - level1Button.getWidth() / 2, uiStage.getHeight() / 2);
		level1Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new GameScreen(game));
			}
		});
		uiStage.addActor(level1Button);

		TextButton backButton = new TextButton("Back", uiSkin);
		backButton.setPosition(10, uiStage.getHeight() - backButton.getHeight() - 10);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenuScreen(game));
			}
		});

		uiStage.addActor(backButton);
	}

}
