package com.example.lifestyleapp;

import android.graphics.Bitmap;

public class UserProfile {

    private String Name;
    private int Age;
    private String City;
    private String Country;
    private int Height; // in inches
    private int Weight; // in lbs
    private boolean Gender; // M=true, F=false
    private Bitmap ProfilePicture;
    private double Goal; // In lbs per week
    private double ActivityLevel; // Sedentary (1.53), Moderate (1.76), Active (2.25)

    // Getters
    public Bitmap getProfilePicture() { return ProfilePicture; }
    public double getActivityLevel() { return ActivityLevel; }
    public double getGoal() { return Goal; }
    public int getAge() { return Age; }
    public int getHeight() { return Height; }
    public int getWeight() { return Weight; }
    public String getCity() { return City; }
    public String getCountry() { return Country; }
    public String getName() { return Name; }
    public boolean getGender() { return Gender;}

    // Setters
    public void setAge(int age) { Age = age; }
    public void setCity(String city) { City = city; }
    public void setCountry(String country) { Country = country; }
    public void setGender(boolean gender) { Gender = gender; }
    public void setGoal(double goal) { Goal = goal; }
    public void setHeight(int height) { Height = height; }
    public void setName(String name) { Name = name; }
    public void setProfilePicture(Bitmap profilePicture) { ProfilePicture = profilePicture; }
    public void setWeight(int weight) { Weight = weight; }

    // Activity level isn't as simple as the other setters.
    // Since its categories are descriptive and not numerical
    // it can't be directly set from the user's input.
    public void setActivityLevel(String activityLevel) {
        if(activityLevel == "Sedentary") {
            ActivityLevel = 1.53;
        }
        else if (activityLevel == "Moderate") {
            ActivityLevel = 1.76;
        }
        else if (activityLevel == "Active") {
            ActivityLevel = 2.25;
        }
    }
    // This version of the setter can be called if the profile is loaded from file.
    public void setActivityLevel(double activityLevel) {
        ActivityLevel = activityLevel;
    }

}
