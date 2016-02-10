package ro.vl_d.android.discolight;

import ro.vl_d.android.discolight.data.DataReceiver;
import ro.vl_d.android.discolight.data.Executable;
import ro.vl_d.android.discolight.data.MobileLight;
import ro.vl_d.android.discolight.data.PeakDetector;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;
import android.widget.Toast;

class MicLightManager {

    private Camera cam;
    private DataReceiver receiver;
    private Context context;
    private boolean started;

    MicLightManager(Context context) {
	this.context = context;
	Log.i(WelcomeScreenActivity.APP_ID, "Instantiating MicLightManager");
    }

    public void start() {
	if (context.getPackageManager().hasSystemFeature(
		PackageManager.FEATURE_CAMERA_FLASH)) {
	    cam = Camera.open();
	    MobileLight light = new MobileLight(cam);
	    Executable detector = new PeakDetector(light);
	    receiver = new DataReceiver(detector);
	    receiver.startReceiving();
	    started = true;
	} else {
	    Toast.makeText(context,
		    "This system does not have the camera flash feature",
		    Toast.LENGTH_LONG).show();
	    ;
	}

    }

    public void stop() {
	if (context.getPackageManager().hasSystemFeature(
		PackageManager.FEATURE_CAMERA_FLASH)) {
	    receiver.stopReceiving();
	    cam.release();
	    started = false;
	}

    }

    public boolean isStarted() {
	return started;
    }
}
