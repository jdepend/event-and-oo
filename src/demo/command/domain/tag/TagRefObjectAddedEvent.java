package demo.command.domain.tag;

import demo.command.base.AbstractEvent;

public class TagRefObjectAddedEvent extends AbstractEvent {

	private TagRefObjectVO tagRefObject;

	public TagRefObjectVO getTagRefObject() {
		return tagRefObject;
	}

	public void setTagRefObject(TagRefObjectVO tagRefObject) {
		this.tagRefObject = tagRefObject;
	}

}
