
public class Input extends Port {
	private Output output;
	
	public Input(Output output, Component component) {
		this.setComponent(component);
		this.output = output;
		this.value = 0.0;
		this.output.getInputs().add(this);
	}
	
	public Input(Component component) {
		this.setComponent(component);
	}
	
	public void setValue(Object value) {}
	
	public void _notify(Object input) {
		this.value = input;
	}
	
	public Object getValue() {
		return (double) value;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}
}
