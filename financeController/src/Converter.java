import java.util.ArrayList;
import java.util.HashMap;

public class Converter {
    //Create hash table to store currency rates
    HashMap<String, Double> currencyRate;
    Converter()
    {
        currencyRate = new HashMap<>();
        currencyRate.put("EUR", 41.7502);
        currencyRate.put("USD", 37.4406);
        currencyRate.put("PLN", 9.3133);
    }

    void showMenu(){
        int counter = 0;
        System.out.println("What currency do you want to convert to? Available Options:");
        for (String currencies : currencyRate.keySet())
            System.out.println( (++counter) + " - " + currencies);
    }
    void convert(int currency, double moneyBeforeSalary){
        int counter = 0;

        for (String currencies : currencyRate.keySet()) {
            if (++counter == currency) {
                System.out.println("Your savings in " + currencies + ": " + moneyBeforeSalary / currencyRate.get(currencies));
                break;
            }
        }
        if (counter==currencyRate.size()) System.out.println("Incorrect number");
    }


}