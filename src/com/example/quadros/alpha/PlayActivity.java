package com.example.quadros.alpha;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {
	static final int DIALOG_NEW_ID = 0;
	static final int DIALOG_RETRY_ID = 1;
	static final int DIALOG_QUIT_ID = 2;
	
	private static final String TAG = "PlayActivity";
	private static int SCORE;
	private TextView mScoreTextView; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_view);
        
        SCORE = 0;
        // set textview
        mScoreTextView = (TextView) findViewById(R.id.score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch(id) {
			case DIALOG_NEW_ID:
				dialog = createNewDialog(builder);
				break;
			case DIALOG_RETRY_ID:
				dialog = createRetryDialog(builder);
				break;
			case DIALOG_QUIT_ID:
				dialog = createQuitDialog(builder);
				break;
		}

		if(dialog == null)
			Log.d("Dialog", "Uh oh! Dialog is null");
		else
			Log.d("Dialog", "Dialog created: " + id + ", dialog: " + dialog);
		return dialog;        
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
    
    public void newGameAction(View v) {
    	showDialog(DIALOG_NEW_ID);
    }
    
    public void retryGameAction(View v) {
    	showDialog(DIALOG_RETRY_ID);
    }
    
    public void quitGameAction(View v) {
    	showDialog(DIALOG_QUIT_ID);
    }
    
    /* ======================= */
    /*      Dialog Handlers    */
    /* ======================= */
    
	private Dialog createNewDialog(AlertDialog.Builder builder) {
		builder.setTitle(R.string.newgame);

		final CharSequence[] levels = {
				getResources().getString(R.string.yes),
				getResources().getString(R.string.no)};

		//final int selected = mGame.getDifficultyLevel().ordinal();
		builder.setItems(levels, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				dialog.dismiss();   // Close dialog	    
			}
		});
		return builder.create();
	}
	
	private Dialog createRetryDialog(AlertDialog.Builder builder) {
		builder.setTitle(R.string.retry);

		final CharSequence[] levels = {
				getResources().getString(R.string.yes),
				getResources().getString(R.string.no)};

		//final int selected = mGame.getDifficultyLevel().ordinal();
		builder.setItems(levels, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				dialog.dismiss();   // Close dialog	    
			}
		});
		return builder.create();
	}
    
	private Dialog createQuitDialog(AlertDialog.Builder builder) {
		builder.setTitle(R.string.quit);

		final CharSequence[] levels = {
				getResources().getString(R.string.yes),
				getResources().getString(R.string.no)};

		//final int selected = mGame.getDifficultyLevel().ordinal();
		builder.setItems(levels, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				dialog.dismiss();   // Close dialog	    
				if (item == 0){
	                //Finish the splash activity so it can't be returned to.
	                PlayActivity.this.finish();
	                // Create an Intent that will start the main activity.
	                Intent intent = new Intent(PlayActivity.this, MenuActivity.class);
	                PlayActivity.this.startActivity(intent);
				}
			}
		});
		return builder.create();
	}
}
