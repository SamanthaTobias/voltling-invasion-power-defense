package nl.samantatobias.voltling.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import nl.samantatobias.voltling.VoltlingGame;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(final VoltlingGame game) {
		super(game);

		TextButton levelSelectButton = new TextButton("Level Select", skin);
		levelSelectButton.setPosition(stage.getWidth() / 2 - levelSelectButton.getWidth() / 2, stage.getHeight() / 2);
		levelSelectButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new LevelSelectScreen(game));
			}
		});

		TextButton exitButton = new TextButton("Exit", skin);
		exitButton.setPosition(stage.getWidth() / 2 - exitButton.getWidth() / 2, stage.getHeight() / 4);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		stage.addActor(levelSelectButton);
		stage.addActor(exitButton);
	}

}
