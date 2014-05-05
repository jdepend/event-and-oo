package demo.command.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import demo.infrastructure.Identifyer;

public class UserVO implements Identifyer, Serializable {

	private String id;

	private int credits;

	private List<String> tags = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return "userId : "+this.getId() + "¡¡credits:" + this.getCredits()+ " tags : " + this.getTags();
	}

}
