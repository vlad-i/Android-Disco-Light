package ro.vl_d.android.discolight;

import ro.vl_d.android.discolight.data.mock.Executable;
import ro.vl_d.android.discolight.data.mock.MobileLight;
import ro.vl_d.android.discolight.data.mock.MockDataReceiver;
import ro.vl_d.android.discolight.data.mock.PeakDetector;
import ro.vl_d.android.discolight.drawing.ChartPainter;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.LinearLayout;

public class WelcomeScreenActivity extends ActionBarActivity {

    LinearLayout layout;

    public static final String APP_ID = "ro.vl_d.android.discolight";
    Canvas page;

    /**
     * Clasa Paint specifica modul in care este desenat obiectul: grosime,
     * culoare, etc. Este un parametru al functiilor draw ale obiectelor de tip
     * Canvas
     */
    Paint style;
    ChartPainter p;

    private boolean flashIsOn = false;

    private Camera cam;
    private MockDataReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	/**
	 * this is the old code for the painting of the chart
	 * 
	 * requestWindowFeature(Window.FEATURE_NO_TITLE);
	 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	 * WindowManager.LayoutParams.FLAG_FULLSCREEN); p = new
	 * ChartPainter(this); setContentView(p);
	 */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.welcome_screen, menu);
	return true;
    }

    @Override
    protected void onResume() {
	super.onResume();
	if (this.getPackageManager().hasSystemFeature(
		PackageManager.FEATURE_CAMERA_FLASH)) {
	    cam = Camera.open();
	    MobileLight light = new MobileLight(cam);
	    Executable detector = new PeakDetector(light);
	    receiver = new MockDataReceiver(detector);
	    receiver.startReceiving();
	}
    }

    @Override
    protected void onPause() {
	super.onPause();
	if (this.getPackageManager().hasSystemFeature(
		PackageManager.FEATURE_CAMERA_FLASH)) {
	    receiver.stopReceiving();
	    cam.release();
	}
	// p.pause();
    }

}
