package App;

import java.util.ArrayList;

public class Shared {
    public ArrayList<Double> list = new ArrayList<Double>();
    private boolean shouldContinue = true;
    private boolean changed = false;

    void stop() {
	shouldContinue = false;
    }

    boolean shouldContinue() {
	return shouldContinue;
    }

    synchronized void setElem(Double value) {
	list.add(value);
	changed = true;
    }

    synchronized Double get_last_element() {
	Double element = list.get(list.size() - 1);
	changed = false;
	return element;
    }

    public boolean wasChanged() {
	// TODO Auto-generated method stub
	return changed;
    }
}
