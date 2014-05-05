package demo.infrastructure;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class JdbcTemplate {

	private static Map<String, Object> database = new Hashtable<String, Object>();

	public void save(Identifyer obj) {
		String key = obj.getClass().getSimpleName() + "_" + obj.getId();
		Object data = database.get(key);
		if (data == null) {
			database.put(key, obj);
		} else {
			try {
				copyBeanToBean(obj, data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LogUtil.print("persistent " + obj.getClass().getSimpleName()
				+ " obj = " + obj);
	}

	public <T> T find(String id, Class<T> type) {
		return (T) database.get(type.getSimpleName() + "_" + id);
	}

	public <T> List<T> find(Class<T> type) {
		List<T> list = new ArrayList<T>();
		for (String key : database.keySet()) {
			if (key.startsWith(type.getSimpleName())) {
				list.add((T) database.get(key));
			}
		}
		return list;
	}

	private static void copyBeanToBean(Object source, Object target)
			throws Exception {

		Method[] method1 = source.getClass().getMethods();
		Method[] method2 = target.getClass().getMethods();
		String methodName1;
		String methodFix1;
		String methodName2;
		String methodFix2;

		for (int i = 0; i < method1.length; i++) {
			methodName1 = method1[i].getName();
			methodFix1 = methodName1.substring(3, methodName1.length());
			if (methodName1.startsWith("get")) {
				for (int j = 0; j < method2.length; j++) {
					methodName2 = method2[j].getName();
					methodFix2 = methodName2.substring(3, methodName2.length());
					if (methodName2.startsWith("set")) {
						if (methodFix2.equals(methodFix1)) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							objs2[0] = method1[i].invoke(source, objs1);
							if (objs2[0] != null) {// 当存在数据时copy，否则不copy
								method2[j].invoke(target, objs2);
							}
							continue;
						}
					}
				}
			}
		}
	}

}
