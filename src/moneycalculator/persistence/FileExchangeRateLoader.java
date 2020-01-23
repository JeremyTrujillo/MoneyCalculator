package moneycalculator.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;


public class FileExchangeRateLoader implements ExchangeRateLoader {

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("exchangeRates")));
            while(true){
                String line = bufferedReader.readLine();
                String[] split = line.split(",");
                if (split[0].equals(from.getCode()) && split[1].equals(to.getCode())){
                    return new ExchangeRate(from,to,Double.parseDouble(split[2]));
                }
            }
        } catch (IOException ex) {
        }
            return null;
        }
    
}
