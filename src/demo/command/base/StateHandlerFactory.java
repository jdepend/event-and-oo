package demo.command.base;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import demo.infrastructure.DateUtil;

/**
 * 负责创建、查找有状态事件处理器的工厂
 * 
 * 有状态事件处理器在实例化后的一段时间内很有可能参与到事件的处理中（既是事件的发起者 也是事件的消费者），故实例化后应保持处理器在内存中一段时间。
 * 
 * 有状态事件处理器在实例化后一方面需要装载自己必要的数据（为了处理事件所需要的基本信
 * 息），另一方面为了完成自己作为事件处理网络中节点的职责，需要进行事件回放
 * 
 * @author user
 * 
 */
public class StateHandlerFactory {

	private static StateHandlerFactory factory = new StateHandlerFactory();

	private Map<StateHandler, Date> handlers = new Hashtable<StateHandler, Date>();

	private Timer clearTimer;

	private final long keepDate = 10000;

	private StateHandlerFactory() {
		this.clearTimer = new Timer();
		this.clearTimer.schedule(new ClearStateHandler(), 1000, 2000);
	}

	public static StateHandlerFactory getInstance() {
		return factory;
	}

	public <T extends StateHandler> T getStateHandler(Class<T> c,
			String id) throws Exception {
		T handler = findStateHandler(c, new Object[] { id });
		if (handler != null) {
			return handler;
		} else {
			Constructor<T> constructor = c
					.getConstructor(new Class[] { String.class });

			handler = constructor.newInstance(id);

			handler.load();
			handler.replay();

			handlers.put(handler, DateUtil.getSysDate());

			return handler;
		}
	}

	public <T extends StateHandler> T createStateHandler(Class<T> c,
			Object[] args) throws Exception {

		T handler = findStateHandler(c, args);
		if (handler != null) {
			return handler;
		} else {
			if (args == null) {
				handler = c.newInstance();
			} else {
				Class[] argTypes = new Class[args.length];
				for (int i = 0; i < args.length; i++) {
					argTypes[i] = args[i].getClass();
				}
				Constructor<T> constructor = c.getConstructor(argTypes);

				handler = constructor.newInstance(args);
			}
			handlers.put(handler, DateUtil.getSysDate());

			return handler;
		}
	}

	private <T extends StateHandler> T findStateHandler(Class<T> c,
			Object[] args) throws Exception {
		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("args is null");
		}

		for (StateHandler handler : handlers.keySet()) {
			if (handler.getClass().equals(c) && handler.getId().equals(args[0])) {
				handlers.put(handler, DateUtil.getSysDate());
				return (T) handler;
			}
		}

		return null;
	}

	public void destory() throws InterruptedException {
		this.clearTimer.cancel();
	}

	class ClearStateHandler extends TimerTask {
		@Override
		public void run() {
			Date clearDate = new Date(DateUtil.getSysDate().getTime()
					- keepDate);
			Iterator<StateHandler> it = handlers.keySet().iterator();
			StateHandler handler;
			while (it.hasNext()) {
				handler = it.next();
				if (handlers.get(handler).compareTo(clearDate) < 0) {
					try {
						handler.destory();
						handler.save();
						it.remove();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
