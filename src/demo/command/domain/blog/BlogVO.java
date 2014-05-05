package demo.command.domain.blog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import demo.infrastructure.Identifyer;

public class BlogVO implements Identifyer, Serializable {

	private String id;

	private String creatorId;

	private String topicId;

	private int likeCount;

	private List<String> tags = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "id : " + this.id + "¡¡creatorId:" + this.creatorId
				+ " topicId : " + this.topicId + " likeCount : "
				+ this.likeCount + " tags : " + this.tags;
	}

}
