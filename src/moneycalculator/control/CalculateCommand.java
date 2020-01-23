package moneycalculator.control;

import moneycalculator.view.Calculator;
import moneycalculator.model.Currency;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.ui.CalculatorDisplay;
import moneycalculator.ui.MoneyDialog;

public class CalculateCommand implements Command {
    private final CalculatorDisplay display;
    private final MoneyDialog dialog;
    private final ExchangeRateLoader loader;
    private Calculator calculator;
    private Currency to;

    public CalculateCommand(CalculatorDisplay display, MoneyDialog dialog, ExchangeRateLoader loader) {
        this.display = display;
        this.dialog = dialog;
        this.loader = loader;
    }
    @Override
    public void execute(Currency to){
        this.to = to;
        this.execute();
    }
    @Override
    public void execute() {
        calculator = new Calculator (dialog.get().getAmount(), loader.load(dialog.get().getCurrency(), to),to);
        display.display((calculator.calculate()));
    }

    @Override
    public String name() {
        return "Calculate";
    }
    
}
