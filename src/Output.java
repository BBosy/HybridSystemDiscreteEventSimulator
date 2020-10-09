import java.util.ArrayList;

public class Output extends Port {
	private ArrayList<Input> inputs;
	
	public Output(ArrayList<Input> inputs, Component component) {
		this.setComponent(component);
		this.inputs = inputs;
	}
	
	public Output(Component component) {
		this.inputs = new ArrayList<>();
		this.setComponent(component);
		this.value = 0.0;
	}
	
	public void addInput(Input i) {
		this.inputs.add(i);
	}
	
	public void setValue(Object value) {
		this.value = (double) value;
		for(Input input : inputs) {
			input._notify(this.value);
		}
	}

	public ArrayList<Input> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<Input> inputs) {
		this.inputs = inputs;
	}
}
