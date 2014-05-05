package demo.query.feed;

import java.util.Date;
import java.util.List;

public class FeedService {

	private FeedRepository repo = new FeedRepository();

	public List<FeedVO> list(String userId, Date start, int limit) {
		return repo.find(userId, start, limit);

	}

}
