package demo.command.domain.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class UserRepoVO extends UserVO {

	private String name;

	private String email;

	private Collection<String> followers = new ArrayList<String>();

	private Collection<String> topics = new ArrayList<String>();

	private Date lastSaveDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<String> getFollowers() {
		return followers;
	}

	public void setFollowers(Collection<String> followers) {
		this.followers = followers;
	}

	public Collection<String> getTopics() {
		return topics;
	}

	public void setTopics(Collection<String> topics) {
		this.topics = topics;
	}

	public Date getLastSaveDate() {
		return lastSaveDate;
	}

	public void setLastSaveDate(Date lastSaveDate) {
		this.lastSaveDate = lastSaveDate;
	}

	@Override
	public String toString() {
		return super.toString() + " followers : " + this.getFollowers()
				+ " topics : " + this.getTopics();
	}

}
