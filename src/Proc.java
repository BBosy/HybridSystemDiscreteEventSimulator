import java.util.ArrayList;

public class Proc extends Component {
	
	int tp = 3;
	
	public Proc() {
		setName("proc");
		this.setInputs(new ArrayList<>());
		this.setOutputs(new ArrayList<>());
		currentState = State.free;
		this.ta = Double.POSITIVE_INFINITY;
		this.tr = getTa();
		this.e = 0;
	}

	public void addInput(Output output) {
		getInputs().add(new Input(output, this));
	}

	public void init() {
		currentState = State.free;
		nextState = State.free;
		setIns(new ArrayList<>());
		this.tr = getTa();
		this.e = 0;
	}
	
	public void external() {
		if(currentState == State.free && ((boolean) getInputs().get(0).getValue() == true)) {
			nextState = State.busy;
			setIns(new ArrayList<>());
		}
		
		currentState = nextState;
		setTr(ta());
	}

	public void internal() {
		if(currentState == State.busy) {
			nextState = State.free;
		}
		
		currentState = nextState;
		setTr(ta());
	}

	public void lambda() {
		ArrayList<Port> temp = new ArrayList<>();
		((Output)getOutputs().get(0)).getInputs().get(0).getComponent().setIns(new ArrayList<>());
		
		if(currentState == State.busy) {
			getOutputs().get(0).setValue(true);
			
			for(Input i : ((Output)this.getOutputs().get(0)).getInputs()) {
				temp.add(i);
			}
		}

		((Output)getOutputs().get(0)).getInputs().get(0).getComponent().setIns(temp);
	}
	
	@Override
	public double getTa() {
		switch(currentState) {
			case free: return Double.POSITIVE_INFINITY;
			case busy: return tp;
			default: return -1;
		}
	}

	
	public String getNextOutput() {		
		switch(currentState) {
			case free : return "";
			case busy : return "done";
			default : return "";
		}
	}
	
	public void showState() {
		if(getTr() != Double.POSITIVE_INFINITY) System.out.println("proc : " + getNextOutput() + "|" + getTr());
	}
	
	public double ta() {
		switch(currentState) {
		case busy : return tp;
		case free : return Double.POSITIVE_INFINITY;
		default : return -1;
		}
	}
}
