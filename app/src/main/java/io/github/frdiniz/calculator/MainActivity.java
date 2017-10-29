package io.github.frdiniz.calculator;

import io.github.frdiniz.calculator.core.Core;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Core core;
    private TextView display;
    private String displayValue;
    private String newValue;
    // start controllers
    private boolean cached = false;
    private boolean firstClick = true;
    private boolean newNumber = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.result);
        core = new Core();

        //clear all on long click
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                core.clearCache();
                resetControllers();
                display.setText(null);
                return true;
            }
        });
    }

    public void onClickNumber(View view) {
        Button number = (Button) findViewById(view.getId());
        displayValue = display.getText().toString();
        newValue = number.getText().toString();
        // number not complete
        if (!newNumber) {
            newValue = displayValue + newValue;
        }

        display.setText(newValue);
        newNumber = false;
    }

    public void onClickClear(View view) {
        displayValue = display.getText().toString();

        if (displayValue.length() > 0) {

            newValue = core.removeLastChar(displayValue);
            display.setText(newValue);
        }
    }

    public void onClickNegate(View view) {
        displayValue = display.getText().toString();

        if (displayValue.length() > 0) {

            newValue = core.negateNumber(displayValue);
            display.setText(newValue);
        }
    }

    public void onClickOperator(View view) {
        Button operator = (Button) findViewById(view.getId());
        displayValue = display.getText().toString();
        String newOperator;

        if (firstClick) {
            newOperator = operator.getText().toString();
            core.setCache(displayValue, newOperator);
            cached = true;
            // controller for cascade operations
            firstClick = false;
            //It allows a new number
            newNumber = true;

        } else {
            // calculate value of the previous operation
            core.calculate(displayValue);
            newValue = String.valueOf(core.getResult());
            newOperator = operator.getText().toString();
            core.setCache(newValue, newOperator);
            // show value of the previous operation
            display.setText(String.valueOf(core.getResult()));
            //It allows a new number
            newNumber = true;
        }
    }

    public void onClickResult(View view) {
        if (cached) {
            displayValue = display.getText().toString();
            // calculate the current operation
            core.calculate(displayValue);
            //It allows a new number
            newNumber = true;
            display.setText(String.valueOf(core.getResult()));
            //clear all
            resetControllers();
            core.clearCache();
        }
    }

    public void resetControllers() {
        // controller for cache
        cached = false;
        // controller for cascade operations
        firstClick = true;
        //It allows a new number
        newNumber = true;
    }
}
