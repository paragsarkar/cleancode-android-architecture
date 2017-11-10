package com.self.core.locationHelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationService extends Service implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LOCATION_UPDATE";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 60;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(TAG, "on  start command");

        if (mLocationRequest == null) {
            createLocationRequest();
        }
        context = this;
        return START_STICKY;

    }

    protected synchronized void buildGoogleApiClient() {

        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.e(TAG, "connection suspended");

    }

    @Override
    public void onLocationChanged(Location location) {

        updateLocation(location);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e(TAG, "connection failed");

    }

    protected void startLocationUpdates() {

        try {
            PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    public void updateLocation(final Location mLocation) {

        Log.e(TAG, "Latitude: " + mLocation.getLatitude());
        Log.e(TAG, "Longitude: " + mLocation.getLongitude());

        Intent i = new Intent("latLng");
        i.putExtra("latitude", String.valueOf(mLocation.getLatitude()));
        i.putExtra("longitude", String.valueOf(mLocation.getLongitude()));
        sendBroadcast(i);
        stopSelf();

    }

    @Override
    public void onDestroy() {

        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        Log.e(TAG, "location update stopped");

        super.onDestroy();

    }


}
