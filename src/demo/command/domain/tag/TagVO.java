package demo.command.domain.tag;

import demo.infrastructure.Identifyer;

public class TagVO implements Identifyer {

	private String content;

	private int heat;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	@Override
	public String getId() {
		return this.content;
	}

	@Override
	public String toString() {
		return "content : " + this.content + "¡¡heat:" + this.heat;
	}

}
