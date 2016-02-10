package ro.vl_d.android.discolight.data;

public class PeakDetector implements Executable {

    public final int WEIGHT_ARRAY_SIZE = 100;
    final int[] weights = new int[WEIGHT_ARRAY_SIZE];
    private CircularBuffer ringBuffer;
    private Switchable switchable;

    public PeakDetector(Switchable switchable) {
	this.switchable = switchable;
	populateWeights();
	ringBuffer = new CircularBuffer(WEIGHT_ARRAY_SIZE);
    }

    @Override
    public void execute(int value) {
	ringBuffer.add(value);
	this.switchLED(value);
    }

    private void populateWeights() {

	for (int i = 0; i < WEIGHT_ARRAY_SIZE; i++) {
	    weights[i] = getWeight(i);
	}
    }

    /**
     * This method returns the provided index.
     */
    private int getWeight(int index) {
	return index;
    }

    private boolean switchLED(int currentValue) {
	int average = computeAverage();
	if (average < currentValue) {
	    switchable.toggle(true);
	    return true;
	} else {
	    switchable.toggle(false);
	    return false;
	}
    }

    private int computeAverage() {
	int average = 0;
	int weightTotal = 0;
	int sum = 0;
	for (int i = 0; i < WEIGHT_ARRAY_SIZE; i++) {
	    // we're assuming there is no zero
	    if (ringBuffer.getBufferItem(i) != 0) {
		// TODO find a better way to take only valid data, instead of
		// also a bunch of FIDDYs
		sum += weights[i] * ringBuffer.getBufferItem(i);
		weightTotal += weights[i];
	    }
	}
	average = sum / weightTotal;
	System.out.println("Average is: " + average);
	return average;
    }

}
