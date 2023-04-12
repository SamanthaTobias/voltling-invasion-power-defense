package nl.samanthatobias.voltling.ui;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import nl.samanthatobias.voltling.core.GameStateActions;
import nl.samanthatobias.voltling.screen.GameScreenActions;
import nl.samanthatobias.voltling.utils.logger.Logger;

public class GameScreenUI {

	private static final Logger log = Logger.createLogger(GameScreenUI.class);

	private final GameScreenActions gameScreenActions;
	private final TowerPlacementController towerPlacementController;
	private final Stage buttonStage;
	private final Skin buttonSkin;

	private final Label livesLabel;
	private final Label waveLabel;
	private final TextButton playPauseButton;
	private final TextButton exitButton;
	private final TextButton placeTowerButton;
	private final TextButton cancelPlaceTowerButton;

	private final int waveAmount;

	public GameScreenUI(GameScreenActions gameScreenActions, GameStateActions gameStateActions, Stage buttonStage, Skin buttonSkin,
						int startingLives, int waveAmount) {
		this.gameScreenActions = gameScreenActions;
		this.towerPlacementController = new TowerPlacementController(gameStateActions);
		this.buttonStage = buttonStage;
		this.buttonSkin = buttonSkin;
		this.waveAmount = waveAmount;

		livesLabel = setupLivesLabel(startingLives);
		waveLabel = setupWaveLabel();
		playPauseButton = setupPlayPauseButton();
		exitButton = setupExitButton();
		placeTowerButton = setupTowerButton();
		cancelPlaceTowerButton = setupCancelPlaceTowerButton();
	}

	public void updateLivesLabel(int lives) {
		livesLabel.setText("Lives: " + lives);
	}

	public void updateWaveLabel(int currentWaveIndex) {
		waveLabel.setText("Wave: " + (currentWaveIndex+1) + "/" + waveAmount);
	}

	private Label setupLivesLabel(int startingLives) {
		final Label label;
		label = new Label("Lives: " + startingLives, buttonSkin);
		label.setPosition(10, buttonStage.getHeight() - label.getHeight() - 10);
		buttonStage.addActor(label);
		return label;
	}

	private Label setupWaveLabel() {
		final Label label;
		label = new Label("Wave: 1/" + waveAmount, buttonSkin);
		label.setPosition(10 + livesLabel.getX() + livesLabel.getWidth(),
				buttonStage.getHeight() - label.getHeight() - 10);
		buttonStage.addActor(label);
		return label;
	}

	private TextButton setupExitButton() {
		final TextButton button;
		button = new TextButton("Exit", buttonSkin);
		button.setPosition(buttonStage.getWidth() - button.getWidth() - 10, playPauseButton.getHeight() + 20);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gameScreenActions.onExitGame();
			}
		});
		buttonStage.addActor(button);
		return button;
	}

	private TextButton setupPlayPauseButton() {
		final TextButton button;
		button = new TextButton("Play", buttonSkin);
		button.setPosition(buttonStage.getWidth() - button.getWidth() - 10, 10);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				boolean playing = gameScreenActions.onPlayPauseToggle();
				button.setText(playing ? "Pause" : "Play");
			}
		});
		buttonStage.addActor(button);
		return button;
	}

	private TextButton setupTowerButton() {
		final TextButton button;
		button = new TextButton("Tower", buttonSkin);
		button.setPosition(buttonStage.getWidth() - button.getWidth() - 10, buttonStage.getHeight() - 10 - button.getHeight());
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				log.debug("STARTING PLACING TOWER MODE");
				towerPlacementController.startPlacingTower();
				placeTowerButton.setTouchable(Touchable.disabled);
				cancelPlaceTowerButton.setVisible(true);
				playPauseButton.setVisible(false);
				exitButton.setVisible(false);
			}
		});
		buttonStage.addActor(button);
		return button;
	}

	private TextButton setupCancelPlaceTowerButton() {
		final TextButton button;
		button = new TextButton("Cancel", buttonSkin);
		button.setPosition(buttonStage.getWidth() - button.getWidth() - 10, 10);
		button.setVisible(false);
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				towerPlacementController.cancelPlacingTower();
				endTowerPlacementMode();
			}
		});
		buttonStage.addActor(button);
		return button;
	}

	private void endTowerPlacementMode() {
		log.debug("Ending tower placement mode.");
		placeTowerButton.setTouchable(Touchable.enabled);
		cancelPlaceTowerButton.setVisible(false);
		exitButton.setVisible(true);
		playPauseButton.setVisible(true);
	}

	public boolean isPlacingTower() {
		return towerPlacementController.isPlacingTower();
	}

	public void handlePlaceTowerClick(float x, float y) {
		boolean placedTower = towerPlacementController.handleTryPlaceTower(x, y);
		if (placedTower) {
			endTowerPlacementMode();
		}
	}

	public void updatePlacingTowerPosition(Vector3 cursorPosition) {
		towerPlacementController.updatePlacingTowerPosition(cursorPosition);
	}

	public void dispose() {
		towerPlacementController.disposeOfPlacingTower();
	}

}
