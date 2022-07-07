package com.example.healthcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Autor:@Leon Lopetrone
 * Version: 1.0
 * Date: 07.07.2022
 */

public class MainActivity3 extends AppCompatActivity {
    private EditText bmi;
    private EditText age;
    private TextView result;
    private TextView function;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bmi = (EditText) findViewById(R.id.bmilol);
        age = (EditText) findViewById(R.id.age);
        result = (TextView) findViewById(R.id.result);
        function = (TextView) findViewById(R.id.function);

/**
* Sensor Manager Integration
*/

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent!=null){
                    float x_accl = sensorEvent.values[0];
                    float y_accl = sensorEvent.values[1];
                    float z_accl = sensorEvent.values[2];

                    if (x_accl > 2 ||
                            x_accl < -2 ||
                            y_accl > 12 ||
                            y_accl < -12 ||
                            z_accl > 2 ||
                            z_accl < -2){

                        function.setText("(1.20 x BMI) + (0.23 x Alter) - 5.4");


                    }
                    else {
                        //passiert nichts
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sensorEventListener, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);

    }

/**
* Methode zur Berechnung von Body Fat %
*/

    public void calculateFat(View v) {
        String ageStr = age.getText().toString();
        String bmiStr = bmi.getText().toString();

        if (ageStr != null && !"".equals(ageStr)
                && bmiStr != null  &&  !"".equals(bmiStr)) {
            double ageValue = Float.parseFloat(ageStr);
            double bmiValue = Float.parseFloat(bmiStr);

            double fat = (1.2 * bmiValue) + (0.23 * ageValue) -5.4 ;

            displayFat((float) fat);
        }
    }

/**
* Methode zur Darstellung der Daten
*/

    private void displayFat(float fat) {
        String fatLabel = "";
        fatLabel = fat + "%" + "\n\n" + fatLabel;
        result.setText(fatLabel);
    }
}