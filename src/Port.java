
public abstract class Port {
	String name;
	Object value;
	private Component component;
	
	public Object getValue() {
		return value;
	}
	
	public abstract void setValue(Object value);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
}
