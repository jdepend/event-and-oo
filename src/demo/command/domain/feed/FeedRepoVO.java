package demo.command.domain.feed;

import demo.command.domain.blog.BlogRepoVO;
import demo.command.domain.user.UserRepoVO;
import demo.infrastructure.JdbcTemplate;

public class FeedRepoVO extends Feed {

	private String userName;

	private String body;

	private transient JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public FeedRepoVO() {

	}

	public FeedRepoVO(Feed feed) {
		this.setId(feed.getId());
		this.setMessage(feed.getMessage());
		this.setCreateTime(feed.getCreateTime());
		this.setEventId(feed.getEventId());
		this.setResourceId(feed.getResourceId());
		this.setResourceType(feed.getResourceType());
		this.setType(feed.getType());
		this.setUser(feed.getUser());
	}

	public String getUserName() {
		if (this.userName == null) {
			this.userName = jdbcTemplate.find(this.getUserId(),
					UserRepoVO.class).getName();
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBody() {
		if (this.body == null) {
			if (this.getResourceType().equals(ResourceType_Blog)) {
				BlogRepoVO blog = jdbcTemplate.find(this.getResourceId(),
						BlogRepoVO.class);
				if (blog != null) {
					this.body = blog.getContent();
				}
			}
		}
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
