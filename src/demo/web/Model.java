package demo.web;

import java.util.LinkedHashMap;
import java.util.Map;

public class Model {

	private static Map<String, Object> data = new LinkedHashMap<String, Object>();

	public static Object get(String name) {
		return data.get(name);
	}

	public static void put(String name, Object value) {
		data.put(name, value);
	}

	public static Map<String, Object> getData() {
		return data;
	}

}
