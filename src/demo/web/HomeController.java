package demo.web;

import java.util.Date;
import java.util.List;

import demo.command.BeanFactory;
import demo.command.domain.blog.Blog;
import demo.command.domain.topic.Topic;
import demo.query.blog.BlogService;
import demo.query.feed.FeedService;
import demo.query.feed.FeedVO;

public class HomeController {

	private FeedService feedService = new FeedService();
	
	private BlogService blogService = new BlogService();

	public void list(Date start, int limit) {
		String userId = Session.getUser().getId();
		List<FeedVO> feeds = feedService.list(userId, start, limit);

		Model.put("feedList", feeds);
	}

	public void createBlog(String blogId, String topicId, String content)
			throws Exception {
		String userId = Session.getUser().getId();

		Object[] args = new Object[4];

		args[0] = blogId;
		args[1] = userId;
		args[2] = BeanFactory.getBean(Topic.class, topicId);
		args[3] = content;

		BeanFactory.createBean(Blog.class, args);
	}

	public void likeBlog(String blogId) {
		Blog blog = BeanFactory.getBean(Blog.class, blogId);

		if (blog != null) {
			String userId = Session.getUser().getId();
			blog.liked(userId);
		}
	}

}
