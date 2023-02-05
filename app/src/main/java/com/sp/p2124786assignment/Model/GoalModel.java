package com.sp.p2124786assignment.Model;

import android.graphics.Bitmap;

public class GoalModel {

    private int id;
    private String goalName;
    private String goalPoints;
    private Bitmap goalImage;

    public GoalModel(int id, String goalName, String goalPoints, Bitmap goalImage) {
        this.id = id;
        this.goalName = goalName;
        this.goalPoints = goalPoints;
        this.goalImage = goalImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getGoalPoints() {
        return goalPoints;
    }

    public void setGoalPoints(String goalPoints) {
        this.goalPoints = goalPoints;
    }

    public Bitmap getGoalImage() {
        return goalImage;
    }

    public void setGoalImage(Bitmap goalImage) {
        this.goalImage = goalImage;
    }
}
