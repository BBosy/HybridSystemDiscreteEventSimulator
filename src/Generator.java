import java.util.ArrayList;

public class Generator extends Component {

	double tg = 2;
	
	public Generator() {
		setName("gen");
		this.setOutputs(new ArrayList<>());
		getOutputs().add(new Output(this));
		currentState = State.s;
		this.tr = ta();
		this.e = 0;
	}

	public void init() {
		tr = ta();
		setIns(new ArrayList<>());
		e = 0;
		nextState = State.s;
	}
	
	public void external() {
	}

	public void internal() {
		if(getTr() == 0) {
			nextState = State.s;
			setTr(ta());
		}
		
		currentState = nextState;
	}
	
	public void lambda() {
		
		ArrayList<Port> temp = new ArrayList<>();
		((Output)getOutputs().get(0)).getInputs().get(0).getComponent().setIns(new ArrayList<>());
		
		if(currentState == State.s) {
			getOutputs().get(0).setValue(true);
			
			for(Input i : ((Output)this.getOutputs().get(0)).getInputs()) {
				temp.add(i);
			}
		}
		
		((Output)getOutputs().get(0)).getInputs().get(0).getComponent().setIns(temp);
	}
	
	public double ta() {
		return tg;
	}
	
	public String getNextOutput() {		
		switch(currentState) {
			case s : return "job";
			default : return "";
		}
	}
	
	public void showState() {
		System.out.println("gen : " + getNextOutput() + "|" + getTr());
	}
}
