package moneycalculator.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private double amount;
    private Currency from;
    private final Currency[] currencies;

    
    public SwingMoneyDialog(Currency[] currencies) {
        this.currencies = currencies;
        this.add(amount());
        this.add(fromCurrency());
    }

    @Override
    public Money get() {
        return new Money(amount, from);
    }
    
    
    private Component amount(){
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Insert amount:");
        JTextField textField = new JTextField("10");
        textField.setColumns(10);
        textField.getDocument().addDocumentListener(amountChanged());
        amount = (Double) Double.parseDouble(textField.getText());
        amountPanel.add(label);
        amountPanel.add(textField);
        return amountPanel;
    }
    private Component fromCurrency(){
        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new BoxLayout(currencyPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("From:");
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(fromCurrencyChanged());
        from = (Currency) combo.getSelectedItem();
        currencyPanel.add(label);
        currencyPanel.add(combo);
        return currencyPanel;
    }

    private ItemListener fromCurrencyChanged() {
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.DESELECTED) return;
                from = (Currency) ie.getItem();
            }
        };
    }
  
    private DocumentListener amountChanged() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                amountChanged(de.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                amountChanged(de.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                amountChanged(de.getDocument());
            }

            private void amountChanged(Document document) {
                try{
                    amount = Double.parseDouble(document.getText(0, document.getLength()));
                } catch (BadLocationException | NumberFormatException ex){
                }
            }
        };
    }
}
