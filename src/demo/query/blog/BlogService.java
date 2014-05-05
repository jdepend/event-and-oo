package demo.query.blog;

import demo.command.domain.blog.BlogRepoVO;
import demo.infrastructure.JdbcTemplate;

public class BlogService {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public BlogRepoVO find(String id) {
		return jdbcTemplate.find(id, BlogRepoVO.class);
	}

}
