package com.example.aashray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    public boolean isLike;
    public boolean isDislike;
    public int counter = 0;
    ArrayList<Question> questions;
    public int numQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questions = getQuestionList();
        numQuestions = questions.size();

        resetCurrentView(counter);

        ImageView like = findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLike = true;
                isDislike = false;
                Question current = questions.get(counter);
                current.setResponse(2);
                counter++;

                resetCurrentView(counter);
            }
        });
        ImageView dislike = findViewById(R.id.dislike);
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDislike = true;
                isLike = false;
                Question current = questions.get(counter);
                current.setResponse(1);
                counter++;
                resetCurrentView(counter);
            }
        });

    }

    public void resetCurrentView(int counter) {
        ImageView photo = findViewById(R.id.pic);
        TextView questionString = findViewById(R.id.question);
        Question current = questions.get(counter);
        questionString.setText(current.getQuestion());
        photo.setImageResource(current.getImageResourceId());
        if(counter == (numQuestions-1)) {
            Intent intent = new Intent(QuestionActivity.this, ExitActivity.class);
            startActivity(intent);
        }
    }

    public ArrayList<Question> getQuestionList() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question( "food", "Was the food provided to you good enough?", R.drawable.food));
        questions.add(new Question("room" , "Was the room clean and hygienic?", R.drawable.room));
        questions.add(new Question("bed" , "Was bed and mattress comfortable?", R.drawable.bed));
        questions.add(new Question("transport", "Was the transport available on time?", R.drawable.transport));
        questions.add(new Question("volunteer", "Were the volunteers helpful?", R.drawable.volunteers));
        questions.add(new Question("doctors", "Were the doctors considerate and helpful?", R.drawable.doctor));
        questions.add(new Question("counselling" , "Were the counsellors approachable and friendly?", R.drawable.counselor));
        questions.add(new Question("recreation", "How were the recreational activities?", R.drawable.recreation));
        questions.add(new Question("education", "Was the educational service helpful?", R.drawable.education));
        questions.add(new Question("overall", "How was the overall experience?", R.drawable.overall));

        return questions;
    }

    public void clickedLike(View w) {
        isLike = true;
        isDislike = false;
    }

    public void clickedDislike(View w) {
        isDislike = true;
        isLike = false;
    }
}