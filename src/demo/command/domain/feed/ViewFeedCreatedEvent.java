package demo.command.domain.feed;

import demo.command.base.AbstractEvent;

public class ViewFeedCreatedEvent extends AbstractEvent {

	private ViewFeed viewFeed;

	public ViewFeed getViewFeed() {
		return viewFeed;
	}

	public void setViewFeed(ViewFeed viewFeed) {
		this.viewFeed = viewFeed;
	}

}
