package moneycalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import moneycalculator.control.Command;
import moneycalculator.model.Currency;
import moneycalculator.ui.CalculatorDisplay;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.SwingCalculatorDisplay;
import moneycalculator.ui.SwingMoneyDialog;

public class MainFrame extends JFrame {
    private final Currency[] currencies;
    private final Map<String,Command> Commands = new HashMap<>();
    private MoneyDialog moneyDialog;
    private CalculatorDisplay calculatorDisplay;
    private Currency to;
   
    public MainFrame(Currency[] currencies) {
        this.currencies = currencies;
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        this.setSize(400, 180);
        this.setLocationRelativeTo(null);
        this.add(mainPanel(),BorderLayout.CENTER);
        //this.add(display(), BorderLayout.EAST);
        //this.add(dialog(), BorderLayout.EAST);
        JPanel toolbar = new JPanel();
        toolbar.add(button());
        this.add(toolbar,BorderLayout.SOUTH);
        this.setVisible(true);   
    }

    private Component display() {
        SwingCalculatorDisplay display = new SwingCalculatorDisplay();
        calculatorDisplay = display;
        return display;
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public CalculatorDisplay getCalculatorDisplay() {
        return calculatorDisplay;
    }

    private Component dialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog(currencies);
        moneyDialog = dialog;
        return dialog;
    }

     private Component toDialog() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("To:");
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(toCurrencyChanged());
        to = (Currency) combo.getSelectedItem();
        jPanel.add(label);
        jPanel.add(combo);
        return jPanel;
    }
     
    private Component button() {
        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Commands.get("Calculate").execute(to);
            }
        });
        return button;
    }

    public void add (Command command){
        Commands.put(command.name(), command);
    }

    private Component mainPanel() {
        JPanel panel = new JPanel();
        panel.add(dialog(),BorderLayout.WEST);
        panel.add(toDialog(),BorderLayout.WEST);
        panel.add(display(),BorderLayout.EAST);
        return panel;
    }

    private ItemListener toCurrencyChanged() {
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.DESELECTED) return;
                to = (Currency) ie.getItem();
            }
        };
    } 

   
}