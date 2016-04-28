package com.bowenzhai.materialize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
    }

    public void onHintClick(View view) {
        Toast toasty = Toast.makeText(getApplicationContext(), getString(R.string.H1), Toast.LENGTH_SHORT);
        toasty.show();
    }

    public void onAnswerClick(View view) {
        String answer = ((EditText)findViewById(R.id.answer)).getText().toString();
        answer = answer.toLowerCase();
        String correctAnswer = getString(R.string.A1);

        if (answer.equals(correctAnswer)) {
            Toast toasty = Toast.makeText(getApplicationContext(), "Right!", Toast.LENGTH_SHORT);
            toasty.show();
        } else {
            Toast toasty = Toast.makeText(getApplicationContext(), "Nope!", Toast.LENGTH_SHORT);
            toasty.show();
        }
    }
}
