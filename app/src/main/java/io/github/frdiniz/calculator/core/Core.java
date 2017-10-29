package io.github.frdiniz.calculator.core;

public class Core {
    private Double cacheNumber;
    private Character cacheOperator;
    private Double result;

    public void setCache (String number, String operator) {
        this.cacheNumber = Double.parseDouble(number);
        this.cacheOperator = operator.charAt(0);
    }

    public Double getResult () {
        return this.result;
    }

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
