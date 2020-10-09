import java.util.ArrayList;

public abstract class Component {
	private ArrayList<Port> inputs;
	private ArrayList<Port> outputs;
	private ArrayList<Port> ins;
	State currentState;
	State nextState;
	double ta;
	double tr;
	double tl;
	double tn;
	double e;
	private String name;
	
	public ArrayList<Port> getInputs() {
		return inputs;
	}
	public void setInputs(ArrayList<Port> inputs) {
		this.inputs = inputs;
	}
	public ArrayList<Port> getOutputs() {
		return outputs;
	}
	public void setOutputs(ArrayList<Port> outputs) {
		this.outputs = outputs;
	}
	public ArrayList<Port> getIns() {
		return ins;
	}
	public void setIns(ArrayList<Port> ins) {
		this.ins = ins;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void external();
	public abstract void internal();
	public abstract void lambda();
	public abstract void init();
	public abstract double ta();
	
	public void addInput(Output output) {
		inputs.add(new Input(output, this));
	}
	
	public void addOutput(ArrayList<Input> inputs) {
		outputs.add(new Output(inputs, this));
	}
	
	public void addOutput() {
		outputs.add(new Output(this));
	}
	
	public double getTr() {
		return tr;
	}
	
	public void setTr(double tr) {
		this.tr = tr;
	}
	
	public double getTl() {
		return tl;
	}
	
	public void setTl(double tl) {
		this.tl = tl;
	}
	
	public double getTn() {
		return tn;
	}
	
	public void setTn(double tn) {
		this.tn = tn;
	}
	
	public double getE() {
		return e;
	}
	
	public void setE(double e) {
		this.e = e;
	}
	
	public double getTa() {
		return this.ta;
	}
	
	public void setTa(double ta) {
		this.ta = ta;
	}

	enum State{
		s,
		free,
		busy,
		a,
		b,
		c
	}
	
	public abstract void showState();
	
	
}
