package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView newQuestion = findViewById(R.id.questionTextField);
        TextView newAnswer = findViewById(R.id.answerTextField);

        TextView flashcardQuestion = findViewById(R.id.flashcard_question);
        TextView flashcardAnswer = findViewById(R.id.flashcard_answer);
        TextView flashcardAnswer1 = findViewById(R.id.flashcard_answer1);
        TextView flashcardAnswer2 = findViewById(R.id.flashcard_answer2);
        TextView flashcardAnswer3 = findViewById(R.id.flashcard_answer3);
        ImageView toggleView = findViewById(R.id.toggle_choices_visibility);
        ImageView toggleViewOpen = findViewById(R.id.toggle_choices_visibility_open);
        flashcardQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardQuestion.setVisibility(View.INVISIBLE);
                flashcardAnswer.setVisibility(View.VISIBLE);
            }
        });

        flashcardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);
            }
        });

        flashcardAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardAnswer1.setBackgroundColor(getResources().getColor(R.color.nice_red, null));
            }
        });

        flashcardAnswer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                flashcardAnswer2.setBackgroundColor(getResources().getColor(R.color.nice_red, null));
            }
        });

        flashcardAnswer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                flashcardAnswer3.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        toggleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_eye_open);
                boolean isShowingAnswers;
                flashcardAnswer1.setVisibility(View.VISIBLE);
                flashcardAnswer2.setVisibility(View.VISIBLE);
                flashcardAnswer3.setVisibility(View.VISIBLE);
                toggleViewOpen.setVisibility(View.VISIBLE);
            }
        });

        toggleViewOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_eye_slash);
                flashcardAnswer1.setVisibility(View.INVISIBLE);
                flashcardAnswer2.setVisibility(View.INVISIBLE);
                flashcardAnswer3.setVisibility(View.INVISIBLE);
                toggleViewOpen.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
            ((TextView)findViewById(R.id.flashcard_question)).setText(string1);

            ((TextView)findViewById(R.id.flashcard_answer)).setText(string2);

        }
    }
}