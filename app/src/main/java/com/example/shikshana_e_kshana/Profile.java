package com.example.shikshana_e_kshana;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Profile {
    private String name;
    private String className;
    private String description;
    private byte[] imageBytes;

    public Profile(String name, String className, String description, byte[] imageBytes) {
        this.name = name;
        this.className = className;
        this.description = description;
        this.imageBytes = imageBytes;
    }

    // Getters
    public String getName() { return name; }
    public String getClassName() { return className; }
    public String getDescription() { return description; }
    public byte[] getImageBytes() { return imageBytes; }

    // Helper method to get Bitmap
    public Bitmap getImageBitmap() {
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}