package com.chromsicle.mathmania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<>(); //ArrayList for answer choices
    Button startButton;
    int correctAnswerPosition;
    TextView resultView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreView;
    TextView equationView;
    TextView timerView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationView = findViewById(R.id.equationView);
        button0 = findViewById(R.id.answerButton0);
        button1 = findViewById(R.id.answerButton1);
        button2 = findViewById(R.id.answerButton2);
        button3 = findViewById(R.id.answerButton3);
        resultView = findViewById(R.id.resultView);
        scoreView = findViewById(R.id.scoreView);
        timerView = findViewById(R.id.timerView);
        playAgainButton = findViewById(R.id.playAgainButton);
        startButton = findViewById(R.id.startButton);
        gameLayout = findViewById(R.id.gameLayout);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.resultView)); //arbitrarily put a view in here because it needs a view
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void answerSelected(View view) {
        if (Integer.toString(correctAnswerPosition).equals(view.getTag().toString())) {
            resultView.setText("Correct!");
            score++;
        } else {
            resultView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreView.setText(score + "/" + numberOfQuestions);
        newQuestion();
    }

    public void newQuestion(){
        //variable for the random numbers used to make up equations
        Random randomNum = new Random();

        int a = randomNum.nextInt(21);
        int b = randomNum.nextInt(21);
        equationView.setText(a + " + " + b + " = ");

        //variable to randomly choose where the correct answer will be
        correctAnswerPosition = randomNum.nextInt(4);

        //clear out everything from the array
        answers.clear();


        for (int i=0; i<4; i++) {
            //if the position matches the correctAnswerPosition, make the button display the correct answer
            if(i == correctAnswerPosition) {
                answers.add(a + b);
            } else {
                int wrongAnswer = randomNum.nextInt(41);

                //if the wrongAnswer is actually the correct answer, choose again until it's different
                while (wrongAnswer == a + b) {
                    wrongAnswer = randomNum.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerView.setText("30s");
        scoreView.setText(score + "/" + numberOfQuestions);
        newQuestion();
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
        resultView.setText("");

        //set timer to 30100
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);

                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mplayer.start();
                resultView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
