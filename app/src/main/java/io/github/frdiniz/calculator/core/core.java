package io.github.frdiniz.calculator.core;

public class core {
    // start cache memory
    private Double cacheNumber;
    private Character cacheOperator;
    public Double result;

    // getters and setters

    public Double getCache () {
        return this.cacheNumber;
    }

    public void setCache (Double number, Character operator) {
        this.cacheNumber = number;
        this.cacheOperator = operator;
    }

    public Double getResult () {
        return this.result;
    }

    // core methods

    public String removeLastChar (String value) {
        return value.substring(0, value.length() -1);
    }

    public String negateNumber (String value) {

        Double invert = Double.parseDouble(value);

        //invert, negative or positive
        invert = invert > 0 ? invert * -1 : Math.abs(invert);

        return String.valueOf(invert);
    }

    public Double calculate(String displayValue) {
        Double currentNumber = Double.parseDouble(displayValue);

        switch (cacheOperator) {
            case '+':
                this.result = cacheNumber + currentNumber;
                break;
            case '-':
                this.result = cacheNumber - currentNumber;
                break;
            case 'x':
                this.result = cacheNumber * currentNumber;
                break;
            case '÷':
                this.result = cacheNumber / currentNumber;
                break;
            case '%':
                this.result = cacheNumber % currentNumber;
                break;
        }

        return this.result;
    }

    public void clearCache() {
        this.cacheNumber = null;
        this.cacheOperator = null;
    }
}
