import java.util.ArrayList;

public class Buf extends Component{
	
	public int getQ() {
		return q;
	}

	public void setQ(int q) {
		this.q = q;
	}

	int q;
	
	public Buf() {
		setName("buf");
		this.setInputs(new ArrayList<>());
		this.setOutputs(new ArrayList<>());
		currentState = State.a;
		this.ta = Double.POSITIVE_INFINITY;
		tr = ta();
		e = 0;
		q=0;	
	}
	
	public void init() {
		currentState = State.a;
		setIns(new ArrayList<>());
		tr = ta();
		e = 0;
	}
	
	public void lambda() {
		
		ArrayList<Port> temp = new ArrayList<>();
		((Output)getOutputs().get(0)).getInputs().get(0).getComponent().setIns(new ArrayList<>());
		
		if(currentState == State.b) {
			getOutputs().get(0).setValue(true);
			
			for(Input i : ((Output)this.getOutputs().get(0)).getInputs()) {
				temp.add(i);
			}
		}
		
		((Output)getOutputs().get(0)).getInputs().get(0).getComponent().setIns(temp);
	}
	
	public void external() {
		if(currentState == State.a && ((boolean) getInputs().get(0).getValue() == true)) {
			((Input)getInputs().get(0)).getOutput().setValue(false);
			q++;
			nextState = State.b;
			setIns(new ArrayList<>());
		}
		
		if(currentState == State.b && ((boolean) getInputs().get(0).getValue() == true)) {
			((Input)getInputs().get(0)).getOutput().setValue(false);
			q++;
			nextState = State.b;
			setIns(new ArrayList<>());
		}
		
		if(currentState == State.c && ((boolean) ((Input)getInputs().get(1)).getValue() == true) && q>0) {
			((Input)getInputs().get(1)).getOutput().setValue(false);
			nextState = State.b;
			setIns(new ArrayList<>());
		}
		if(currentState == State.c && ((boolean) getInputs().get(0).getValue() == true)) {
			((Input)getInputs().get(0)).getOutput().setValue(false);
			nextState = State.c;
			q++;
			setIns(new ArrayList<>());
		}
		
		if(currentState == State.c && ((boolean) getInputs().get(1).getValue() == true) && q==0) {
			((Input)getInputs().get(1)).getOutput().setValue(false);
			nextState = State.a;
			setIns(new ArrayList<>());
		}
		
		currentState = nextState;
		//System.out.println(currentState);
		setTr(ta());
	}
	
	public void internal() {
		if(currentState == State.b) {
			nextState = State.c;
			q--;
		}
		
		currentState = nextState;
		setTr(ta());
	}
	
	public double ta() {
		switch(currentState) {
			case a : return Double.POSITIVE_INFINITY;
			case b : return 0;
			case c : return Double.POSITIVE_INFINITY;
			default : return -1;
		}
	}
	
	public String getNextOutput() {	
		switch(currentState) {
			case a : return "";
			case b : return "req";
			case c : return "";
			default : return "";
		}
	}
	
	public void showState() {
		System.out.println("q="+q);
		if(getTr() != Double.POSITIVE_INFINITY) System.out.println("buf : " + getNextOutput() + "|" + getTr());
	}
}
