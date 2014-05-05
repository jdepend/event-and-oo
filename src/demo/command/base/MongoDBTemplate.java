package demo.command.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import demo.infrastructure.CreatedIdentifyer;

public class MongoDBTemplate {

	private static Map<String, CreatedIdentifyer> database = new Hashtable<String, CreatedIdentifyer>();

	public void save(String type, CreatedIdentifyer identifyer) {
		database.put(type + "_" + identifyer.getId(), identifyer);
	}

	public List<Event> loadEvents(Date startDate, Date endDate,
			List<Class<? extends Event>> types) {

		List<Event> list = new ArrayList<Event>();
		CreatedIdentifyer element;
		for (String key : database.keySet()) {
			if (key.startsWith(EventRepository.Event)) {
				element = database.get(key);
				if (element.getCreateTime().compareTo(startDate) > 0
						&& element.getCreateTime().compareTo(endDate) < 0
						&& types.contains(element)) {
					list.add((Event) database.get(key));
				}
			}
		}
		return list;
	}

	public List<EventTeam> loadEventTeams(Date startDate, Date endDate) {

		List<EventTeam> list = new ArrayList<EventTeam>();
		CreatedIdentifyer element;
		for (String key : database.keySet()) {
			if (key.startsWith(EventRepository.EventTeam)) {
				element = database.get(key);
				if (element.getCreateTime().compareTo(startDate) > 0
						&& element.getCreateTime().compareTo(endDate) < 0) {
					list.add((EventTeam) database.get(key));
				}
			}
		}
		return list;

	}
}
