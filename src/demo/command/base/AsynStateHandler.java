package demo.command.base;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AsynStateHandler<T extends Event> extends
		StateHandler<T> {

	protected Queue<T> eventQueue = new ConcurrentLinkedQueue<T>();

	private Timer handleEventTimer;

	public AsynStateHandler() {

		this.handleEventTimer = new Timer();
		this.handleEventTimer.schedule(new HandleEventHandler(), 1000, 2000);
	}

	@Override
	public void handle(T event) {
		if (this.isInterestedEvent(event)) {
			this.eventQueue.add(event);
		}
	}

	protected abstract void doHandle(T event);

	protected abstract boolean isInterestedEvent(T event);

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
