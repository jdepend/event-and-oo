package demo.command.base;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class ComplexHandler<T extends Event> extends
		AsynStateHandler<T> {

	protected Queue<T> needFilterQueue = new ConcurrentLinkedQueue<T>();

	private Timer filterTimer;

	public ComplexHandler() {
		this.filterTimer = new Timer();
		this.filterTimer.schedule(new FilterHandler(), 4000, 4000);
	}

	@Override
	public void handle(T event) {
		if (this.isInterestedEvent(event)) {
			if (event.isNeedFilter()) {
				this.needFilterQueue.add(event);
			} else {
				this.eventQueue.add(event);
			}
		}
	}

	protected void filter() {
		while (!this.needFilterQueue.isEmpty()) {
			Map<String, T> result = new LinkedHashMap<String, T>();
			T event1;
			for (T event : this.needFilterQueue) {
				event1 = result.get(event.getEventTeam().getId());
				if (event1 == null || event.compareTo(event1) > 0) {
					result.put(event.getEventTeam().getId(), event);
				}
			}
			for (T event : result.values()) {
				this.eventQueue.add(event);
			}
			this.needFilterQueue.clear();
		}

	}

	protected abstract boolean isInterestedEvent(T event);

	@Override
	public void doDestory() throws Exception {
		while (!needFilterQueue.isEmpty()) {
			Thread.sleep(1000);
		}
		this.filterTimer.cancel();

		super.doDestory();
	}

	class FilterHandler extends TimerTask {
		@Override
		public void run() {
			filter();
		}
	}
}
