package com.example.aashray;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questions = getQuestionList();
        int numQuestions = questions.size();

        ImageView photo = findViewById(R.id.pic);
        TextView questionString = findViewById(R.id.question);
        //Question current = questions.get(counter);
        questionString.setText(questions.get(counter).getQuestion());

        ImageView like = findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLike = true;
                isDislike = false;
                Question current = questions.get(counter);
                current.setResponse(2);
                counter++;
                TextView questionString = findViewById(R.id.question);
                questionString.setText(questions.get(counter).getQuestion());
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
                TextView questionString = findViewById(R.id.question);

                questionString.setText(questions.get(counter).getQuestion());
            }
        });

//        while(counter < numQuestions) {
//            isLike = false;
//            isDislike = false;
//            TextView questionString = findViewById(R.id.question);
//            Question current = questions.get(counter);
//            questionString.setText(current.getQuestion());
//            //photo.setImageResource(current.getImageResourceId());
////            ImageView like = findViewById(R.id.like);
////            ImageView dislike  = findViewById(R.id.dislike);
//            ImageView like = findViewById(R.id.like);
//            like.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    isLike = true;
//                    isDislike = false;
//
//                }
//            });
//            ImageView dislike = findViewById(R.id.dislike);
//            dislike.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    isDislike = true;
//                    isLike = false;
//                }
//            });
//            if (isLike || isDislike) {
//                counter++;
//                if(isLike)
//                    current.setResponse(2);
//                if(isDislike)
//                    current.setResponse(1);
//            }
//            if(counter == numQuestions-1)
//                break;
//        }


    }

    public ArrayList<Question> getQuestionList() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question( "room", "Was room clean and hygienic?"));
        questions.add(new Question("room" , "Was room clean and hygienic?"));
        questions.add(new Question("bed" , "Was bed and mattress comfortable?"));
        questions.add(new Question("transport", "Was the transport available on time?"));
        questions.add(new Question("volunteer", "Were the volunteers helpful?"));
        questions.add(new Question("doctors", "Were the doctors considerate and helpful?"));
        questions.add(new Question("counselling" , "Were the counsellors approachable and friendly?"));
        questions.add(new Question("recreation", "How were the recreational activities?"));
        questions.add(new Question("education", "Was the educational service helpful?"));
        questions.add(new Question("overall", "How was the overall experience?"));

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