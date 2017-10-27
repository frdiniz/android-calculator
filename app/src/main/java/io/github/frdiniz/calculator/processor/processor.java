package io.github.frdiniz.calculator.processor;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class processor {

    // init cache
    private Double cacheNumber;
    private Character cacheOperator;

    public String removeLastChar (String value) {
        return value.substring(0, value.length() -1);
    }

    public String negateNumber (String value) {

        Double invert = Double.parseDouble(value);

        //invert, negative or positive
        invert = invert > 0 ? invert * -1 : Math.abs(invert);

        return String.valueOf(invert);
    }

    public void compute() {}

    public void reset() {}
}
