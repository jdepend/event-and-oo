package demo.command.domain.feed;

import demo.command.base.AsynHandler;
import demo.infrastructure.JdbcTemplate;

public class ViewFeedSaver extends AsynHandler<ViewFeedCreatedEvent> {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	protected void doHandle(ViewFeedCreatedEvent event) {
		jdbcTemplate.save(event.getViewFeed());
	}
}
