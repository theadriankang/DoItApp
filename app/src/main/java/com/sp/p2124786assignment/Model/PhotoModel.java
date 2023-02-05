package com.sp.p2124786assignment.Model;

import android.graphics.Bitmap;

public class PhotoModel {

    private Bitmap photo;

    public PhotoModel(Bitmap photo) {
        this.photo = photo;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
