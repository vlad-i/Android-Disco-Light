package ro.vl_d.android.discolight;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MicLightService extends Service {
    MicLightManager micLight;
    private static boolean started;

    @Override
    public void onCreate() {
	micLight = new MicLightManager(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
	// no binding will be used
	return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
	if (!micLight.isStarted()) {
	    micLight.start();
	}
	started = true;

	return START_STICKY;
    }

    @Override
    public void onDestroy() {
	micLight.stop();
	started = false;
    }

    public static boolean isStarted() {
	return started;
    }

}
