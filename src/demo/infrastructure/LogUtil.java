package demo.infrastructure;

public class LogUtil {

	public static void print(String info) {
		System.out.println(Thread.currentThread().getName() + " : " + info);
	}

}
