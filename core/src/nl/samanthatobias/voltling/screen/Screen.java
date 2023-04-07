package nl.samanthatobias.voltling.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import nl.samanthatobias.voltling.VoltlingGame;

public abstract class Screen extends ScreenAdapter {

	protected final VoltlingGame game;
	protected final Stage uiStage;
	protected final Skin uiSkin;

	public Screen(VoltlingGame game) {
		this.game = game;
		uiStage = new Stage(new ScreenViewport());
		uiSkin = new Skin(Gdx.files.internal( "skins/glassy/glassy-ui.json"));
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(uiStage);
	}

	@Override
	public void resize(int width, int height) {
		uiStage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		uiStage.dispose();
		uiSkin.dispose();
	}

	@Override
	public void render(float delta) {
		// Clear the screen with a black background
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update and draw the stage
		uiStage.act(delta);
		uiStage.draw();
	}

}
