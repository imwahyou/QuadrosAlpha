package com.example.quadros.alpha;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends Activity {
	
	static final int DIALOG_DIFFICULTY_ID = 0;
	static final int DIALOG_OPTION_ID = 1;
	
	private SharedPreferences mPrefs;
	
	//private boolean mSfx = true;
	//private boolean mMusic = true;
	
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
    
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch(id) {
			case DIALOG_DIFFICULTY_ID:
				dialog = createDifficultyDialog(builder);
				break;
			case DIALOG_OPTION_ID:
				dialog = createOptionDialog(builder);
				break;
		}

		if(dialog == null)
			Log.d("Dialog", "Uh oh! Dialog is null");
		else
			Log.d("Dialog", "Dialog created: " + id + ", dialog: " + dialog);
		return dialog;        
	}
    
    /* ==================== */
    /*      Activities      */
    /* ==================== */
    
    // button redirects to main gameplay screenDIALOG_QUIT_ID
	public void playAction(View v) {
		//mSounds.play(mSoundIDMap.get(R.raw.bgmusic), (float)0.1, (float)0.1, 1, 0, 1);
		
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
		showDialog(DIALOG_DIFFICULTY_ID);
	}
	
	public void optionsAction(View v) {
		showDialog(DIALOG_OPTION_ID);
	}
	
    /* ======================= */
    /*      Dialog Handlers    */
    /* ======================= */
    
	private Dialog createDifficultyDialog(AlertDialog.Builder builder) {
		builder.setTitle(R.string.difficulty_choose);

		final CharSequence[] levels = {
				getResources().getString(R.string.difficulty_easy),
				getResources().getString(R.string.difficulty_medium), 
				getResources().getString(R.string.difficulty_hard)};

		//final int selected = mGame.getDifficultyLevel().ordinal();
		builder.setItems(levels, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				dialog.dismiss();   // Close dialog

				//mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.values()[item]);

				// Display the selected difficulty level
				Toast.makeText(getApplicationContext(), "Difficulty changed to: " + levels[item], 
						Toast.LENGTH_SHORT).show();        	    
			}
		});
		return builder.create();
	}
	
	private Dialog createOptionDialog(AlertDialog.Builder builder) {
		builder.setTitle(R.string.options);

		final CharSequence[] levels = {
				getResources().getString(R.string.music),
				getResources().getString(R.string.sfx)};
		final boolean[] checked = {
				true,
				true};
		
		builder.setMultiChoiceItems(levels, checked, 
				new DialogInterface.OnMultiChoiceClickListener() {
					
					//@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						checked[which] = isChecked;
					}
				});
		builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				dialog.dismiss();   // Close dialog   	    
			}
		});
		return builder.create();
	}
	
}
