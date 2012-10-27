package com.example.quadros.alpha;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ProgressActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* ======================= */
    /*      Partial Views      */
    /* ======================= */
    
    public void clearDataAction(View v) {
    	// TODO
    }

}
