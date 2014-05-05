package demo.command.domain.tag;

import java.util.HashMap;
import java.util.Map;

import demo.command.base.AsynStateHandler;
import demo.command.base.Event;
import demo.command.base.EventBus;
import demo.command.base.EventFactory;
import demo.command.domain.blog.BlogStateUpdatedEvent;
import demo.command.domain.user.UserProfileUpdatedEvent;
import demo.command.domain.user.UserTagAddedEvent;
import demo.infrastructure.DateUtil;
import demo.infrastructure.JdbcTemplate;
import demo.infrastructure.LogUtil;

public class Tag extends AsynStateHandler<Event> {

	private String content;

	private int heat;

	private transient Map<String, String> refObjects = new HashMap<String, String>();

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public Tag(String content) {
		this.content = content;

		EventBus.registHandler(BlogStateUpdatedEvent.class, this);
		EventBus.registHandler(UserProfileUpdatedEvent.class, this);
	}

	public String getId() {
		return content;
	}

	public void addRefObject(String id, String type) {
		this.refObjects.put(id, type);

		TagRefObjectAddedEvent tagRefObjectAddedEvent = EventFactory
				.createEvent(TagRefObjectAddedEvent.class);

		TagRefObjectVO tagRefObjectVO = new TagRefObjectVO();
		tagRefObjectVO.setTag(content);
		tagRefObjectVO.setObjId(id);
		tagRefObjectVO.setObjType(type);

		tagRefObjectAddedEvent.setTagRefObject(tagRefObjectVO);

		EventBus.send(tagRefObjectAddedEvent);
	}

	public Map<String, String> getRefObjects() {
		return refObjects;
	}

	public TagVO getTagVO() {

		TagVO tagVO = new TagVO();

		tagVO.setContent(content);
		tagVO.setHeat(heat);

		return tagVO;
	}

	public TagRepoVO getTagRepoVO() {

		TagRepoVO tagVO = new TagRepoVO();

		tagVO.setContent(content);
		tagVO.setHeat(heat);
		tagVO.setLastSaveDate(DateUtil.getSysDate());

		return tagVO;
	}

	@Override
	protected void doHandle(Event event) {
		heat++;
		LogUtil.print("Tag " + this.content + " heat++ heat=" + heat);

		TagStateUpdatedEvent tagStateUpdatedEvent = EventFactory.createEvent(
				TagStateUpdatedEvent.class, event, this);
		tagStateUpdatedEvent.setTag(this.getTagVO());

		EventBus.send(tagStateUpdatedEvent);
	}

	@Override
	protected boolean isInterestedEvent(Event event) {
		if (event instanceof BlogStateUpdatedEvent) {
			BlogStateUpdatedEvent blogStateUpdatedEvent = (BlogStateUpdatedEvent) event;
			if (blogStateUpdatedEvent.getTags().contains(this.content)) {
				return true;

			}

		} else if (event instanceof UserProfileUpdatedEvent) {
			UserProfileUpdatedEvent userProfileUpdatedEvent = (UserProfileUpdatedEvent) event;
			if (userProfileUpdatedEvent.getSource() instanceof UserTagAddedEvent
					&& userProfileUpdatedEvent.getTags().contains(this.content)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void load() {
		TagRepoVO tagVO = jdbcTemplate.find(content, TagRepoVO.class);
		this.heat = tagVO.getHeat();
		this.setLastSaveDate(tagVO.getLastSaveDate());
	}

	@Override
	public void save() throws Exception {
		jdbcTemplate.save(this.getTagRepoVO());
	}

	@Override
	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append("\nTag : ");
		info.append(content);
		info.append("\n");

		info.append("heat : ");
		info.append(heat);
		info.append("\n");

		info.append("refObjects : ");
		for (String refObject : refObjects.keySet()) {
			info.append("\n");
			info.append(refObject);
			info.append(",");
			info.append(refObjects.get(refObject));
		}
		info.append("\n");

		return info.toString();
	}

}
