package demo.command;

import demo.command.base.StateHandler;
import demo.command.base.EventBus;
import demo.command.base.StateHandlerFactory;
import demo.command.domain.blog.BlogPublishedEvent;
import demo.command.domain.blog.BlogSaver;
import demo.command.domain.feed.FeedCreatedEvent;
import demo.command.domain.feed.FeedSaver;
import demo.command.domain.feed.ViewFeedCreatedEvent;
import demo.command.domain.feed.ViewFeedSaver;
import demo.command.domain.tag.TagRefObjectAddedEvent;
import demo.command.domain.tag.TagRefObjectSaver;
import demo.command.domain.tag.TagSaver;
import demo.command.domain.tag.TagStateUpdatedEvent;

public class BeanFactory {

	public static void init() {
		EventBus.registHandler(BlogPublishedEvent.class, new BlogSaver());
		EventBus.registHandler(FeedCreatedEvent.class, new FeedSaver());
		EventBus.registHandler(ViewFeedCreatedEvent.class, new ViewFeedSaver());
		EventBus.registHandler(TagStateUpdatedEvent.class, new TagSaver());
		EventBus.registHandler(TagRefObjectAddedEvent.class,
				new TagRefObjectSaver());
	}

	public static <T extends StateHandler> T getBean(Class<T> c,
			String id) {
		try {
			return StateHandlerFactory.getInstance().getStateHandler(c, id);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static <T extends StateHandler> T createBean(Class<T> c,
			Object[] args) throws Exception {
		return StateHandlerFactory.getInstance().createStateHandler(c, args);
	}

	public static void destory() throws Exception {
		StateHandlerFactory.getInstance().destory();
		EventBus.destory();
	}

}
