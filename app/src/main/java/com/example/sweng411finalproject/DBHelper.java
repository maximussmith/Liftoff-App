package com.example.sweng411finalproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sweng411finalproject.dataObjects.RunningExercise;
import com.example.sweng411finalproject.dataObjects.SwimmingExercise;
import com.example.sweng411finalproject.dataObjects.WalkingExercise;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";


    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table walking(username TEXT primary key, distance INT, stepsTaken INT, duration INT, avgHeartRate INT)");
        MyDB.execSQL("create Table running(username TEXT primary key, distance INT, stepsTaken INT, duration INT, avgHeartRate INT )");
        MyDB.execSQL("create Table swimming(username TEXT primary key, distance INT, duration INT, avgHeartRate INT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists walking");
        MyDB.execSQL("drop Table if exists running");
        MyDB.execSQL("drop Table if exists swimming");

    }

    public Boolean insertUserPass(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);

        return result != -1;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        return cursor.getCount() > 0;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        return cursor.getCount() > 0;
    }


    public Boolean insertWalkingWorkout(WalkingExercise workout, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("distance", workout.getDistance());
        contentValues.put("stepsTaken", workout.getStepsTaken());
        contentValues.put("duration", workout.getDuration());
        contentValues.put("avgHeartRate", workout.getAvgHeartRate());

        long result = MyDB.insert("walking", null, contentValues);
        return result != -1;
    }


    public ArrayList<WalkingExercise> selectWalkingWorkouts(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ArrayList<WalkingExercise> workouts = new ArrayList<WalkingExercise>();
        Cursor cursor = MyDB.rawQuery("Select * from walking where username = ?", new String[] {username});
        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    WalkingExercise exercise = new WalkingExercise(cursor.getInt(cursor.getColumnIndexOrThrow("distance")), cursor.getInt(cursor.getColumnIndexOrThrow("stepsTaken")), cursor.getInt(cursor.getColumnIndexOrThrow("duration")), cursor.getInt(cursor.getColumnIndexOrThrow("avgHeartRate")));
                    workouts.add(exercise);
                }while(cursor.moveToNext());
            }
        }
        return workouts;
    }

    public ArrayList<RunningExercise> selectRunningWorkouts(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ArrayList<RunningExercise> workouts = new ArrayList<RunningExercise>();
        Cursor cursor = MyDB.rawQuery("Select * from running where username = ?", new String[] {username});

        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    RunningExercise exercise = new RunningExercise(cursor.getInt(cursor.getColumnIndexOrThrow("distance")), cursor.getInt(cursor.getColumnIndexOrThrow("stepsTaken")), cursor.getInt(cursor.getColumnIndexOrThrow("duration")), cursor.getInt(cursor.getColumnIndexOrThrow("avgHeartRate")));
                    workouts.add(exercise);
                } while(cursor.moveToNext());

            }
        }

        return workouts;
    }

    public Boolean insertRunningWorkout(RunningExercise exercise, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("distance", exercise.getDistance());
        contentValues.put("stepsTaken", exercise.getStepsTaken());
        contentValues.put("duration", exercise.getDuration());
        contentValues.put("avgHeartRate", exercise.getAvgHeartRate());

        long result = MyDB.insert("running", null, contentValues);
        return result != -1;
    }

    public ArrayList<SwimmingExercise> selectSwimmingWorkouts(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ArrayList<SwimmingExercise> workouts = new ArrayList<SwimmingExercise>();
        Cursor cursor = MyDB.rawQuery("Select * from swimming where username = ?", new String[]{username});

        if (cursor != null){
            if(cursor.moveToFirst()){
                do {
                    SwimmingExercise exercise = new SwimmingExercise(cursor.getInt(cursor.getColumnIndexOrThrow("distance")), cursor.getInt(cursor.getColumnIndexOrThrow("duration")), cursor.getInt(cursor.getColumnIndexOrThrow("avgHeartRate")));
                    workouts.add(exercise);
                }while (cursor.moveToNext());
            }
        }
        return workouts;
    }

    public Boolean insertSwimmingWorkout(SwimmingExercise exercise, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("distance", exercise.getDistance());
        contentValues.put("duration", exercise.getDuration());
        contentValues.put("avgHeartRate", exercise.getAvgHeartRate());

        long result = MyDB.insert("swimming", null, contentValues);
        return result != -1;
    }


}
