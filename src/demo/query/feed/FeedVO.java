package demo.query.feed;

import java.util.Date;

public class FeedVO {

	private String id;

	private String resourceId;

	private String resourceType;

	private String sourceUserId;

	private String sourceUserName;

	private Date createTime;

	private String feedType;

	private String titleParameter;

	private String bodyParameter;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getSourceUserName() {
		return sourceUserName;
	}

	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getTitleParameter() {
		return titleParameter;
	}

	public void setTitleParameter(String titleParameter) {
		this.titleParameter = titleParameter;
	}

	public String getBodyParameter() {
		return bodyParameter;
	}

	public void setBodyParameter(String bodyParameter) {
		this.bodyParameter = bodyParameter;
	}

	@Override
	public String toString() {
		return "id : " + this.getId() + "¡¡resourceId:" + this.resourceId
				+ " titleParameter : " + this.titleParameter + " createTime : "
				+ this.createTime;
	}

}
