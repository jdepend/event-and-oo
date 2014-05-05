package demo.command.base;

public abstract class AbstractHandler<T extends Event> implements Handler<T> {

	protected void doDestory() throws Exception {

	}

	@Override
	public void destory() throws Exception {
		this.doDestory();
		EventBus.cancelHandler(this);
	}

}
