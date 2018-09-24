package com.example.lifestyleapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    UserProfile userProfile;
    String profileName = "user_profile.txt";
    String pictureName = "thumbnail.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check to see if it's a tablet or not

        // check if there's a saved file or not. If not, bring up the edit_profile page.
        // If there is, bring up the menu page.
        File file = new File(getApplicationContext().getFilesDir(), profileName);
        if(file.exists()) {
            // Load the user profile from the file and then bring up the menu
        }
        else {  // Bring up the edit profile page
            EditProfileFragment editProfileFragment = new EditProfileFragment();
            FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
            ftrans.replace(R.id.fl_frag_masterlist_container_phone, editProfileFragment, "Edit_Profile_Fragment");
            ftrans.commit();
        }

    }

    public boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void saveProfileToFile() {

        // Open a file and write to it
        File file = new File(getApplicationContext().getFilesDir(), profileName);
        if(file.exists()) { file.delete(); }
        try {
            FileOutputStream out = openFileOutput(profileName, Context.MODE_PRIVATE);
            DataOutputStream d_out = new DataOutputStream(out);
            d_out.writeChars(userProfile.getName());
            d_out.writeInt(userProfile.getAge());
            d_out.writeChars(userProfile.getCity());
            d_out.writeChars(userProfile.getCountry());
            d_out.writeInt(userProfile.getHeight());
            d_out.writeInt(userProfile.getWeight());
            d_out.writeBoolean(userProfile.getGender());
            d_out.writeDouble(userProfile.getGoal());
            d_out.writeDouble(userProfile.getActivityLevel());
            d_out.flush();
            d_out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveImageToFile() {
        // Open a file and write to it
        File file = new File(getApplicationContext().getFilesDir(), pictureName);
        if(file.exists()) { file.delete(); }
        try {
            FileOutputStream out = openFileOutput(pictureName, Context.MODE_PRIVATE);
            userProfile.getProfilePicture().compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create an action bar
}
