package com.example.quadros.alpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* ==================== */
    /*      Activities      */
    /* ==================== */
    
    // button redirects to main gameplay screen
	public void playAction(View v) {
		Intent intent = new Intent(this, PlayActivity.class);
		startActivityForResult(intent, 0);
	}
	
	// button redirects to progress chart
	public void progressAction(View v) {
		Intent intent = new Intent(this, ProgressActivity.class);
		startActivityForResult(intent, 0);
	}

    /* ======================= */
    /*      Partial Views      */
    /* ======================= */
	
	public void modesAction(View v) {
		// TODO
	}
	
	public void optionsAction(View v) {
		// TODO
	}
	
}
