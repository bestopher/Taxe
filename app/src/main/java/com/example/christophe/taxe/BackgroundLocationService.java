package com.example.christophe.taxe;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by christophe on 11/1/17.
 */

public class BackgroundLocationService extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = BackgroundLocationService.class.getSimpleName();
    private String android_id = null;
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 30000;
    private static final float LOCATION_DISTANCE = 0;
    private double currentLat, currentLng;
    private SharedPreferences pref;
    private String driverId;
    private GoogleApiClient mGoogleApiClient;
    // A request to connect to Location Services
    private LocationRequest mLocationRequest;

    private LocationListener locationListener;
    private static final int REQUEST_LOCATION = 8;

    private class LocationListener implements
            com.google.android.gms.location.LocationListener {

        public LocationListener() {
        }

        @Override
        public void onLocationChanged(Location location) {
            String _L_ = TAG + "|onLocationChanged: ";
            //Flog.begin(_L_);
            //Flog.d(_L_, "" + location);
            currentLat = location.getLatitude();
            currentLng = location.getLongitude();
            android_id = Utils.getAndroidId(getBaseContext());
            String extras = "GoogleApi" + "_Lat_" + currentLat + "_Long_" + currentLng;
            //Files.createFlyFile(android_id, ".geo", extras);
            //Flog.d(_L_, extras);
            //Flog.end(_L_);

        }

    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Flog.d(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        boolean stopService = false;
        if (intent != null)
            stopService = intent.getBooleanExtra("stopservice", false);

       // Flog.d(TAG, "stopservice " + stopService);

        locationListener = new LocationListener();
        if (stopService) {
            stopLocationUpdates();
         //   Flog.i(TAG, "erreur de connexion, impossible d'avoir geo|");
        } else {
            if (!mGoogleApiClient.isConnected())
                mGoogleApiClient.connect();
        }

        return START_STICKY;
    }

    @Override
    public void onCreate() {
      //  Flog.d(TAG, "onCreate");
        pref = getSharedPreferences("driver_app", MODE_PRIVATE);
        driverId = pref.getString("driver_id", "");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
    }


    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, locationListener);

        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }


    @Override
    public void onConnected(Bundle arg0) {
        // TODO Auto-generated method stub
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//PRIORITY_BALANCED_POWER_ACCURACY
        mLocationRequest.setInterval(15000);
        mLocationRequest.setFastestInterval(15000);
        startLocationUpates();
    }

    private Context context;

    private void startLocationUpates() {
       /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Flog.d(TAG + "|onRequestPermissionsResult |", "Permission was denied or request was cancelled");
            String extras = "_GoogleApi";
            Files.createFlyFile(android_id, "_RequestFailed_", extras);
            ActivityCompat.requestPermissions((Activity) context.getApplicationContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
            //return;
        } else {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, locationListener);
        }*/
        //modifié

        onRequestPermissionsResult(8, new String[]{"PERMISSION_GRANTED", "PERMISSION_NOT_GRANTED"}, new int[]{0,-1});
    }


    //ajouté
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            switch (requestCode) {
                case 8: {
                    // grantResults[0] = -1
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            //return;
                        }
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, locationListener);
                       // Flog.d(TAG+"|onRequestPermissionsResult |GoogleApi_PERMISSION_GRANTED", permissions[0]);

                    } else {// Permission was denied or request was cancelled
                        String extras = "_GoogleApi";
                        //MediaStore.Files.createFlyFile(android_id, "_RequestFailed_grantResult="+ grantResults[1], extras);

                        //Flog.d(TAG+"|onRequestPermissionsResult |GoogleApi_PERMISSION_NOT_GRANTED", permissions[1]);

                    }

                }
                return;
            }

        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        //Flog.begin(TAG+ "|onConnectionSuspended");
        // TODO Auto-generated method stub
        //Flog.d(TAG, "onConnectionSuspended, impossible d'avoir geo|"+arg0);
        String extras = "_onConnectionSuspended_GoogleAPI_null_";
        //  startService(new Intent(this, LocationUpdates.class));
        //Files.createFlyFile(android_id, "null", extras);
        //Flog.end(TAG+ "|onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        //Flog.begin(TAG + "|onConnectionFailed");
        // If no resolution is available, display an error dialog
        // TODO Auto-generated method stub
       // Flog.d(TAG, "onConnectionFailed, impossible d'avoir geo|"+arg0);
        String extras = "_onConnectionFailed_GoogleAPI_null_";
        // startService(new Intent(this, LocationUpdates.class));
        //Si erreur, se connecter sur google play store
        //Files.createFlyFile(android_id, "null_"+arg0, extras);

        //Flog.end(TAG + "|onConnectionFailed");

    }

    @Override
    public void onDestroy() {
       // Flog.d(TAG, "onDestroy");
        super.onDestroy();
        stopLocationUpdates();
    }

}
