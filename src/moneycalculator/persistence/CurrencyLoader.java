package moneycalculator.persistence;

import moneycalculator.model.Currency;

public interface CurrencyLoader {
    Currency[] load();
    
}
