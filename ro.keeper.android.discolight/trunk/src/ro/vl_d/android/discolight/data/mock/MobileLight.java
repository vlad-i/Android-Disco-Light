package ro.vl_d.android.discolight.data.mock;

import ro.vl_d.android.discolight.WelcomeScreenActivity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class MobileLight implements Switchable {

    private boolean status = false;

    private Camera cam;

    public MobileLight(Camera camera) {
	this.cam = camera;
    }

    @Override
    public void toggle(boolean on) {
	if (on != status) {
	    Log.i(WelcomeScreenActivity.APP_ID, "Light will be turned "
		    + (on ? "on" : "off"));
	    if (on) {
		Parameters p = cam.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		cam.setParameters(p);
		cam.startPreview();
	    } else {
		Parameters p = cam.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_OFF);
		cam.setParameters(p);
		cam.stopPreview();
	    }
	    status = on;
	}

    }

}
