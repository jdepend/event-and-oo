package demo.infrastructure;


public interface PersistentObject extends Identifyer {

	public void load();

	public void save() throws Exception;
}
