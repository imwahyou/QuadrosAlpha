package com.example.quadros.alpha;

import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {
	static final int DIALOG_NEW_ID = 0;
	static final int DIALOG_RETRY_ID = 1;
	static final int DIALOG_QUIT_ID = 2;
	static final int DIALOG_START_ID = 3;

	private static final String TAG = "PlayActivity";
	private TextView mScoreTextView;

	private QuadrosGame mGame;
	private Button mBoardButtons[];
	private ImageView mHearts[];
	private boolean isGameOver;
	
	// for all the sounds  we play
	private SoundPool mSounds;
	private HashMap<Integer, Integer> mSoundIDMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "in onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_view);

		// set textview
		mScoreTextView = (TextView) findViewById(R.id.score);
		mGame = new QuadrosGame();

		isGameOver = false;
		createSoundPool();
		// hello
		mBoardButtons = new Button[mGame.getBoardSize()];
		mBoardButtons[0] = (Button) findViewById(R.id.b0);
		mBoardButtons[1] = (Button) findViewById(R.id.b1);
		mBoardButtons[2] = (Button) findViewById(R.id.b2);
		mBoardButtons[3] = (Button) findViewById(R.id.b3);
		mBoardButtons[4] = (Button) findViewById(R.id.b4);
		mBoardButtons[5] = (Button) findViewById(R.id.b5);
		mBoardButtons[6] = (Button) findViewById(R.id.b6);
		mBoardButtons[7] = (Button) findViewById(R.id.b7);
		mBoardButtons[8] = (Button) findViewById(R.id.b8);

		mHearts = new ImageView[4];
		mHearts[0] = (ImageView) findViewById(R.id.heart1);
		mHearts[1] = (ImageView) findViewById(R.id.heart2);
		mHearts[2] = (ImageView) findViewById(R.id.heart3);
		mHearts[3] = (ImageView) findViewById(R.id.heart4);

		// Reset all buttons
		for (int i = 0; i < mBoardButtons.length; i++) {
			mBoardButtons[i].setText("");
			mBoardButtons[i].setEnabled(true);
			mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));    		 		   
		}

		showAnswer();
		
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				clearAnswer();
			}
		}, 1500);
		displayScore();
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
	
	/* ======================= */
	/*      Music & Sound      */
	/* ======================= */
	
	private void createSoundPool() {
		int[] soundIds = {R.raw.correctbeep, R.raw.incorrectbeef, R.raw.bgmusic};
		mSoundIDMap = new HashMap<Integer, Integer>();
		mSounds = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		for(int id : soundIds) 
			mSoundIDMap.put(id, mSounds.load(this, id, 1));
		
	}


	/* ========================== */
	/*      Gameplay Actions      */
	/* ========================== */

	// button click represents correct user guess
	public void correctAction(View v) {
		Log.d(TAG, "in correctAction");
		mSounds.play(mSoundIDMap.get(R.raw.correctbeep), 1, 1, 1, 0, 1);
		displayScore();

		// concurrency problem (potential)
		checkAnswer();
		if (isGameOver) {

			for (int i = 0; i < mBoardButtons.length; i++) {
				mBoardButtons[i].setEnabled(false);		 		   
			}

			newGameAction(v);
		}

	}

	private void displayScore() {
		Log.d(TAG, "in displayScore");
		mScoreTextView.setText(Integer.toString(mGame.getScore()));
	}

	// button click represents incorrect user guess
	public void incorrectAction(View v) {
		mSounds.play(mSoundIDMap.get(R.raw.incorrectbeef), 1, 1, 1, 0, 1);
		if(mGame.getLife() > 0) {
			mHearts[mGame.getLife()].setVisibility(View.INVISIBLE);

			Button test = new Button(this);
			test.setWidth(100);
			test.setHeight(100);
		}

		else {
			mHearts[mGame.getLife()].setVisibility(View.INVISIBLE);
				for (int i = 0; i < mBoardButtons.length; i++) {
					mBoardButtons[i].setEnabled(false);		 		   
				}
				showAnswer();
				retryGameAction(v);
		}
	}

	public void checkAnswer() {
		boolean check = true;
		for (int s : mGame.getSelectedCells()) {
			check &= mGame.getBoardLocation(s);
		}
		
		isGameOver = check;
	}

	public void showAnswer() {
		for (int s : mGame.getSelectedCells()) {
			mBoardButtons[s].setBackgroundColor(Color.GREEN);
		}
		
	}

	public void clearAnswer() {
		for (int i = 0; i < mGame.getBoardSize(); i++) {
			mBoardButtons[i].setBackgroundResource(android.R.drawable.btn_default);
		}
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

		builder.setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				dialog.dismiss();   // Close dialog  
				//Finish the splash activity so it can't be returned to.
				PlayActivity.this.finish();
				// Create an Intent that will start the main activity.
				Intent intent = new Intent(PlayActivity.this, PlayActivity.class);
				PlayActivity.this.startActivity(intent);
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
				if (item == 0){
					//Finish the splash activity so it can't be returned to.
					PlayActivity.this.finish();
					// Create an Intent that will start the main activity.
					Intent intent = new Intent(PlayActivity.this, PlayActivity.class);
					PlayActivity.this.startActivity(intent);
				}
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

	// on touch listener

	// Listen for touches on the board
	// Handles clicks on the game board buttons
	private class ButtonClickListener implements View.OnClickListener {
		int location;

		public ButtonClickListener(int location) {
			this.location = location;
		}

		public void onClick(View view) {
			if (!isGameOver) {
				if(mGame.setMove(location)) {
					mBoardButtons[location].setEnabled(false);
					mBoardButtons[location].setBackgroundColor(Color.GREEN);
					correctAction(view);
				}
				else {
					incorrectAction(view);
				}
				//if (!mGameOver && mBoardButtons[location].isEnabled()) {
				//	mGame.setMove(location);

				// If no winner yet, let the computer make a move
				//int winner = mGame.checkForWinner();

				//}
			}
		}
	}

	//    public void addButton(View v) {
	//        /* Find Tablelayout defined in main.xml */
	//        TableLayout tl = (TableLayout)findViewById(R.id.play_grid);
	//             /* Create a new row to be added. */
	//             TableRow tr = new TableRow(this);
	//             tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	//                  /* Create a Button to be the row-content. */
	//                  Button b = new Button(this);
	//                  b.setText("Dynamic Button");
	//                  b.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	//                  /* Add Button to row. */
	//                  tr.addView(b);
	//        /* Add row to TableLayout. */
	//        tl.addView(tr,new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	//    }
}
