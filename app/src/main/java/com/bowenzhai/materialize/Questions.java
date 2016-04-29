package com.bowenzhai.materialize;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Questions extends AppCompatActivity {

    private boolean done;
    private int questionNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        String[] questions = getResources().getStringArray(R.array.Questions);
        TextView t = (TextView) findViewById(R.id.question);
        t.setText(questions[questionNo]);
        findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
        findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
    }

    public void onHintClick(View view) {
        String[] hints = getResources().getStringArray(R.array.Hints);
        Toast toasty = Toast.makeText(getApplicationContext(), hints[questionNo], Toast.LENGTH_SHORT);
        toasty.show();
    }

    public void onAnswerClick(View view) {
        if (!done) {
            String answer = ((EditText) findViewById(R.id.answer)).getText().toString();
            String[] answers = getResources().getStringArray(R.array.Answers);
            answer = answer.toLowerCase();
            String correctAnswer = answers[questionNo];
            correctAnswer = correctAnswer.toUpperCase();
            answer = answer.toUpperCase();

            if (answer.equals(correctAnswer)) {
                TextView t = (TextView) findViewById(R.id.correctornot);
                t.setText("CORRECT!");
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.right);
                mp.start();
                ImageView i = (ImageView) findViewById(R.id.tickcross);
                i.setImageDrawable(getDrawable(R.drawable.tick));
                answerSubmitted();
            } else {
                TextView t = (TextView) findViewById(R.id.correctornot);
                t.setText("CORRECT ANSWER: " + correctAnswer);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                mp.start();
                ImageView i = (ImageView) findViewById(R.id.tickcross);
                i.setImageDrawable(getDrawable(R.drawable.cross));
                answerSubmitted();
            }
            done = true;
        }
    }

    public void answerSubmitted() {
        findViewById(R.id.tickcross).setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(0,0,2000,0);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.correctornot).setVisibility(View.VISIBLE);
                findViewById(R.id.nextbutton).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        findViewById(R.id.tickcross).startAnimation(animation);
    }

    public void onNextClick(View view){
        if (done) {
            String[] questions = getResources().getStringArray(R.array.Questions);
            if (questionNo < (questions.length - 1)) {
                ++questionNo;
                findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
                findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
                findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
                EditText et = (EditText) findViewById(R.id.answer);
                et.setText("");
                TextView t = (TextView) findViewById(R.id.question);
                t.setText(questions[questionNo]);
                done = false;
            }
        }
    }
}
