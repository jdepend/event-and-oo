package demo.command.domain.feed;

import demo.command.base.AsynHandler;
import demo.command.base.EventBus;
import demo.command.base.EventFactory;
import demo.command.domain.user.UserProfileUpdatedEvent;
import demo.infrastructure.JdbcTemplate;

public class FeedSaver extends AsynHandler<FeedCreatedEvent> {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	protected void doHandle(FeedCreatedEvent feedCreatedEvent) {

		jdbcTemplate.save(new FeedRepoVO(feedCreatedEvent.getFeed()));

		UserProfileUpdatedEvent updateEvent = EventFactory.createEvent(
				UserProfileUpdatedEvent.class, feedCreatedEvent);
		updateEvent.setUserId(feedCreatedEvent.getFeed().getUserId());
		updateEvent.setFeed(feedCreatedEvent.getFeed());
		updateEvent.setTags(feedCreatedEvent.getFeed().getUser().getTags());

		EventBus.send(updateEvent);
	}
}
