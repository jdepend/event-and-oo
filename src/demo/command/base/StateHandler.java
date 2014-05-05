package demo.command.base;

import java.util.Date;

import demo.infrastructure.PersistentObject;

public abstract class StateHandler<T extends Event> extends
		AbstractHandler<T> implements PersistentObject {

	private Date lastSaveDate;

	public Date getLastSaveDate() {
		return lastSaveDate;
	}

	public void setLastSaveDate(Date lastSaveDate) {
		this.lastSaveDate = lastSaveDate;
	}

	public void replay() {
		for (Event event : EventBus.getStayEvents(getLastSaveDate(), this)) {
			if (EventBus.getEventClasses(this).contains(event.getClass())) {
				this.handle((T) event);
			}
		}
	}

}
