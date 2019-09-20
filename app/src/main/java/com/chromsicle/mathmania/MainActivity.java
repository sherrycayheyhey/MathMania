package com.chromsicle.mathmania;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //create an ArrayList for answer choices
    ArrayList<Integer> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView equationView = findViewById(R.id.equationView);
        Button button0 = findViewById(R.id.answerButton0);
        Button button1 = findViewById(R.id.answerButton1);
        Button button2 = findViewById(R.id.answerButton2);
        Button button3 = findViewById(R.id.answerButton3);

        //variable for the random numbers used to make up equations
        Random randomNum = new Random();

        int a = randomNum.nextInt(21);
        int b = randomNum.nextInt(21);
        equationView.setText(a + " + " + b + " = ");

        //variable to randomly choose where the correct answer will be
        int correctAnswerPosition = randomNum.nextInt(4);

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
}
