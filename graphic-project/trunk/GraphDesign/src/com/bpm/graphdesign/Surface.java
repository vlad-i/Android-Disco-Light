package com.bpm.graphdesign;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Surface extends SurfaceView implements Runnable{

	
	private SurfaceHolder surfaceHolder = null;
	
	/**Thread that will draw on the surface view*/
	private Thread drawThread = null;
	
	/**Where the drawing will be*/
	private Canvas drawingSheet = null;
	
	/**Offset of drawing. For Y margins purpose*/
	private Double offsetY;
	
	/**Drawing height in pixels corresponding to the maxvalue (Direct proportionality)*/
	private Double drawingHeight;
	
	/**Used to set margins and drawing sizes*/
	private Double fragmentation = 38.0 / 40;
	
	/**The style of the line drawing*/
	private Paint linePaint;
	
	private boolean isRunning = false;
	
	private ArrayList<Double> values;
	
	public Surface(Context context, ArrayList<Double> v) {
		super(context);
		
		surfaceHolder = this.getHolder();
		drawThread = new Thread(this);
		isRunning = true;
		
		linePaint = new Paint();
		linePaint.setStrokeWidth(1);
		linePaint.setStyle(Paint.Style.FILL);
		linePaint.setColor(Color.BLACK);
		
		//38/40 (fragmentation variable) is for test purposes
		offsetY = AppData.getWindowHeightV() / 40.0;
		drawingHeight = AppData.getWindowHeightV() *fragmentation;
		
		values = v;
		drawThread.start();
		
	}

	@Override
	public void run() {
		while(isRunning)
			if(surfaceHolder.getSurface().isValid()){
				drawingSheet = surfaceHolder.lockCanvas();
				
				//white background
				drawingSheet.drawRGB(255, 255, 255);
				
				drawGraph();
				
				
				surfaceHolder.unlockCanvasAndPost(drawingSheet);
		}
		
	}

	private void drawGraph() {
		/*int x[] = {50, 100, 100, 50};
		int y[] = {50, 50, 100, 100};
		
		for(int i = 0; i < 3; i++){
			drawingSheet.drawLine(x[i], y[i], x[i+1], y[i+1], linePaint);
		}
		drawingSheet.drawLine(x[3], y[3], x[0], y[0], linePaint);*/
		
	/*	
		//X-axis drawing
		drawingSheet.drawLine(0, AppData.getWindowHeightV()-30, AppData.getWindowWidthV(), AppData.getWindowHeightV()-30, linePaint);
		
		//Y-axis drawing
		drawingSheet.drawLine(30, 0, 30, AppData.getWindowHeightV(), linePaint);
	
		*/
		
		for(int i = 0; i < values.size()-1; ++i){
			double y1 = AppData.getRatio() * values.get(i) * fragmentation;
			double pozY1 = offsetY + drawingHeight - y1;
			
			double y2 = AppData.getRatio() * values.get(i+1) * fragmentation;
			double pozY2 = offsetY + drawingHeight - y2;
			
			//set the Path class - used to create a shape from input points and fill it
			Path trapezium = new Path();
			trapezium.setFillType(Path.FillType.EVEN_ODD);
			
			//draw trapezium
			trapezium.moveTo((i+1)*50.f, (float)pozY1);
			trapezium.lineTo((i+2) * 50.f, (float)pozY2);
			trapezium.lineTo((i+2) * 50.f, (float)(offsetY + drawingHeight));
			trapezium.lineTo((i+1) * 50.f, (float)(offsetY + drawingHeight));
			trapezium.lineTo((i+1) * 50.f, (float)pozY1);
			
			//close path
			trapezium.close();
			
			//draw shape
			drawingSheet.drawPath(trapezium, linePaint);
		}
		
	}

	/**This will be called when the activity's onPause function has been called*/
	public void pause(){
		isRunning = false;
		try{
			drawThread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		drawThread = null;
	}
	
	/**This will be called when the activity's onResume function has been called*/
	public void resume(){
		if (drawThread == null) {
			drawThread = new Thread(this);
			isRunning = true;
			drawThread.start();
		}
	}

}
