import java.util.ArrayList;

public class Adder extends Component{

	private double sum;
	State currentState;
	State nextState;

	enum State{
		free,
		busy
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		setName("add");
		sum=0;
		currentState = State.free;
		setOutputs(new ArrayList<>());
		setInputs(new ArrayList<>());
		setIns(new ArrayList<>());
		setTr(ta());
	}
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public void external() {
		// TODO Auto-generated method stub
		nextState = State.busy;
		for(Port i : getIns()) {
			sum += (double) i.getValue();
			((Input) i).getOutput().setValue(0.0);
		}
		
		setIns(new ArrayList<>());
		
		currentState = nextState;
		setTr(ta());
	}

	@Override
	public void internal() {
		nextState = State.free;
		currentState = nextState;
		setTr(ta());
	}

	@Override
	public void lambda() {
		// TODO Auto-generated method stub
		getOutputs().get(0).setValue(sum);
		if(((Output) getOutputs().get(0)).getInputs().size() > 0)
			((Output)getOutputs().get(0)).getInputs().get(0).getComponent().getIns().add(((Output)getOutputs().get(0)).getInputs().get(0));
	}
	
	@Override
	public void showState() {
		System.out.println("sum="+sum);
		if(getTr() != Double.POSITIVE_INFINITY) System.out.println("sum|"+getTr());
	}
	
	public double ta() {
		switch(currentState) {
			case free: return Double.POSITIVE_INFINITY;
			case busy: return 0;
			default: return -1;
		}
	}
}
