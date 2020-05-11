package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */

    String TAG = "Advance";
    private CountDownTimer mCountDownTimer;
    int advancedScore = 0;
    TextView viewScore;
    String seconds;
    long timeleft;
    Button buttonleft, buttonright, buttonmiddle;
    Button buttonleft2, buttonmiddle2, buttonright2;
    Button buttonleft3, buttonmiddle3, buttonright3;
    Toast toast;

    private void readyTimer(){
       timeleft = 10;

        mCountDownTimer = new CountDownTimer(timeleft, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                toast.cancel();
                Log.v(TAG,"OnTick: " + timeleft);
                seconds = String.valueOf(timeleft);
                toast.makeText(Main2Activity.this, "Get Ready in " + seconds + " Seconds", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinish() {
                Log.v(TAG,"Completed");
                Toast.makeText(Main2Activity.this, "Go", Toast.LENGTH_LONG).show();

            }
        }.start();
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
    }
    private static final Button[] BUTTON_IDS = {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        advancedScore = Integer.parseInt(intent.getStringExtra("Score"));
        Log.v(TAG, "Current User Score: " + advancedScore);
        viewScore = findViewById(R.id.score);
        viewScore.setText(String.valueOf(advancedScore));

        readyTimer();

    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
    }
}

