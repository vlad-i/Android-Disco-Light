package com.example.lineexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class ChartPainter extends SurfaceView implements Runnable {

    /** Holder of surface */
    private SurfaceHolder holder;

    /** Drawing thread */
    private Thread drawThread;

    /** Canvas for drawing */

    private Canvas canvas = null;

    /** Bool for running state */
    private boolean isRunning = true;

    public ChartPainter(Context context) {
	super(context);
	holder = getHolder();
	holder.addCallback(new Callback() {

	    @Override
	    public void surfaceCreated(SurfaceHolder holder) {
		Thread t = new Thread(ChartPainter.this);
		t.start();
		// ChartPainter.this.drawChart();
	    }

	    @Override
	    public void surfaceChanged(SurfaceHolder holder, int format,
		    int width, int height) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	    }
	});
    }

    private void drawSomeShit() {
	if (holder.getSurface().isValid()) {
	    canvas = holder.lockCanvas();
	    canvas.drawRGB(255, 255, 205);
	    holder.unlockCanvasAndPost(canvas);
	}
    }

    @Override
    public void run() {
	do {
	    if (holder.getSurface().isValid()) {
		canvas = holder.lockCanvas();
		/** Draw background */
		canvas.drawRGB(255, 255, 70);

		/** Draw Chart */
		Log.e("ChartPainter", "running the draw chart soon");
		drawChart();

		/** Print to screen */
		holder.unlockCanvasAndPost(canvas);
	    }
	    try {
		Thread.sleep(5000);
	    } catch (InterruptedException e) {
		Log.e("ChartPainter", e.getMessage());
	    }
	    if (holder.getSurface().isValid()) {
		canvas = holder.lockCanvas();
		/** Draw background */
		canvas.drawRGB(255, 255, 205);

		/** Draw Chart */
		Log.e("ChartPainter", "running the draw chart soon");
		drawChart();

		/** Print to screen */
		holder.unlockCanvasAndPost(canvas);
	    }
	} while (isRunning);
    }

    private void drawChart() {

	float startX = (float) (100 * Math.random());
	float startY = (float) (100 * Math.random());
	
	float stopY = (float) (100 * Math.random());
	
	Paint linePaint = new Paint();
	linePaint.setColor(Color.BLUE);
	linePaint.setStrokeWidth(20);
	
	float stopX = (float) (100 * Math.random());
	
	canvas.drawLine(startX, startY, stopX, stopY, linePaint);
    }

    /** Will be called from GraphDrawer when onPause() will occur */
    public void pause() {
	isRunning = false;

	try {
	    drawThread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	drawThread = null;
    }

    public void resume() {
	drawThread = new Thread(this);
	isRunning = true;
	drawThread.start();
    }

}
