package com.bowenzhai.materialize;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
        findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
    }

    public void onHintClick(View view) {
        Toast toasty = Toast.makeText(getApplicationContext(), getString(R.string.H1), Toast.LENGTH_SHORT);
        toasty.show();
    }

    public void onAnswerClick(View view) {
        String answer = ((EditText)findViewById(R.id.answer)).getText().toString();
        answer = answer.toLowerCase();
        String correctAnswer = getString(R.string.A1);
        correctAnswer = correctAnswer.toUpperCase();
        answer = answer.toUpperCase();

        if (answer.equals(correctAnswer)) {
            TextView t = (TextView) findViewById(R.id.correctornot);
            t.setText("CORRECT!");
            ImageView i = (ImageView) findViewById(R.id.tickcross);
            i.setImageDrawable(getDrawable(R.drawable.tick));
            answerSubmitted();
        } else {
            TextView t = (TextView) findViewById(R.id.correctornot);
            t.setText("CORRECT ANSWER: " + correctAnswer);
            ImageView i = (ImageView) findViewById(R.id.tickcross);
            i.setImageDrawable(getDrawable(R.drawable.cross));
            answerSubmitted();
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
}
