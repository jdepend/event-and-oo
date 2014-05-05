package demo.command.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import demo.infrastructure.DateUtil;
import demo.infrastructure.Identifyer;
import demo.infrastructure.LogUtil;

public class EventRepository {

	private List<Event> eventList = new Vector<Event>();

	private Timer clearOldEventTimer;

	private final long keepDate = 7 * 24 * 3600 * 1000;

	private final int maxEventCount = 5000;

	private MongoDBTemplate mongoDBTemplate = new MongoDBTemplate();

	public static final String Event = "Event";

	public static final String EventTeam = "Team";

	public EventRepository() {
		this.clearOldEventTimer = new Timer();
		this.clearOldEventTimer.schedule(new ClearEventHandler(), 1000, 2000);
	}

	public List<Event> getEvents(Date start, List<Class<? extends Event>> types) {

		if (this.eventList.size() > 0) {
			Date end = this.eventList.get(0).getCreateTime();
			if (start.compareTo(end) < 0) {
				this.load(start, end, types);
			}
		}

		List<Event> rtnEvents = new ArrayList<Event>();
		for (Event event : eventList) {
			if (types.contains(event.getClass())
					&& event.getCreateTime().compareTo(start) > 0) {
				rtnEvents.add(event);
			}
		}
		return rtnEvents;
	}

	public void save(Event event) {
		eventList.add(event);
		event.save();
		LogUtil.print(event + " saved.");
	}

	private void load(Date start, Date end, List<Class<? extends Event>> types) {

		Map<String, EventTeam> eventTeams = this.loadEventTeams(start, end);

		Map<String, Event> events = this.loadEvents(start, end, types);

		for (Event event : events.values()) {
			event.load(eventTeams, events);
			this.eventList.add(event);
		}
	}

	private Map<String, EventTeam> loadEventTeams(Date start, Date end) {
		Map<String, EventTeam> eventTeams = new HashMap<String, EventTeam>();

		for (Identifyer identifyer : mongoDBTemplate.loadEventTeams(start, end)) {
			eventTeams.put(identifyer.getId(), (EventTeam) identifyer);

		}
		return eventTeams;
	}

	private Map<String, Event> loadEvents(Date start, Date end,
			List<Class<? extends Event>> types) {
		Map<String, Event> events = new LinkedHashMap<String, Event>();

		for (Identifyer identifyer : mongoDBTemplate.loadEvents(start, end,
				types)) {
			events.put(identifyer.getId(), (Event) identifyer);

		}
		return events;
	}

	public void destory() {
		this.clearOldEventTimer.cancel();
	}

	class ClearEventHandler extends TimerTask {
		@Override
		public void run() {
			clearWithCount();
			clearWithDate();
		}

		private void clearWithCount() {
			if (eventList.size() > maxEventCount) {
				Iterator<Event> it = eventList.iterator();
				int i = 0;
				int end = eventList.size() - maxEventCount;
				while (i < end) {
					it.next();
					it.remove();
					i++;
				}
			}
		}

		private void clearWithDate() {
			Date clearDate = new Date(DateUtil.getSysDate().getTime()
					- keepDate);
			Iterator<Event> it = eventList.iterator();
			Event event;
			boolean stop = false;
			while (it.hasNext() && !stop) {
				event = it.next();
				if (event.getCreateTime().compareTo(clearDate) < 0) {
					it.remove();
				} else {
					stop = true;
				}
			}
		}
	}

}
