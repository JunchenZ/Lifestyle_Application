package com.example.lifestyleapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;



// I might have this moved into the MainActivity since the activity and its context are
// required for location services and checking for permissions.
public class FindHikes {

    private LocationManager mLocMgr;
    private double longitude;
    private double latitude;
    private String mSearchFor = "hikes";

    public void findHikes(Activity activity, UserProfile userProfile) {
        mLocMgr = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        Uri queryUri;
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            // Use the user's country and city to find hikes since no location is specified
            queryUri = Uri.parse("geo:0,0?q=" + Uri.encode(userProfile.getCity() + ", " +userProfile.getCountry() +
                    " " +  mSearchFor));
        }
        else {
            // Update the longitude and latitude variables with the device's coordinates
            mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

            // Construct the search for hikes from the device's coordinates
            queryUri = Uri.parse("geo:" + longitude + "," + latitude + "?q=" + mSearchFor);
        }
        // Implicit Intent to Maps App
        Intent mapsIntent = new Intent(Intent.ACTION_VIEW, queryUri);

        // If an activity exists for this intent start it
        if(mapsIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(mapsIntent);
        }

    }

    private  final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    };
}
