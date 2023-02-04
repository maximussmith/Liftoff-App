package com.example.sweng411finalproject.dataObjects;

public class RunningExercise extends Exercise{
    private int distance;
    private int stepsTaken;


    public RunningExercise(int distance, int stepsTaken, int duration, int avgHeartRate){
        this.distance = distance;
        this.stepsTaken = stepsTaken;
        setDuration(duration);
        setAvgHeartRate(avgHeartRate);
        setCaloriesBurned(calculateCalories());

    }
    public RunningExercise(){
        distance = 0;
        stepsTaken = 0;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getStepsTaken() {
        return stepsTaken;
    }

    public void setStepsTaken(int stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    private int calculateCalories(){
        return 0;
    }
}
