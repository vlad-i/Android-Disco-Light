package ro.vl_d.android.discolight;

import ro.vl_d.android.discolight.drawing.ChartPainter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class WelcomeScreenActivity extends ActionBarActivity {

    LinearLayout layout;

    Canvas page;

    /**
     * Clasa Paint specifica modul in care este desenat obiectul: grosime,
     * culoare, etc. Este un parametru al functiilor draw ale obiectelor de tip
     * Canvas
     */
    Paint style;
    ChartPainter p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	p = new ChartPainter(this);
	setContentView(p);
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

    }

    @Override
    protected void onPause() {
	super.onPause();
	p.pause();
    }
}
