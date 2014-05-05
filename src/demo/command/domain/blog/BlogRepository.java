package demo.command.domain.blog;

import demo.infrastructure.JdbcTemplate;

public class BlogRepository {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public void save(BlogRepoVO blog) {
		jdbcTemplate.save(blog);
	}

	public BlogRepoVO find(String id) {
		return jdbcTemplate.find(id, BlogRepoVO.class);
	}

}
