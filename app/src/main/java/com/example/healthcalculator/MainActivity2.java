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

public class MainActivity2 extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView result;
    private TextView function;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        height = (EditText) findViewById(R.id.bmilol);
        weight = (EditText) findViewById(R.id.age);
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

                        function.setText("Körpergewicht geteilt durch Körpergrösse zum Quadrat");


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
* Methode zur Berechnung von BMI
*/

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

/**
* Methode zur Darstellung der Daten
* inklusive Eingrenzungsbereich
*/

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.sehr_stark_untergewichtig);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.stark_untergewichtig);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.untergewichtig);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.uebergewichtig);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.uebergewichtsklasse_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.uebergewichtsklasse_ii);
        } else {
            bmiLabel = getString(R.string.uebergewichtsklasse_iii);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }
}