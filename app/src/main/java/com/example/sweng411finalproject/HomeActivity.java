package com.example.sweng411finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.sweng411finalproject.dataObjects.RunningExercise;
import com.example.sweng411finalproject.dataObjects.SwimmingExercise;
import com.example.sweng411finalproject.dataObjects.WalkingExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    Button addWorkout;
    ImageButton refresh;
    ImageButton button;
    ListView list;
    String[] ListElements = new String[] {};

    ArrayList<WalkingExercise> walkingWorkouts = new ArrayList<WalkingExercise>();
    ArrayList<RunningExercise> runningWorkouts = new ArrayList<RunningExercise>();
    ArrayList<SwimmingExercise> swimmingWorkouts = new ArrayList<SwimmingExercise>();

    DBHelper DB = new DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        addWorkout = (Button) findViewById(R.id.addWorkout);
        refresh = (ImageButton) findViewById(R.id.refreshButton);
        list = (ListView) findViewById(R.id.list);
        button = (ImageButton) findViewById(R.id.stopwatchButton);



        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Initializing the popup menu and giving the reference as current context
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, addWorkout);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getTitle().equals("Walking")) {

                            Intent intent = new Intent(getApplicationContext(), WalkingInput.class);
                            intent.putExtra("username", user);
                            startActivity(intent);

                        }
                        else if (menuItem.getTitle().equals("Running")) {

                            Intent intent = new Intent(getApplicationContext(), RunInput.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                        else if (menuItem.getTitle().equals("Swimming")){
                            Intent intent = new Intent(getApplicationContext(), SwimmingInput.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                        return true;
                    }
                });



            }
        });



        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walkingWorkouts = DB.selectWalkingWorkouts(user);
                runningWorkouts = DB.selectRunningWorkouts(user);
                swimmingWorkouts = DB.selectSwimmingWorkouts(user);

                final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));



                for(int i = 0; i < walkingWorkouts.size(); i++){
                    ListElementsArrayList.add("WALKING\n" + "Distance: " + walkingWorkouts.get(i).getDistance() + "\n" + "Steps Taken: " + walkingWorkouts.get(i).getStepsTaken() + "\n" + "Duration: " + walkingWorkouts.get(i).getDuration() + "\n" + "Avg Heart Rate: " + walkingWorkouts.get(i).getAvgHeartRate());
                }

                for(int i = 0; i < runningWorkouts.size(); i++){
                    ListElementsArrayList.add("RUNNING\n" + "Distance: " + runningWorkouts.get(i).getDistance() + "\n" + "Steps Taken: " + runningWorkouts.get(i).getStepsTaken() + "\n" + "Duration: " + runningWorkouts.get(i).getDuration() + "\n" + "Avg Heart Rate: " + runningWorkouts.get(i).getAvgHeartRate());
                }

                for(int i = 0; i < swimmingWorkouts.size(); i++){
                    ListElementsArrayList.add("SWIMMING\n" + "Distance: " + swimmingWorkouts.get(i).getDistance() + "\n" + "Duration: " + swimmingWorkouts.get(i).getDuration() + "\n" + "Avg Heart Rate: " + swimmingWorkouts.get(i).getAvgHeartRate());
                }

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
                list.setAdapter(adapter);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,Clockpage.class);
                startActivity(intent);
            }
        });



    }


}


