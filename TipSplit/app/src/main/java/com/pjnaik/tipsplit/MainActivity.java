package com.pjnaik.tipsplit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText billAmount;
    private EditText individual;
    private EditText totalIndividualValue;
    private EditText totalTipPayVariable;
    private EditText totalWithTipAmountVariable;
    private double valueaftertip;
    private static final String TAG = "MainActivityTag";
    private double multiplier;
    public static double dn1;
    public static double dn2;
    public EditText overagevar;
    public RadioGroup radiogroupX;
    public RadioButton tiponetwo;
    private RadioGroup RadioGroupvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.billtotalvalue);
        individual = findViewById(R.id.numberofpeopleanswer);
        totalIndividualValue = findViewById(R.id.totalperpersonvalue);
        totalTipPayVariable = findViewById(R.id.tipamountvalue);
        totalWithTipAmountVariable = findViewById(R.id.totalwithtipvalue);
        overagevar = findViewById(R.id.overagevalue);
    }

    public void doCalculation(View v) {

        String n2 = individual.getText().toString();
        if (n2.trim().isEmpty())
            return;

        dn2 = Double.parseDouble(n2);

        double finalvalue = ( dn1 + multiplier * dn1 ) / dn2;
        double roundOff = Math.ceil(finalvalue * 100) / 100;
        totalIndividualValue.setText(String.format("$%.2f", roundOff));

        double totaloverage = ( roundOff - finalvalue ) * dn2;
        overagevar.setText(String.format("$%.2f", totaloverage));

    }

    public void radioClickActivity(View v) {

        String n1 = billAmount.getText().toString();
        if (n1.trim().isEmpty()) {
            RadioGroupvar = (RadioGroup) findViewById(R.id.radiogroupX);
            RadioGroupvar.clearCheck();
            return;
        }

        if (v.getId() == R.id.tiponetwo)
            multiplier = 0.12;
        else if (v.getId() == R.id.tiponefive)
            multiplier = 0.15;
        else if (v.getId() == R.id.tiponeeight)
            multiplier = 0.18;
        else if (v.getId() == R.id.tiptwozero)
            multiplier = 0.20;

        dn1 = Double.parseDouble(n1);

        totalTipPayVariable.setText(String.format("$%.2f", multiplier * dn1 ));
        totalWithTipAmountVariable.setText(String.format("$%.2f", dn1 + multiplier * dn1));
    }

    public void clickClear(View v) {
        Log.d(TAG, "I am here");
        billAmount.getText().clear();
        individual.getText().clear();
        totalIndividualValue.getText().clear();
        totalTipPayVariable.getText().clear();
        totalWithTipAmountVariable.getText().clear();
        overagevar.getText().clear();
        RadioGroupvar = (RadioGroup) findViewById(R.id.radiogroupX);
        RadioGroupvar.clearCheck();
    }

}