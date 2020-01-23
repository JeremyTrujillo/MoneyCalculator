package moneycalculator.view;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;

public class Calculator {
    private final double amount;
    private final ExchangeRate rate;
    private final Currency to;

    public Calculator(double amount, ExchangeRate rate, Currency to) {
        this.amount = amount;   
        this.rate = rate;
        this.to = to;
    }
    public Money calculate(){
        return new Money (amount*rate.getRate(),to);
    }
}
