package com.example.minhl.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private WeatherService weatherService;
    private boolean binded = false;
    private EditText editText;
    private Button button;
    ServiceConnection weatherServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WeatherService.LocalWeatherBinder binder = (WeatherService.LocalWeatherBinder) service;
            weatherService = binder.getService();
            binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binded = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edt_city);
        button = (Button) findViewById(R.id.btn_weather);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, WeatherService.class);
        bindService(intent, weatherServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Tắt App", Toast.LENGTH_SHORT).show();
        if (binded) {
            unbindService(weatherServiceConnection);
            binded = false;
        }
    }

    public void showWeather(View view) {
        String location = editText.getText().toString();
        Toast.makeText(this, location + " có thời tiết là " + weatherService.getWeatherToday() + "", Toast.LENGTH_SHORT).show();
    }

}
