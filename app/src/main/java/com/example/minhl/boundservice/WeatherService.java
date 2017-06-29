package com.example.minhl.boundservice;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherService extends Service {
    private static final String TAG = "Weather Sevice";
    //    private static final Map<String, String> weatherData = new HashMap<String, String>();
    private final IBinder binder = new LocalWeatherBinder();

    public class LocalWeatherBinder extends Binder {
        public WeatherService getService() {
            return WeatherService.this;
        }
    }

    public WeatherService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "onBind: ");
        return binder;
    }
    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service bị destroy :D ", Toast.LENGTH_SHORT).show();
    }

    public String getWeatherToday() {
        String[] weathers = new String[]{"Rainy", "Hot", "Cool", "Warm", "Snowy"};
        int i = new Random().nextInt(5);
        return weathers[i];
    }
}
