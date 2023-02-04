package com.example.sweng411finalproject.dataObjects;

public class SwimmingExercise extends Exercise{
    private int distance;

    public SwimmingExercise(int distance, int duration, int avgHeartRate){
        this.distance = distance;
        setDuration(duration);
        setAvgHeartRate(avgHeartRate);
        setCaloriesBurned(calculateCalories());
    }
    public SwimmingExercise(){
        distance = 0;
    }

    private int calculateCalories(){
        return 0;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}
