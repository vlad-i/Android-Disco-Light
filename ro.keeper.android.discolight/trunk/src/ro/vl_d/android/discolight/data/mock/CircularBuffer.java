package ro.vl_d.android.discolight.data.mock;

public class CircularBuffer {
    private final int[] circularBuffer;
    private int circularBufferIndex = 0;
    private final int bufferSize;

    public CircularBuffer(int size) {
	bufferSize = size;
	circularBuffer = new int[size];
    }

    public int getBufferItem(int index) {
	int bufferIndex = (index + this.circularBufferIndex) % bufferSize;
	return circularBuffer[bufferIndex];
    }

    public void add(int value) {
	circularBuffer[circularBufferIndex] = value;
	circularBufferIndex = (++circularBufferIndex) % bufferSize;
    }
}
