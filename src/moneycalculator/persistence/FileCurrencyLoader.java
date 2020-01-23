package moneycalculator.persistence;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import moneycalculator.model.Currency;


public class FileCurrencyLoader implements CurrencyLoader {

    private final String filename;

    public FileCurrencyLoader(String filename) {
        this.filename = filename;
    }
    
    @Override
    public Currency[] load() {
        List<Currency> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            while (true){
                String line = reader.readLine();
                if (line == null) break;
                list.add(parseCurrency(line));
            }
        } catch (IOException ex) {
        }
        return list.toArray(new Currency[0]);
    }

    private Currency parseCurrency(String line) {
        String[] split = line.split(",");
        return new Currency (split[0],split[1],split[2]);
    }
    
}
