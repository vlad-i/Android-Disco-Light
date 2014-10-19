/**Proiect-exemplu pentru lucrul cu canvas. E un mod simplificat de a-l desena pe ecran.*/

package com.example.lineexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	LinearLayout layout;
	
	
	Canvas page;
	
	/**Clasa Paint specifica modul in care este desenat obiectul: grosime, culoare, etc.
	 * Este un parametru al functiilor draw ale obiectelor de tip Canvas*/
	Paint style;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layout = (LinearLayout)findViewById(R.id.screen);
		
		style = new Paint();
		style.setColor(Color.BLUE);
		style.setStrokeWidth(20);
		
		Bitmap bm = Bitmap.createBitmap(480, 800, Config.ARGB_8888);
		page = new Canvas(bm);
		
		page.drawRGB(255,  255,  255); //deseneaza fundalul
		page.drawLine(100, 100, 200, 200, style);
		
		//functia asta are nevoie de android API 16, adica Android 4.1.2
		layout.setBackground(new BitmapDrawable(getResources(), bm));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
