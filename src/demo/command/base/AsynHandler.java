package demo.command.base;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AsynHandler<T extends Event> extends AbstractHandler<T> {

	private Queue<T> eventQueue = new ConcurrentLinkedQueue<T>();

	private Timer handleEventTimer;

	public AsynHandler() {

		this.handleEventTimer = new Timer();
		this.handleEventTimer.schedule(new HandleEventHandler(), 500, 1000);
	}

	@Override
	public void handle(T event) {
		this.eventQueue.add(event);
	}

	protected abstract void doHandle(T event);

	@Override
	public void doDestory() throws Exception {
		while (!eventQueue.isEmpty()) {
			Thread.sleep(1000);
		}
		this.handleEventTimer.cancel();
	}

	class HandleEventHandler extends TimerTask {
		@Override
		public void run() {
			while (!eventQueue.isEmpty()) {
				doHandle(eventQueue.element());
				eventQueue.remove();
			}
		}
	}
}
