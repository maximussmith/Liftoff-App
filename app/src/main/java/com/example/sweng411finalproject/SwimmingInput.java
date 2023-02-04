package com.example.sweng411finalproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sweng411finalproject.dataObjects.SwimmingExercise;
public class SwimmingInput extends AppCompatActivity{
    SwimmingExercise exercise = new SwimmingExercise();
    EditText durationBox,distanceBox,avgHeartRateBox;
    Button button;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swimming_popup);
        Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        DB = new DBHelper(this);
        durationBox = (EditText) findViewById(R.id.duration);
        distanceBox = (EditText) findViewById(R.id.distance);
        avgHeartRateBox = (EditText) findViewById(R.id.heartRate);
        button = (Button) findViewById(R.id.add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(avgHeartRateBox.getText() != null && distanceBox != null && durationBox.getText() != null) {
                    exercise.setDuration(Integer.parseInt(durationBox.getText().toString()));
                    exercise.setDistance(Integer.parseInt(distanceBox.getText().toString()));
                    exercise.setAvgHeartRate(Integer.parseInt(avgHeartRateBox.getText().toString()));
                    DB.insertSwimmingWorkout(exercise, user);
                }
                Toast.makeText(SwimmingInput.this, "Successfully Entered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        });
    }
}