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

public class MainActivity4 extends AppCompatActivity {

    private EditText bmi;
    private EditText weight;
    private TextView result;
    private TextView function;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        bmi = (EditText) findViewById(R.id.bmilol);
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

                        function.setText(
                                "10-16BMI --> 50-55kg" +
                                "\n16-18BMI --> 55-65kg" +
                                "\n18-22BMI --> 65-80kg" +
                                "\n22-26BMI --> 80-90kg");


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
* Methode zur Berechnung von Idealem Gewicht
*/

    public void calculateIdeal(View v) {
        String bmiStr = bmi.getText().toString();
        String weightStr = weight.getText().toString();

        if (bmiStr != null && !"".equals(bmiStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float bmiValue = Float.parseFloat(bmiStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / bmiValue ;

            displayBMI(bmi);
        }
    }

/**
* Methode zur Darstellung der Daten
 * inklusive Eingrenzungsbereich
*/

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 3.4f) <= 0) {
            bmiLabel = getString(R.string.klasse1);
        } else if (Float.compare(bmi, 3.4f) > 0  &&  Float.compare(bmi, 3.6f) <= 0) {
            bmiLabel = getString(R.string.klasse2);
        } else if (Float.compare(bmi, 3.6f) > 0  &&  Float.compare(bmi, 3.7f) <= 0) {
            bmiLabel = getString(R.string.klasse3);
        } else if (Float.compare(bmi, 3.7f) > 0  &&  Float.compare(bmi, 3.8f) <= 0) {
            bmiLabel = getString(R.string.klasse4);
        } else {
            bmiLabel = getString(R.string.klasse4);
        }

        result.setText(bmiLabel);
    }
}