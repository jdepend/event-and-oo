package demo.command.domain.topic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.infrastructure.Identifyer;

public class TopicRepoVO implements Identifyer {

	private String id;

	private int likeCount;

	private List<String> blogIds = new ArrayList<String>();

	private Date lastSaveDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public List<String> getBlogIds() {
		return blogIds;
	}

	public void setBlogIds(List<String> blogIds) {
		this.blogIds = blogIds;
	}

	public Date getLastSaveDate() {
		return lastSaveDate;
	}

	public void setLastSaveDate(Date lastSaveDate) {
		this.lastSaveDate = lastSaveDate;
	}

	@Override
	public String toString() {
		return "topicId : " + this.getId() + "¡¡likeCount:" + this.likeCount
				+ " blogIds : " + this.blogIds;
	}

}
