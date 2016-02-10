package ro.vl_d.android.discolight;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class WelcomeScreenActivity extends ActionBarActivity {

    private final int DRAWER_NOTIFICATION_ID = 1337;
    LinearLayout layout;
    ToggleButton toggleButton;

    public static final String APP_ID = "ro.vl_d.android.discolight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_welcome_screen);
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
	toggleButton = (ToggleButton) this.findViewById(R.id.toggling_button);
	toggleButton.setChecked(MicLightService.isStarted());
	toggleButton
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    Intent serviceIntent = new Intent(
			    WelcomeScreenActivity.this, MicLightService.class);

		    public void onCheckedChanged(CompoundButton buttonView,
			    boolean isChecked) {

			if (isChecked) {
			    WelcomeScreenActivity.this
				    .startService(serviceIntent);
			    AsyncTask<Object, Object, Object> task = new AsyncTask<Object, Object, Object>() {

				@Override
				protected void onPreExecute() {
				    toggleButton.setEnabled(false);
				}

				@Override
				protected Object doInBackground(
					Object... params) {
				    while (!MicLightService.isStarted()) {
					try {
					    Thread.sleep(50);
					} catch (InterruptedException e) {
					    // TODO Auto-generated catch block
					    e.printStackTrace();
					}
				    }
				    return null;
				}

				@Override
				protected void onPostExecute(Object result) {
				    toggleButton.setEnabled(true);
				}
			    };
			    task.execute();

			} else {
			    WelcomeScreenActivity.this
				    .stopService(serviceIntent);

			    AsyncTask<Object, Object, Object> task = new AsyncTask<Object, Object, Object>() {

				@Override
				protected void onPreExecute() {
				    toggleButton.setEnabled(false);
				}

				@Override
				protected Object doInBackground(
					Object... params) {
				    while (MicLightService.isStarted()) {
					try {
					    Thread.sleep(50);
					} catch (InterruptedException e) {
					    // TODO Auto-generated catch block
					    e.printStackTrace();
					}
				    }
				    return null;
				}

				@Override
				protected void onPostExecute(Object result) {
				    toggleButton.setEnabled(true);
				}
			    };
			    task.execute();

			}

		    }
		});

    }

    @Override
    public void onPause() {
	super.onPause();
	if (MicLightService.isStarted()) {
	    NotificationCompat.Builder builder = new NotificationCompat.Builder(
		    this).setSmallIcon(R.drawable.ic_launcher)
		    .setContentTitle("MicLight")
		    .setContentText("MicLight still working...")
		    .setAutoCancel(true);

	    // poc

	    // Gets an instance of the NotificationManager service
	    NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

	    Intent resultIntent = new Intent(this, WelcomeScreenActivity.class);

	    // Because clicking the notification opens a new ("special")
	    // activity,
	    // there's
	    // no need to create an artificial back stack.
	    PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
		    0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	    builder.setContentIntent(resultPendingIntent);
	    // Builds the notification and issues it.
	    mNotifyMgr.notify(DRAWER_NOTIFICATION_ID, builder.build());
	    // / poc
	}
    }
}
