package demo.command.domain.tag;

import demo.command.base.AbstractEvent;
import demo.command.domain.blog.BlogStateUpdatedEvent;
import demo.command.domain.user.UserProfileUpdatedEvent;

public class TagStateUpdatedEvent extends AbstractEvent {

	private TagVO tag = new TagVO();

	public String getContent() {
		return tag.getContent();
	}

	public int getHeat() {
		return this.tag.getHeat();
	}

	public TagVO getTag() {
		return tag;
	}

	public void setTag(TagVO tag) {
		this.tag = tag;
	}

	public String getOperator() {
		if (this.getSource() instanceof UserProfileUpdatedEvent) {
			return ((UserProfileUpdatedEvent) this.getSource()).getUserId();
		}
		if (this.getSource() instanceof BlogStateUpdatedEvent) {
			return ((BlogStateUpdatedEvent) this.getSource()).getOperId();
		}
		return null;
	}

}
