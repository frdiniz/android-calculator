package io.github.frdiniz.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // init display
    private TextView display;
    // init handles
    private String newValue;
    private String displayValue;
    // init cache
    private Double cacheNumber;
    private Character cacheOperator;
    // init controllers
    private boolean cached = false;
    private boolean firstClick = true;
    private boolean newNumber = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.result);

        //clear all on long click
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                reset();
                display.setText(null);
                return true;
            }
        });
    }

    public void onClickNumber(View view) {
        Button number = (Button) findViewById(view.getId());
        displayValue = display.getText().toString();
        newValue = number.getText().toString();

        if (!newNumber) {
            newValue = displayValue + newValue;
        }

        display.setText(newValue);
        newNumber = false;
    }

    public void onClickClear(View view) {
        displayValue = display.getText().toString();

        // remove last char
        if (displayValue.length() > 0) {
            newValue = displayValue.substring(0, displayValue.length() - 1);
        }

        display.setText(newValue);
    }

    public void onClickNegate(View view) {
        displayValue = display.getText().toString();

        if (displayValue.length() > 0) {
            Double invert = Double.parseDouble(displayValue);

            // invert, negative or positive
            invert = invert > 0 ? invert * -1 : Math.abs(invert);

            display.setText(String.valueOf(invert));
        }
    }

    public void onClickOperator(View view) {
        displayValue = display.getText().toString();

        if (firstClick) {
            // set cache
            cacheNumber = Double.parseDouble(displayValue);
            Button operator = (Button) findViewById(view.getId());
            cacheOperator = operator.getText().toString().charAt(0);
            cached = true;

            // controller for cascade operations
            firstClick = false;
            //It allows a new value
            newNumber = true;

        } else {
            // set cache
            cacheNumber = compute();
            Button operator = (Button) findViewById(view.getId());
            cacheOperator = operator.getText().toString().charAt(0);

            display.setText(String.valueOf(cacheNumber));
            //It allows a new value
            newNumber = true;
        }
    }

    public void onClickResult(View view) {
        if (cached) {
            Double temp = compute();
            display.setText(String.valueOf(temp));
            reset();
        }
    }

    public void reset() {
        // clean cache
        cacheNumber = null;
        cacheOperator = null;
        cached = false;
        // controller for cascade operations
        firstClick = true;
        //It allows a new value
        newNumber = true;
    }

    public Double compute(){
        displayValue = display.getText().toString();
        Double currentNumber = Double.parseDouble(displayValue);
        Double result = 0.0;

        switch (cacheOperator) {
            case '+':
                result = cacheNumber + currentNumber;
                break;
            case '-':
                result = cacheNumber - currentNumber;
                break;
            case 'x':
                result = cacheNumber * currentNumber;
                break;
            case '÷':
                result = cacheNumber / currentNumber;
                break;
            case '%':
                result = cacheNumber % currentNumber;
                break;
        }
        //It allows a new value
        newNumber = true;

        return result;
    }
}
