package demo.command.domain.blog;

import java.util.Date;

public class BlogRepoVO extends BlogVO {

	private String content;

	private Date lastSaveDate;

	public BlogRepoVO() {

	}

	public BlogRepoVO(BlogVO blogVO, String content) {
		this.setId(blogVO.getId());
		this.setContent(content);
		this.setCreatorId(blogVO.getCreatorId());
		this.setLikeCount(blogVO.getLikeCount());
		this.setTopicId(blogVO.getTopicId());
		this.setTags(blogVO.getTags());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLastSaveDate() {
		return lastSaveDate;
	}

	public void setLastSaveDate(Date lastSaveDate) {
		this.lastSaveDate = lastSaveDate;
	}

	@Override
	public String toString() {
		return super.toString() + " content : " + this.content;
	}

}
