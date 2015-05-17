package ro.vl_d.android.discolight.data.mock;

import android.os.AsyncTask;

/**
 * This class is responsible for starting the data receiving Thread, and
 * performing callbacks to whichever GUI component is executing updates.
 * 
 * @author Vlad
 *
 */
public class MockDataReceiver {
    /**
     * There can be only one Executable present for the DataReceiver.
     * 
     * The Observer design pattern could not be properly implemented here,
     * because multiple listeners might end up costing too much time.
     * 
     */
    private Executable executable;
    private boolean canRun;

    public MockDataReceiver(Executable executable) {
	this.executable = executable;
	canRun = false;
    }

    /**
     * Within this method, a thread is started, and periodically the given
     * {@link Executable#execute(int)} is called
     */
    public void startReceiving() {
	canRun = true;
	// Thread t = new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	//
	// }
	//
	// });
	// t.start();

	AsyncTask<Object, Object, Object> task = new AsyncTask<Object, Object, Object>() {

	    @Override
	    protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		while (canRun) {
		    int value = getCurrentValue();
		    System.out.println("Executing the value:" + value);
		    executable.execute(value);
		    try {
			Thread.sleep(100);
		    } catch (InterruptedException e) {
			e.printStackTrace();
			// we interrupt the loop
			canRun = false;
		    }
		}
		return null;
	    }

	};
	task.execute(new Object[0]);
    }

    /**
     * brings the data population thread to a halt
     */
    public void stopReceiving() {
	canRun = false;
    }

    /**
     * TODO implement a proper audio sampling method here
     * 
     * @return
     */
    private int getCurrentValue() {

	return (int) (Math.random() * 100.0);
    }
}
