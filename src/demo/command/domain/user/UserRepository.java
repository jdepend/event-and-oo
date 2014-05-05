package demo.command.domain.user;

import demo.infrastructure.JdbcTemplate;

public class UserRepository {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public void save(UserRepoVO user) {
		jdbcTemplate.save(user);
	}

	public UserRepoVO find(String id) {
		return jdbcTemplate.find(id, UserRepoVO.class);
	}

}
