package demo.command.domain.topic;

import demo.infrastructure.JdbcTemplate;

public class TopicRepository {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public void save(TopicRepoVO topic) {
		jdbcTemplate.save(topic);
	}

	public TopicRepoVO find(String id) {
		return new TopicRepoVO();
	}

}
