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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvanceActivity extends AppCompatActivity {
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
    Toast toast = null;
    String textButton;


    private void readyTimer(){
       timeleft = 10 * 1000;
       mCountDownTimer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(toast != null) toast.cancel();
                Log.v(TAG,"OnTick: " + millisUntilFinished);
                if(toast != null) toast.cancel();
                seconds = String.valueOf(millisUntilFinished / 1000);
                toast = Toast.makeText(AdvanceActivity.this, "Get Ready in " + seconds + " Seconds", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onFinish() {
                toast.cancel();
                placeMoleTimer();
                Log.v(TAG,"Completed");
                Toast.makeText(AdvanceActivity.this, "Go", Toast.LENGTH_SHORT).show();

            }
        }.start();
    }
    private void placeMoleTimer(){
        mCountDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setNewMole();
            }

            @Override
            public void onFinish() {
                mCountDownTimer.start();
            }
        }.start();
    }


    private static final int[] BUTTON_IDS = {1,2,3,4,5,6,7,8,9};
    private ArrayList<Button> buttonList = new ArrayList<>();

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

        for (int i = 1; i <= BUTTON_IDS.length; i++) {
            final Button button = findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            buttonList.add(button);
        }

        for(int i = 0; i < buttonList.size(); i++){
            final Button button = buttonList.get(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;
                    String buttonText = b.getText().toString();
                    doCheck(buttonText);
                }
            });
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Set all to "O"
        for (int i = 1; i <= BUTTON_IDS.length; i++) {
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            button.setText("O");
        }
    }

    private void doCheck(String checkButton)
    {
        if(checkButton == "*") {Log.v(TAG, "Hit, Score added!"); advancedScore = advancedScore + 1;}
        else{ Log.v(TAG, "Missed, Score deducted"); advancedScore = advancedScore - 1;}
        viewScore.setText(Integer.toString(advancedScore));
        setNewMole();
    }

    public void setNewMole()
    {
        //Set all to "O"
        for (int i = 1; i <= BUTTON_IDS.length; i++) {
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            button.setText("O");
        }

        Random ran = new Random();
        int randomLocation = ran.nextInt(8)+1;
        Log.v(TAG,"Its located at: " + randomLocation);

        Button button = (Button) findViewById(getResources().getIdentifier("button" + randomLocation, "id", this.getPackageName()));
        button.setText("*");
    }
}

