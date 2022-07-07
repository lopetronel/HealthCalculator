package com.example.healthcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button bmibtn;
    private Button fatbtn;
    private Button weightbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmibtn = findViewById(R.id.bmicalc);
        bmibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBmiCalculator();
            }
        });

        weightbtn = findViewById(R.id.idealcalc);
        weightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIdealCalculator();
            }
        });

        fatbtn = findViewById(R.id.fatcalc);
        fatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFatCalculator();
            }
        });

    }
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