package moneycalculator;

import javax.swing.*;
import moneycalculator.control.CalculateCommand;
import moneycalculator.persistence.CurrencyLoader;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.persistence.FileCurrencyLoader;
import moneycalculator.persistence.FileExchangeRateLoader;

public class MoneyCalculator extends JFrame {

    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new FileCurrencyLoader("currencies");
        ExchangeRateLoader rateLoader = new FileExchangeRateLoader();

        MainFrame frame = new MainFrame(currencyLoader.load());
        frame.add(new CalculateCommand(frame.getCalculatorDisplay(), frame.getMoneyDialog(),rateLoader));
    }


}
