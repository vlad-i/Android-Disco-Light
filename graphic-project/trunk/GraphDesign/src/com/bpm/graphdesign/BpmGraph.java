package com.bpm.graphdesign;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class BpmGraph extends Activity {
	
	/**Received values*/
	private ArrayList<Double> values = null;
	
	/**SurfaceView which will be shown to the user*/
	private Surface surface = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setup();
        
        
        surface = new Surface(this, values);
        setContentView(surface);
    }


    private void setup() {
		// TODO appdata setting;
    	//TODO: future: get resources(if any other elements will appear)
    	//request no title
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        //request full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        values = new ArrayList<Double>();
        addTestValues();
        
        setupAppData();
        
		
	}


    /**Only for test purposes*/
	private void addTestValues() {
		values.add(25D);
		values.add(125D);
		values.add(45D);
		values.add(250D);
		values.add(160D);
		values.add(560D);
		values.add(200D);
		values.add(180D);
			
	}


	private void setupAppData() {
		//set AppData orientation of activity
		AppData.setOrientation(getResources().getConfiguration().orientation);
		
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        if(AppData.getOrientation() == Configuration.ORIENTATION_LANDSCAPE){
        	AppData.setWindowHeightH(dm.heightPixels);
        	AppData.setWindowWidthH(dm.widthPixels);
        }else{
        	AppData.setWindowHeightV(dm.heightPixels);
        	AppData.setWindowWidthV(dm.widthPixels);
        }
        	
        
        //set minimum and maximum registered values
        //TODO: acquire them from the algorithmic project
        //test values
       
        
        //setting the minValue to 0 (considering only positives. still to be changed)
        AppData.setMinValue(0D);
        
        for(int i = 0; i < values.size(); ++i){
        	if(values.get(i) > AppData.getMaxValue())
        		AppData.setMaxValue(values.get(i));
        	else
        		if(values.get(i) < AppData.getMinValue())
        			AppData.setMinValue(values.get(i));
        }
       
        AppData.setRatio();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bpm_graph, menu);
        return true;
    }


	@Override
	protected void onPause() {
		surface.pause();
		super.onPause();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		surface.resume();
	}
    
}
