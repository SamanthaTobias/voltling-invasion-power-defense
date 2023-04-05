package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import nl.samanthatobias.voltling.VoltlingGame;

public class LevelSelectScreen extends Screen {

	public LevelSelectScreen(final VoltlingGame game) {
		super(game);

		TextButton level1Button = new TextButton("Level 1", skin);
		level1Button.setPosition(stage.getWidth() / 2 - level1Button.getWidth() / 2, stage.getHeight() / 2);
		level1Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new GameScreen(game));
			}
		});
		stage.addActor(level1Button);

		TextButton backButton = new TextButton("Back", skin);
		backButton.setPosition(10, stage.getHeight() - backButton.getHeight() - 10);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainMenuScreen(game));
			}
		});

		stage.addActor(backButton);
	}

}
