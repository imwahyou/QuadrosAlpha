package com.example.quadros.alpha;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class PlayActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* ========================== */
    /*      Gameplay Actions      */
    /* ========================== */
    
    // button click represents correct user guess
    public void correctAction() {
    	// TODO
    	scoreIncrease();
    }
    
    // called by correctAction
    private void scoreIncrease() {
    	// TODO
    }
    
    // button click represents incorrect user guess
    public void incorrectAction() {
    	// TODO
    	heartDecrease();
    }
    
    // called by incorrectAction
    private void heartDecrease() {
    	// TODO
    }
    
    /* ======================= */
    /*      Partial Views      */
    /* ======================= */
    
    public void newGameAction() {
    	// TODO
    }
    
    public void retryGameAction() {
    	// TODO
    }
    
    public void quitGameAction() {
    	// TODO
    }
}
