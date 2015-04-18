package App;

class Average implements Runnable {
    private Shared shared;
    public double sum;
    private Double current_element;

    Average(Shared elements) {
	this.shared = elements;
    }

    private double calc_average() {
	double sum = 0;
	for (int i = 0; i < shared.list.size(); i++) {
	    sum += shared.list.get(i);
	}
	return sum / shared.list.size();
    }

    private boolean light() {
	if (current_element > calc_average()) 
	    return true;
	return false;
    }

    @Override
    public void run() {
	while (shared.shouldContinue()) {
	    try {
		Thread.sleep(100);

		if (shared.list.size() != 0 && shared.wasChanged()) {
		    calc_average();
		    current_element = shared.get_last_element();
		    System.out.println("Led: " + current_element + " is "
			    + light() + "\n");
		}
	    } catch (InterruptedException ex) {
		// asdada
	    }
	}
    }
}
