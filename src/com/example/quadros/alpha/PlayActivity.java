package com.example.quadros.alpha;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PlayActivity extends Activity {
	
	private static final String TAG = "PlayActivity";
	private static int SCORE = 0;
	private TextView mScoreTextView; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_view);
        
        // set textview
        mScoreTextView = (TextView) findViewById(R.id.score);
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
    public void correctAction(View v) {
    	Log.d(TAG, "in correctAction");
    	// TODO
    	scoreIncrease();
    	displayScore();
    }
    
    // called by correctAction
    private void scoreIncrease() {
    	Log.d(TAG, "in scoreIncrease");
    	SCORE+=10;
    }
    
    private void displayScore() {
    	Log.d(TAG, "in displayScore");
    	mScoreTextView.setText(Integer.toString(SCORE));
    }
    
    // button click represents incorrect user guess
    public void incorrectAction(View v) {
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
