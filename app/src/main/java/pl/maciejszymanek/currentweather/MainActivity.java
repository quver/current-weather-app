package pl.maciejszymanek.currentweather;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    TextView text;

    @RestService
    WeatherService weatherService;

    CurrentLocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener = new CurrentLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Click(R.id.button)
    void updateWeather() {

        if (locationListener.lat == 0 && locationListener.lon == 0) {
            Toast.makeText(getApplicationContext(), "GPS nie działa", Toast.LENGTH_SHORT).show();
        } else {
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                double currentTemp = weatherService.getWeather(locationListener.lat, locationListener.lon).getMain().getTemp() - 273.16;

                text.setText(String.format("%1$,.2f", currentTemp));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Wystąpił błąd", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

}
