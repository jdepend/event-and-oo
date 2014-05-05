package demo.command.domain.tag;

import java.util.Date;

public class TagRepoVO extends TagVO {

	private Date lastSaveDate;

	public Date getLastSaveDate() {
		return lastSaveDate;
	}

	public void setLastSaveDate(Date lastSaveDate) {
		this.lastSaveDate = lastSaveDate;
	}

}
