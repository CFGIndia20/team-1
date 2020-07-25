package com.example.aashray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
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

        // this list of questions can be dynamically retrieved
        questions = getQuestionList();
        numQuestions = questions.size();

        resetCurrentView(counter);

        // storing the response for each question
        ImageView like = findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLike = true;
                isDislike = false;
                Question current = questions.get(counter);
                current.setResponse(2);
                counter++;
                // showing the next question
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

        // updating view acc to question number
        ImageView photo = findViewById(R.id.pic);
        TextView questionString = findViewById(R.id.question);
        Question current = questions.get(counter);
        questionString.setText(current.getQuestion());
        photo.setImageResource(current.getImageResourceId());

        if(counter == (numQuestions-1)) {
            // when questionnaire list has ended, give incentives to the user for filling out the feedback
            incentiveNotif();

            // new page that has personalised tips for the user
            Intent intent = new Intent(QuestionActivity.this, ExitActivity.class);
            startActivity(intent);
        }
    }

    public void incentiveNotif() {
        // providing an incentive like free consulting

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
        mBuilder.setContentTitle("Aashray");
        mBuilder.setContentText("Thanks for filling out the feedback, here is a free consultation for you");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }

    public void clickedLike(View w) {
        isLike = true;
        isDislike = false;
    }

    public void clickedDislike(View w) {
        isDislike = true;
        isLike = false;
    }

    public ArrayList<Question> getQuestionList() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question( "food", "Was the food provided to you good enough?", R.drawable.food));
        questions.add(new Question("room" , "Was the room clean and hygienic?", R.drawable.room));
        questions.add(new Question("bed" , "Was bed and mattress comfortable?", R.drawable.bed));
        questions.add(new Question("transport", "Was the transport available on time?", R.drawable.transport));
        questions.add(new Question("volunteer", "Were the volunteers helpful and friendly?", R.drawable.volunteers));
        questions.add(new Question("doctors", "Were the doctors considerate and helpful?", R.drawable.doctor));
        questions.add(new Question("counselling" , "Were the counsellors approachable and friendly?", R.drawable.counselor));
        questions.add(new Question("recreation", "How were the recreational activities?", R.drawable.recreation));
        questions.add(new Question("education", "Was the educational service helpful?", R.drawable.education));
        questions.add(new Question("overall", "How was the overall experience?", R.drawable.overall));

        return questions;
    }

}