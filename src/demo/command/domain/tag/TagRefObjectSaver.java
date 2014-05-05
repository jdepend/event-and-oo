package demo.command.domain.tag;

import demo.command.base.AsynHandler;
import demo.infrastructure.JdbcTemplate;

public class TagRefObjectSaver extends AsynHandler<TagRefObjectAddedEvent> {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	protected void doHandle(TagRefObjectAddedEvent tagRefObjectAddedEvent) {
		jdbcTemplate.save(tagRefObjectAddedEvent.getTagRefObject());
	}
}
