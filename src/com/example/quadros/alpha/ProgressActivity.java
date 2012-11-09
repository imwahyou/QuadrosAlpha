package com.example.quadros.alpha;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ProgressActivity extends Activity {
	
	static final int DIALOG_CLEAR_ID = 0;
	
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
    
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch(id) {
			case DIALOG_CLEAR_ID:
				dialog = this.createClearDialog(builder);
				break;
		}

		if(dialog == null)
			Log.d("Dialog", "Uh oh! Dialog is null");
		else
			Log.d("Dialog", "Dialog created: " + id + ", dialog: " + dialog);
		return dialog;        
	}
    
    /* ======================= */
    /*      Partial Views      */
    /* ======================= */
    
    public void clearDataAction(View v) {
    	showDialog(DIALOG_CLEAR_ID);
    }
    
    /* ======================= */
    /*      Dialog Handlers    */
    /* ======================= */
    
	private Dialog createClearDialog(AlertDialog.Builder builder) {
		builder.setTitle(R.string.sure);

		final CharSequence[] levels = {
				getResources().getString(R.string.yes),
				getResources().getString(R.string.no), };
		
		builder.setItems(levels, 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				dialog.dismiss();   // Close dialog

				//mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.values()[item]);   	    
			}
		});
		return builder.create();
	}

}
