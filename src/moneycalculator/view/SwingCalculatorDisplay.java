package moneycalculator.ui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import moneycalculator.model.Money;

public class SwingCalculatorDisplay extends JPanel implements CalculatorDisplay {

    private Money money;

    @Override
    public void display(Money money) {
        this.money = money;
        this.removeAll();
        this.add(amount());
        this.add(currency());
        this.updateUI();
    }

    private Component amount() {
        return new JLabel(String.valueOf(money.getAmount()));
    }

    private Component currency() {
        return new JLabel(String.valueOf(money.getCurrency().getCode()));
    }
    
    
    
}
