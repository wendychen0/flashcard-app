package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

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
                flashcardAnswer1.setBackgroundColor(getResources().getColor(R.color.green, null));
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
                flashcardAnswer3.setBackgroundColor(getResources().getColor(R.color.nice_red, null));
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

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() == 0)
                    return;
                currentCardDisplayedIndex += 1;
                flashcardAnswer1.setBackgroundColor(getResources().getColor(R.color.yellow, null));
                flashcardAnswer2.setBackgroundColor(getResources().getColor(R.color.yellow, null));
                flashcardAnswer3.setBackgroundColor(getResources().getColor(R.color.yellow, null));
                if(currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(flashcardQuestion,
                            "You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT).show();
                    currentCardDisplayedIndex = 0;
                }
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard.getAnswer());
                ((TextView) findViewById(R.id.flashcard_answer1)).setText(flashcard.getAnswer());
                ((TextView) findViewById(R.id.flashcard_answer2)).setText(flashcard.getWrongAnswer1());
                ((TextView) findViewById(R.id.flashcard_answer3)).setText(flashcard.getWrongAnswer2());
            }
        });
        findViewById(R.id.trashButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());

                allFlashcards = flashcardDatabase.getAllCards();
                // if we delete the only card, display nothing
                if (allFlashcards.size() == 0) {
                    ((TextView) findViewById(R.id.flashcard_question)).setText("Add a card!");
                    // If we delete the last card, display the first card
                } else if (currentCardDisplayedIndex >= allFlashcards.size()) {
                    currentCardDisplayedIndex = 0;
                } else {
                    Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);
                    ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard.getAnswer());
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText(flashcard.getAnswer());
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText(flashcard.getWrongAnswer1());
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText(flashcard.getWrongAnswer2());
                }


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
            String string3 = data.getExtras().getString("string3");
            String string4 = data.getExtras().getString("string4");
            ((TextView)findViewById(R.id.flashcard_question)).setText(question);

            ((TextView)findViewById(R.id.flashcard_answer)).setText(string2);
            ((TextView)findViewById(R.id.flashcard_answer2)).setText(string3);
            ((TextView)findViewById(R.id.flashcard_answer3)).setText(string4);

            flashcardDatabase.insertCard(new Flashcard(question, string2, string3, string4));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }
}