package nl.samanthatobias.voltling.wave;

import com.badlogic.gdx.utils.Array;

import nl.samanthatobias.voltling.voltling.Voltling;
import nl.samanthatobias.voltling.voltling.VoltlingFactory;

public class WavesFactory {

	public static Array<Wave> createWaves(String level) {
		if (!level.equals("ONE")) {
			throw new UnsupportedOperationException(); // TODO implement level based waves system
		}

		Voltling voltling = VoltlingFactory.createLesserVoltling();

		Array<Wave> waves = new Array<>();
		for (int i = 0; i < 10; i++) {
			Wave wave = new Wave(voltling, 5*i + 15, 1f - (((float)i)/20));
			waves.add(wave);
		}

		return waves;
	}

}
