package pl.maciejszymanek.currentweather;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class CurrentLocationListener implements LocationListener {

    public double lat;
    public double lon;

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        System.out.println("Latitude = " + lat + " Longitude = " + lon );

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}