package nl.samanthatobias.voltling.utils;

import com.badlogic.gdx.utils.Array;

public class ArrayUtils {

	public static <T> Iterable<T> iterator(Array<T> array) {
		return new Array.ArrayIterator<>(array);
	}

}
