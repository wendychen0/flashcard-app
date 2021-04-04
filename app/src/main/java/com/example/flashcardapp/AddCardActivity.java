package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView saveButton = findViewById(R.id.saveButton);

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String question = ((EditText) findViewById(R.id.questionTextField)).getText().toString();
                String answer = ((EditText) findViewById(R.id.answerTextField)).getText().toString();
                String answer2 = ((EditText) findViewById(R.id.answerTextField2)).getText().toString();
                String answer3 = ((EditText) findViewById(R.id.answerTextField3)).getText().toString();

                if (question.equals("") || answer.equals("") || answer2.equals("") || answer3.equals("")){
                    Toast.makeText(getApplicationContext(), "Must enter both Question and Answers!", Toast.LENGTH_SHORT).show();

                } else {
                    Intent data = new Intent(); // create a new Intent, this is where we will put our data
                    data.putExtra("question", question); // puts one string into the Intent, with the key as 'string1'
                    data.putExtra("string2", answer); // puts another string into the Intent, with the key as 'string2
                    data.putExtra("string3", answer2); // puts another string into the Intent, with the key as 'string2
                    data.putExtra("string4", answer3); // puts another string into the Intent, with the key as 'string2

                    setResult(RESULT_OK, data); // set result code and bundle data for response
                    finish(); // closes this activity and pass data to the original activity that launched this activity
                }



            }
        });
    }

}