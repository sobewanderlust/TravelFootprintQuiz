package com.example.android.travelfootprintquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int numberOfTravelQuestions = 5;
    int points[] = new int[numberOfTravelQuestions];

    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setArray();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method to recognize the traveler name
     */
    String getName()
    {
        return ((EditText) findViewById(R.id.name_box)).getText().toString();
    }

    /**
     * Method to use RadioButton and which answers are correct or incorrect and assign the final score.
     */
    public void AssignScore(View view)
    {

        RadioGroup rg = (RadioGroup) view.getParent();
        String rgTag = rg.getTag().toString();
        int questionNumber = Integer.parseInt(rgTag);

        RadioButton selectedAnswer = (RadioButton) view;
        if(selectedAnswer.getTag().toString().equals(getString(R.string.tag_answer_correct)))
            points[questionNumber - 1] = 1;
        else
            points[questionNumber - 1] = 0;

    }

    /**
     * Method to calculate the final score.
     */
    void quizSummary(View view)
    {
        int total = 0;
        for(int i=0; i<numberOfTravelQuestions; i++)
            total += points[i];


        String name = getName();
        String result = getString(R.string.final_score_message, name, total, numberOfTravelQuestions);

        String message;
        if(total == 0)
            message = getString(R.string.message1);
        else if(total < numberOfTravelQuestions/2)
            message = getString(R.string.message2);
        else if(total < numberOfTravelQuestions)
            message = getString(R.string.message3);
        else
            message = getString(R.string.message4);
        result += "\n" + message;
     /*
     * This will show the view of the text for a short period of time
     */
        Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
        toast.show();
    }

    /*
     * Array initialized with a default value of zero
     */
    void setArray()
    {
        for(int i = 0; i<numberOfTravelQuestions; i++)
            points[i]=0;
    }

    /**
     * This method is for saving data.
     */

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.getInt("SavedTotal", total);
    }
    /**
     * This method is for restoring data.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            total = savedInstanceState.getInt("SavedTotal");
        }
    }
}