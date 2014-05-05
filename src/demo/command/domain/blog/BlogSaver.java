package demo.command.domain.blog;

import demo.command.base.AsynHandler;
import demo.command.base.EventBus;
import demo.command.base.EventFactory;

public class BlogSaver extends AsynHandler<BlogPublishedEvent> {

	private BlogRepository repo = new BlogRepository();

	@Override
	protected void doHandle(BlogPublishedEvent event) {
		repo.save(new BlogRepoVO(event.getBlog(), event.getContent()));

		BlogPublishFinishedEvent blogPublishFinishedEvent = EventFactory
				.createEvent(BlogPublishFinishedEvent.class, event);

		blogPublishFinishedEvent.setBlog(event.getBlog());
		blogPublishFinishedEvent.setOperId(event.getOperId());

		EventBus.send(blogPublishFinishedEvent);

	}
}
