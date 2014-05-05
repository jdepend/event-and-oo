package demo.query.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.command.domain.user.UserViewFeed;
import demo.infrastructure.JdbcTemplate;

public class FeedRepository {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public List<FeedVO> find(String userId, Date start, int limit) {
		List<FeedVO> feeds = new ArrayList<FeedVO>();
		FeedVO feedVO;
		int count = 0;
		List<UserViewFeed> viewFeeds = jdbcTemplate.find(UserViewFeed.class);
		for (UserViewFeed viewFeed : viewFeeds) {
			if (count < limit) {
				if (viewFeed.getTargetId().equals(userId)
						&& (start == null || viewFeed.getFeed().getCreateTime()
								.compareTo(start) > 0)) {
					feedVO = new FeedVO();
					feedVO.setId(viewFeed.getId());
					feedVO.setTitleParameter(viewFeed.getFeed().getMessage());
					feedVO.setBodyParameter(viewFeed.getFeed().getBody());
					feedVO.setCreateTime(viewFeed.getFeed().getCreateTime());
					feedVO.setSourceUserId(viewFeed.getFeed().getUserId());
					feedVO.setSourceUserName(viewFeed.getFeed().getUserName());
					feedVO.setResourceId(viewFeed.getFeed().getResourceId());
					feedVO
							.setResourceType(viewFeed.getFeed()
									.getResourceType());

					feeds.add(feedVO);
					count++;
				}
			} else {
				break;
			}
		}

		return feeds;
	}
}
