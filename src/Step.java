import java.util.ArrayList;

public class Step extends Component {

	double xi;
	double xf;
	double ts;
	
	enum State{
		i,
		f,
		rest
	}
	
	State currentState;
	State nextState;
	
	public void init(String name, Double xi, Double xf, Double ts) {
		this.setName(name);
		this.xi = xi;
		this.xf = xf;
		this.ts = ts;
		currentState = State.i;
		setOutputs(new ArrayList<>());
		setInputs(new ArrayList<>());
		setIns(new ArrayList<>());
		setTr(ta());
	}
	
	@Override
	public void internal() {
		switch(currentState) {
			case i: nextState = State.f; break;
			case f: nextState = State.rest; break;
			case rest: break;
		}
		
		currentState = nextState;
		setTr(ta());
	}
	
	@Override
	public void lambda() {
		switch(currentState) {
			case i: ((Output) getOutputs().get(0)).setValue((double) xi); break;
			case f: ((Output) getOutputs().get(0)).setValue((double) xf); break;
			case rest: break;
		}
		
		((Output) getOutputs().get(0)).getInputs().get(0).getComponent().getIns().add(((Output)getOutputs().get(0)).getInputs().get(0));
	}
	
	@Override
	public double ta() {
		switch(currentState) {
			case i: return 0;
			case f: return ts;
			case rest: return Double.POSITIVE_INFINITY;
			default : return -1.0;
		}
	}

	@Override
	public void external() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showState() {
		switch(currentState) {
			case i: System.out.println("xi"+getName()+"|"+getTr()); break;
			case f: System.out.println("xf"+getName()+"|"+getTr()); break;
			case rest: break;
		}
	}
}
