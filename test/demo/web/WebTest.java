package demo.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import demo.command.BeanFactory;
import demo.command.domain.tag.Tag;
import demo.command.domain.tag.TagRepoVO;
import demo.command.domain.topic.Topic;
import demo.command.domain.topic.TopicRepoVO;
import demo.command.domain.user.User;
import demo.command.domain.user.UserRepoVO;
import demo.infrastructure.DateUtil;
import demo.infrastructure.JdbcTemplate;
import demo.infrastructure.LogUtil;

public class WebTest {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public WebTest() throws Exception {
		BeanFactory.init();
		this.initData();
		this.loadData();
	}

	private void initData() throws Exception {

		LogUtil.print("start init data...");

		List<String> followers;
		List<String> topics;

		UserRepoVO user1 = new UserRepoVO();
		user1.setId("user1");
		user1.setName("u1");
		user1.setEmail("user1@neusoft.com");
		user1.setLastSaveDate(DateUtil.getSysDate());

		followers = new ArrayList<String>();
		followers.add("user2");
		user1.setFollowers(followers);

		topics = new ArrayList<String>();
		topics.add("topic1");
		user1.setTopics(topics);

		jdbcTemplate.save(user1);

		UserRepoVO user2 = new UserRepoVO();
		user2.setId("user2");
		user2.setName("u2");
		user2.setEmail("user2@neusoft.com");
		user2.setLastSaveDate(DateUtil.getSysDate());

		jdbcTemplate.save(user2);

		UserRepoVO user3 = new UserRepoVO();
		user3.setId("user3");
		user3.setName("u3");
		user3.setEmail("user3@neusoft.com");
		user3.setLastSaveDate(DateUtil.getSysDate());

		followers = new ArrayList<String>();
		followers.add("user2");
		user3.setFollowers(followers);
		jdbcTemplate.save(user3);

		UserRepoVO user4 = new UserRepoVO();
		user4.setId("user4");
		user4.setName("u4");
		user4.setEmail("user4@neusoft.com");
		user4.setLastSaveDate(DateUtil.getSysDate());

		topics = new ArrayList<String>();
		topics.add("topic1");
		user4.setTopics(topics);

		jdbcTemplate.save(user4);

		UserRepoVO user5 = new UserRepoVO();
		user5.setId("user5");
		user5.setName("u5");
		user5.setEmail("user5@neusoft.com");
		user5.setLastSaveDate(DateUtil.getSysDate());

		followers = new ArrayList<String>();
		followers.add("user1");
		user5.setFollowers(followers);
		jdbcTemplate.save(user5);

		UserRepoVO user6 = new UserRepoVO();
		user6.setId("user6");
		user6.setName("u6");
		user6.setEmail("user6@neusoft.com");
		user6.setLastSaveDate(DateUtil.getSysDate());

		jdbcTemplate.save(user6);

		TopicRepoVO topic1 = new TopicRepoVO();
		topic1.setId("topic1");
		topic1.setLastSaveDate(DateUtil.getSysDate());

		jdbcTemplate.save(topic1);

		TagRepoVO tag1 = new TagRepoVO();
		tag1.setContent("tag1");
		tag1.setLastSaveDate(DateUtil.getSysDate());

		jdbcTemplate.save(tag1);

		TagRepoVO tag2 = new TagRepoVO();
		tag2.setContent("tag2");
		tag2.setLastSaveDate(DateUtil.getSysDate());

		jdbcTemplate.save(tag2);

		LogUtil.print("end init data");
	}

	private void loadData() {

		LogUtil.print("start load memory data...");

		Topic topic1 = new Topic("topic1");
		topic1.load();
		topic1.replay();

		Tag tag1 = new Tag("tag1");
		tag1.load();
		tag1.replay();

		Tag tag2 = new Tag("tag2");
		tag2.load();
		tag2.replay();

		LogUtil.print("end load memory data");

	}

	public void login(String userId) {

		LogUtil.print("start login ... " + userId);

		User user = new User(userId);
		user.load();
		user.replay();

		Session.setUser(user);

		LogUtil.print("end login " + userId);
	}

	public void logout(String userId) throws Exception {
		LogUtil.print("start logout ... " + userId);

		Session.getUser().destory();
		Session.getUser().save();
		Session.setUser(null);

		LogUtil.print("end logout " + userId);
	}

	public void testCreateBlog() throws Exception {
		HomeController homeController = new HomeController();
		homeController.createBlog("blog1", "topic1", "this is blog1 content");
	}

	public void testLikeBlog() throws Exception {
		HomeController homeController = new HomeController();
		homeController.likeBlog("blog1");
	}

	public void testList() throws Exception {

		LogUtil.print("list current user view feed...");

		HomeController homeController = new HomeController();
		homeController.list(null, 10);

		for (String name : Model.getData().keySet()) {
			LogUtil.print(name);
			if (Model.get(name) instanceof Collection) {
				for (Object element : ((Collection) Model.get(name))) {
					LogUtil.print(element.toString());
				}
			} else {
				LogUtil.print(Model.get(name).toString());
			}
		}
	}

	public void destory() throws Exception {
		BeanFactory.destory();
	}

	public static void main(String[] args) throws Exception {

		WebTest test = new WebTest();

		test.login("user1");

		test.testCreateBlog();

		// 等待数据持久化
		try {
			Thread.currentThread().sleep(4000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		test.logout("user1");
		test.login("user2");

		test.testLikeBlog();

		// 等待数据持久化
		try {
			Thread.currentThread().sleep(4000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		test.logout("user2");
		test.login("user4");

		// 等待User装载数据
		try {
			Thread.currentThread().sleep(8000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		test.testList();

		test.destory();

	}

}
