package com.example.healthcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Autor:@Leon Lopetrone
 * Version: 1.0
 * Date: 07.07.2022
 * Programm welches mit Hilfe von Formeln
 */

public class MainActivity extends AppCompatActivity {
    private Button bmibtn;
    private Button fatbtn;
    private Button weightbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/**
 * Weiterleitung auf die MainActivity2
 * (BMI Calculator)
 */

        bmibtn = findViewById(R.id.bmicalc);
        bmibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBmiCalculator();
            }
        });

/**
 * Weiterleitung auf die MainActivity4
 * (Ideal Weight Calculator)
 */

        weightbtn = findViewById(R.id.idealcalc);
        weightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIdealCalculator();
            }
        });

/**
 * Weiterleitung auf die MainActivity3
 * (Body Fat Calculator)
 */

        fatbtn = findViewById(R.id.fatcalc);
        fatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFatCalculator();
            }
        });

    }

/**
* Intent Methoden für alle 3 Buttons
* inklusive custom animationen (anim)
*/

        public void openBmiCalculator(){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        }

        public void openIdealCalculator() {
            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }

        public void openFatCalculator() {
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }


}