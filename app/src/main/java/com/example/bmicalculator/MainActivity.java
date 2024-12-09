package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private RadioButton male;
    private RadioButton female;
    private Button calc;
    private EditText feet;
    private EditText inch;
    private EditText weight;
    private TextView rest;
    private EditText age;

    private TextView rest1;
    double bmic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        findviews();
        mybuttonlistener();


    }

    private void findviews() {
        male = findViewById(R.id.r_b_male);
        female = findViewById(R.id.r_b_female);
        calc = findViewById(R.id.calc);
        age = findViewById(R.id.age);
        feet = findViewById(R.id.feet);
        inch = findViewById(R.id.inch);
        weight = findViewById(R.id.weight);
        rest = findViewById(R.id.rest);
        rest1 = findViewById(R.id.rest1);


    }

    private void mybuttonlistener() {
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calbmi();
                int fage = Integer.parseInt(age.getText().toString());  // Extracts the text as String then to int

                if (fage >= 18) {
                    displaybmi(bmic);
                } else {
                    displayguid(bmic);
                }
            }
        });
    }



    private void calbmi() {
//        String striage = age.getText().toString();
//        String striwe = weight.getText().toString();
//        String strife = feet.getText().toString();
//        String striin = inch.getText().toString();
//        rest.setText("age: " + striage + " weight: " + striwe + " Height: " + strife + "." + striin);


        double fwe = Double.parseDouble(weight.getText().toString());
        int ffeet = Integer.parseInt(feet.getText().toString());
        int finch = Integer.parseInt(inch.getText().toString());

        int totin = (ffeet * 12) + finch;
        double totme = totin * 0.0254;
        bmic = fwe / (totme * totme);
        // String bmics = String.valueOf(bmic);


    }

    private void displaybmi(double bmic) {
        DecimalFormat dm = new DecimalFormat("0.00");
        String bmics = dm.format(bmic);


        if (bmic < 18.5) {

            rest1.setText("You are Underweight.");

        } else if (bmic >= 18.5 && bmic <= 24.9) {
            rest1.setText("Your Weight is Normal.");

        } else if (bmic > 24.9 && bmic <= 29.92) {
            rest1.setText("You are Overweight.");

        } else if (bmic >= 30) {
            rest1.setText("Obesity.");

        }
        rest.setText("BMI: " + bmics);



    }


    private void displayguid(double bmic) {
        DecimalFormat dm = new DecimalFormat("0.00");
        String bmics = dm.format(bmic);

        if(male.isChecked()){
            rest1.setText("As you are a boy under 18, refer to your doctor for healthy range.");
        } else if (female.isChecked()) {
            rest1.setText("As you are a girl under 18, refer to your doctor for healthy range.");
        }
        else {
            rest1.setText("As you are  under 18 non-binary, refer to your doctor for healthy range.");
        }
        rest.setText("BMI: " + bmics);
    }


}