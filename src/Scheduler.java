import java.util.ArrayList;

import chart.Chart;
import chart.ChartFrame;

public class Scheduler{

	static ArrayList<Component> comps = new ArrayList<>();
	static ChartFrame cf = new ChartFrame("Intégrateur à temps discret","COO");
	static Chart cs = new Chart("sum");
	static Chart cx = new Chart("x");
//	static Chart cq = new Chart("q");
	
	public static void main(String[] args) {
		
		ArrayList<Step> steps = new ArrayList<>();
		Adder add = new Adder();
		IntegratorDT idt = new IntegratorDT();
		
		idt.init();
		
		for(Component c : comps) {
			c.init();
		}
		
		add.init();
	 	
		for(int i=0 ; i<4 ; i++) {
			steps.add(new Step());
		}
		
		comps.addAll(steps);
		comps.add(add);
		comps.add(idt);
		
		steps.get(0).init("1", 1.0, -3.0, 0.65);
		steps.get(1).init("2", 0.0, 1.0, 0.35);
		steps.get(2).init("3", 0.0, 1.0, 1.0);
		steps.get(3).init("4", 0.0, 4.0, 1.5);
		
		for (int i=0 ; i<4; i++) {
			steps.get(i).addOutput();
		}
		
		add.addOutput();
		
		idt.addInput((Output) add.getOutputs().get(0));
		idt.addOutput();
		
		for(int i=0 ; i<4 ; i++) {
			add.addInput((Output) steps.get(i).getOutputs().get(0));
		}
		
		
//		Generator gen = new Generator();
//		Buf buf = new Buf();
//		Proc proc = new Proc();
//		cf.addToLineChartPane(cq);
//		
//		comps.add(buf);
//		comps.add(gen);
//		comps.add(proc);
//		
//		for(Component c : comps) {
//			c.init();
//			//System.out.println("*** " + c.getTr());
//		}
//		
//		proc.addOutput();
//		gen.addOutput();
//		buf.addOutput();
//		
//		buf.addInput((Output) gen.getOutputs().get(0));
//		buf.addInput((Output) proc.getOutputs().get(0));
//		
//		proc.addInput((Output) buf.getOutputs().get(0));
		
		run();
	}
	
	public static void run() {
		double t = 0;
		double tfin = 20;
		cf.addToLineChartPane(cs);
		cf.addToLineChartPane(cx);
		
		while(t < tfin) {
			
			double trmin = getTrMin(comps);
			ArrayList<Component> cImms = new ArrayList<>();
			
			System.out.println("**************************************");
			t += trmin;
			System.out.println("trmin = " + trmin);
			System.out.println("t = " + t);
				
			
			for(Component c : comps) {
				if(trmin == c.getTr())
					cImms.add(c);
			}
			
			for(Component c : comps) {
				c.setTr(c.getTr() - trmin);
				c.setE(c.ta() - c.getTr());
			}
			
			for(Component c : cImms) {
				c.lambda();
			}
			
			for(Component c : comps) {
				
				if(cImms.contains(c) && c.getIns().size()==0){
					c.internal();
				} else if(!cImms.contains(c) && c.getIns().size()!=0) {
					c.external();
				} else if(cImms.contains(c) && c.getIns().size()!=0) {
					c.internal();
				}
			}
			

			if(t!=Double.POSITIVE_INFINITY) {
				cs.addDataToSeries(t, ((Adder) comps.get(4)).getSum());
				cx.addDataToSeries(t, ((IntegratorDT) comps.get(5)).getX());
			}
			
//			cq.addDataToSeries(t, ((Buf) comps.get(0)).getQ());
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static double getTrMin(ArrayList<Component> comp) {
		double trmin = comp.get(0).getTr();
		
		for(Component c : comp) {
			if(trmin > c.getTr()) trmin = c.getTr();
		}
		
		return trmin;
	}
}
