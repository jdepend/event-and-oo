package demo.command.base;

import java.util.HashMap;
import java.util.Map;

public class EventType {

	private static Map<String, Boolean> isPersistents = new HashMap<String, Boolean>();

	public static Boolean isPersistent(String type) {
		return isPersistents.get(type);
	}

	public static void setPersistent(String type, boolean isPersistent) {
		isPersistents.put(type, isPersistent);
	}

}
