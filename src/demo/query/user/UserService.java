package demo.query.user;

import demo.command.domain.user.UserRepoVO;
import demo.infrastructure.JdbcTemplate;

public class UserService {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public UserRepoVO find(String id) {
		return jdbcTemplate.find(id, UserRepoVO.class);
	}
}
