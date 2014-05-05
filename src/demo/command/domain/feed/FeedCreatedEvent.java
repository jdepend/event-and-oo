package demo.command.domain.feed;

import demo.command.base.AbstractEvent;

public class FeedCreatedEvent extends AbstractEvent {

	private Feed feed;

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

}
