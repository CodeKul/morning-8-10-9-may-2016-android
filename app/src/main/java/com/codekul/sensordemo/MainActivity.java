package com.codekul.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSensor();
    }

    private final void initSensor(){

        SensorManager manager =
                (SensorManager) getSystemService(SENSOR_SERVICE);

        roomTemprature(manager);
    }

    private final void listAllSensors(SensorManager manager){

        List<Sensor> list =
                manager.getSensorList(Sensor.TYPE_ALL);

        final TextView textInfo =
                (TextView) findViewById(R.id.textInfo);
        for (Sensor s : list){
            textInfo.append("\n Name - "+s.getName());
        }
    }

    private final void lightSensorDemo(SensorManager manager){

        Sensor lightSensor =
                manager.getDefaultSensor(Sensor.TYPE_LIGHT);

        final TextView textInfo =
                (TextView) findViewById(R.id.textInfo);

        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float []arr = event.values;
                textInfo.setText(""+arr[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private final void proximitySensor(SensorManager manager){

        Sensor proximitySensor =
                manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        final TextView textInfo =
                (TextView) findViewById(R.id.textInfo);

        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float []arr = event.values;
                textInfo.setText(""+arr[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private final void rotationVectorSensor(SensorManager manager){

        Sensor rotationSensor =
                manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        final TextView textInfo =
                (TextView) findViewById(R.id.textInfo);

        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float []arr = event.values;
                textInfo.setText("X - "+arr[0] +"\n Y - "+arr[1] + "\n Z - "+arr[2] +"\n Angle - "+arr[3]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },rotationSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private final void roomTemprature(SensorManager manager){

        Sensor roomTempSensor =
                manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        final TextView textInfo =
                (TextView) findViewById(R.id.textInfo);

        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float []arr = event.values;
                textInfo.setText(""+arr[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },roomTempSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
