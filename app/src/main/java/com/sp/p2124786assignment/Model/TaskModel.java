package com.sp.p2124786assignment.Model;

import android.graphics.Bitmap;

public class TaskModel {
    private int id;
    private String taskName;
    private String taskPoints;
    private String taskDesc;
    private Bitmap taskPic;
    private double lon;
    private double lat;

    public TaskModel(int id, String taskName, String taskPoints, String taskDesc, Bitmap taskPic, double taskLat, double taskLon) {
        this.id = id;
        this.taskName = taskName;
        this.taskPoints = taskPoints;
        this.taskDesc = taskDesc;
        this.taskPic = taskPic;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskPoints() {
        return taskPoints;
    }

    public void setTaskPoints(String taskPoints) {
        this.taskPoints = taskPoints;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Bitmap getTaskPic() {
        return taskPic;
    }

    public void setTaskPic(Bitmap taskPic) {
        this.taskPic = taskPic;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}



