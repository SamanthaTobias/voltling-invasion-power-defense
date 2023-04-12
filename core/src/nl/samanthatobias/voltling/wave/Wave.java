package nl.samanthatobias.voltling.wave;

import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.utils.ArrayUtils;
import nl.samanthatobias.voltling.utils.logger.Logger;
import nl.samanthatobias.voltling.voltling.Voltling;

public class Wave {

	private final static Logger log = Logger.createLogger(Wave.class);

	private final Voltling blueprint;
	private final int amount;
	private final float period;

	private final Array<Voltling> voltlings;
	private float sinceLastPeriod = 0f;

	public Wave(Voltling voltling, int amount, float period) {
		this.blueprint = voltling;
		this.amount = amount;
		this.period = period;
		this.voltlings = new Array<>();
	}

	public Voltling tic(float delta) {
		sinceLastPeriod += delta;
		if (sinceLastPeriod >= period && voltlings.size < amount) {
			sinceLastPeriod -= period;
			Voltling voltling = blueprint.clone();
			voltlings.add(voltling);
			log.debug("Wave spawning Voltling. {}/{} spawned.", voltlings.size, amount);
			return voltling;
		}
		return null;
	}

	public boolean isOver() {
		if (voltlings.size < amount) {
			return false;
		}

		for (Voltling voltling : ArrayUtils.iterator(voltlings)) {
			if (voltling.isAlive()) {
				return false;
			}
		}

		log.debug("Wave is over.");
		return true;
	}

}
