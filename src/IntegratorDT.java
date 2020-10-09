import java.util.ArrayList;

public class IntegratorDT extends Component {

	double x;
	double diffx;
	double hstep; 
	State currentState;
	State nextState;
	
	enum State{
		free,
		busy
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		setName("inte");
		x = 0;
		hstep = 0.001;
		currentState = State.free;
		setInputs(new ArrayList<>());
		setOutputs(new ArrayList<>());
		setIns(new ArrayList<>());
		setTr(ta());
	}
	
	public void init(double x0, double hstep) {
		setName("inte");
		x = x0;
		this.hstep = hstep;
		currentState = State.free;
		setInputs(new ArrayList<>());
		setOutputs(new ArrayList<>());
		setIns(new ArrayList<>());
		setTr(ta());
	}
	
	@Override
	public void external() {
		diffx = (double) getInputs().get(0).getValue();
		x = x + diffx * e;
		currentState = State.busy;
		setIns(new ArrayList<>());
		setTr(ta());
	}

	@Override
	public void internal() {
		// TODO Auto-generated method stub
		switch(currentState) {
			case free: nextState = State.busy; break;
			case busy: nextState = State.free; break;
		}
		
		x += diffx * hstep;
		
		currentState = nextState;
		setTr(ta());
	}

	@Override
	public void lambda() {
		// TODO Auto-generated method stub
		getOutputs().get(0).setValue(x);

		for(Input i : ((Output)getOutputs().get(0)).getInputs()) {
			((Output)getOutputs().get(0)).getInputs().get(0).getComponent().getIns().add(i);
		}
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(currentState) {
			case free: return hstep;
			case busy: return 0;
			default: return -1;
		}
	}

	@Override
	public void showState() {
		// TODO Auto-generated method stub
		System.out.println("x="+x);
		if(currentState == State.busy)
			System.out.println("x|"+getTr());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getHstep() {
		return hstep;
	}

	public void setHstep(double hstep) {
		this.hstep = hstep;
	}

}
