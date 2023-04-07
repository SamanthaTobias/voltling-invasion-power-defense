package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import nl.samanthatobias.voltling.VoltlingGame;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(final VoltlingGame game) {
		super(game);

		TextButton levelSelectButton = new TextButton("Level Select", uiSkin);
		levelSelectButton.setPosition(uiStage.getWidth() / 2 - levelSelectButton.getWidth() / 2, uiStage.getHeight() / 2);
		levelSelectButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new LevelSelectScreen(game));
			}
		});

		TextButton exitButton = new TextButton("Exit", uiSkin);
		exitButton.setPosition(uiStage.getWidth() / 2 - exitButton.getWidth() / 2, uiStage.getHeight() / 4);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		uiStage.addActor(levelSelectButton);
		uiStage.addActor(exitButton);
	}

}
