package moneycalculator.control;

import moneycalculator.model.Currency;

public interface Command {
    public String name();
    public void execute();

    public void execute(Currency to);
    
}
