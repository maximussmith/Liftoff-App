package com.example.sweng411finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.sweng411finalproject.dataObjects.WalkingExercise;

public class SignUpActivity extends AppCompatActivity {
    //WalkingExercise exercise = new WalkingExercise();
    EditText username, password, repassword;
    Button register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        DB = new DBHelper(this);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                //exercise.setDuration(Integer.parseInt("6"));
                //exercise.setStepsTaken(Integer.parseInt("6"));
                //exercise.setDistance(Integer.parseInt("6"));
                //exercise.setAvgHeartRate(Integer.parseInt("6"));

                if (user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if (!checkuser){
                            Boolean insert = DB.insertUserPass(user, pass);
                            //DB.insertWalkingWorkout(exercise,"1");
                            if (insert){
                                Toast.makeText(SignUpActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }

                    }
                }
            }
        });
    }


}