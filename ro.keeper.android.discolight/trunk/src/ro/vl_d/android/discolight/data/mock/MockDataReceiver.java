package ro.vl_d.android.discolight.data.mock;

import java.io.IOException;

import ro.vl_d.android.discolight.WelcomeScreenActivity;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.util.Log;

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
    private MediaRecorder recorder = null;

    public MockDataReceiver(Executable executable) {
	this.executable = executable;
	canRun = false;
	recorder = new MediaRecorder();
	recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	recorder.setOutputFile("/dev/null");
	try {
	    recorder.prepare();
	} catch (IllegalStateException e) {

	    Log.e(WelcomeScreenActivity.APP_ID, e.getMessage());

	} catch (IOException e) {

	    Log.e(WelcomeScreenActivity.APP_ID, e.getMessage());
	}
    }

    /**
     * Within this method, a thread is started, and periodically the given
     * {@link Executable#execute(int)} is called
     */
    public void startReceiving() {
	canRun = true;

	AsyncTask<Object, Object, Object> task = new AsyncTask<Object, Object, Object>() {

	    @Override
	    protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		recorder.start();
		while (canRun) {
		    int value = getCurrentValue();
		    System.out.println("Executing the value:" + value);
		    executable.execute(value);
		    try {
			Thread.sleep(50);
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
	if (recorder != null) {
	    recorder.stop();
	    recorder.release();
	    recorder = null;
	}
	canRun = false;
    }

    /**
     * TODO implement a proper audio sampling method here
     * 
     * @return
     */
    private int getCurrentValue() {
	double amplitude = getAmplitude();
	Log.i(WelcomeScreenActivity.APP_ID, "Amplitude: " + amplitude);
	return (int) amplitude;
    }

    private double getAmplitude() {
	if (recorder != null) {
	    return recorder.getMaxAmplitude();
	} else {
	    // TODO find a better default value
	    return 1;
	}
    }
}
