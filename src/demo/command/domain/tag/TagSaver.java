package demo.command.domain.tag;

import demo.command.base.AsynHandler;
import demo.infrastructure.JdbcTemplate;

public class TagSaver extends AsynHandler<TagStateUpdatedEvent> {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	protected void doHandle(TagStateUpdatedEvent tagStateUpdatedEvent) {
		jdbcTemplate.save(tagStateUpdatedEvent.getTag());
	}
}
