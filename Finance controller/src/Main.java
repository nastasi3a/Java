//import classes
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int counter;

        //Create list of shopping categories
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Grocery");
        categories.add("Clothes");
        categories.add("Beauty&Care");
        categories.add("Health");
        categories.add("Else");

        //Create hash table to store expenses
        HashMap<String, ArrayList<Double>> expensesByCategories = new HashMap<>();
        for (String category : categories) expensesByCategories.put(category, new ArrayList<>());

        //Create hash table to store currency rates
        HashMap<String, Double> currencyRate = new HashMap<>();
        currencyRate.put("EUR", 41.7502);
        currencyRate.put("USD", 37.4406);
        currencyRate.put("PLN", 9.3133);
        currencyRate.put("GBP", 48.4419);
        currencyRate.put("HUF", 0.1091);

        System.out.println("How much money in UAH do you have left before payday?");
        double moneyBeforeSalary = scanner.nextDouble();

        System.out.println("How many days left before payday?");
        int daysBeforeSalary = scanner.nextInt();

        boolean isRunning = true;
        while (isRunning) {
            //show menu
            System.out.println("What would you like to do?");
            System.out.println("1 - Convert currency");
            System.out.println("2 - Get advice");
            System.out.println("3 - Enter spending");
            System.out.println("4 - Show expenditures");
            System.out.println("5 — Show the spent highest amount");
            System.out.println("0 - Exit");
            int command = scanner.nextInt();

            switch (command) {
                case 1 -> {
                    System.out.println("Your savings: " + moneyBeforeSalary + " UAH");
                    System.out.println("What currency do you want to convert to? Available Options:");
                    counter = 0;
                    for (String currencies : currencyRate.keySet())
                        System.out.println( (++counter) + " - " + currencies);
                    int currency = scanner.nextInt();
                    counter = 0;
                    for (String currencies : currencyRate.keySet()) {
                        if (++counter == currency) {
                            System.out.println("Your savings in " + currencies + ": " + moneyBeforeSalary / currencyRate.get(currencies));
                            break;
                        }
                    }
                    if (counter==currencyRate.size()) System.out.println("Incorrect number");
                }
                case 2 -> {
                    if (moneyBeforeSalary < 1000)
                        System.out.println("Today it is better to eat at home. Save and you'll make it to paycheck!");
                    else if (moneyBeforeSalary < 5000) {
                        if (daysBeforeSalary < 10) System.out.println("Okay, time to McDuck!");
                        else
                            System.out.println("Today it is better to eat at home. Save and you'll make it to paycheck!");
                    } else if (moneyBeforeSalary < 15000) {
                        if (daysBeforeSalary < 10)
                            System.out.println("Not bad! Buy dollars and go to dinner at a cool place. :)");
                        else System.out.println("Okay, time to McDuck!");
                    } else if (daysBeforeSalary < 10) System.out.println("Great! Order crabs!");
                    else System.out.println("Not bad! Buy dollars and go to dinner at a cool place. :)");
                }
                case 3 -> {
                    System.out.println("Enter the amount of spending:");
                    double expense = scanner.nextDouble();
                    moneyBeforeSalary -= expense;
                    System.out.println("Choose the category:");
                    System.out.println("1 - Grocery");
                    System.out.println("2 - Clothes");
                    System.out.println("3 - Beauty&Care");
                    System.out.println("4 - Health");
                    System.out.println("5 - Else");
                    int categoryNum = scanner.nextInt();
                    if (categoryNum > 0 && categoryNum <= categories.size()) {
                        String category = categories.get(categoryNum - 1);
                        ArrayList<Double> exp = expensesByCategories.get(category);
                        exp.add(expense);
                        expensesByCategories.put(category, exp);
                    } else {
                        String category = "Else";
                        ArrayList<Double> exp = expensesByCategories.get(category);
                        exp.add(expense);
                        expensesByCategories.put(category, exp);
                    }

                    System.out.println("Value saved! Your current balance in UAH: " + moneyBeforeSalary);
                    if (moneyBeforeSalary < 400)
                        System.out.println("There is very little left in your account. It's time to start saving!");
                }
                case 4 -> {
                    for (String i : expensesByCategories.keySet()) {
                        System.out.println(i + ":");
                        for (double j : expensesByCategories.get(i))
                            System.out.println("    " + j + " UAH spent");
                    }
                }
                case 5 -> {
                    double maxExpense = 0;
                    String categoryOfMaxExpense = "";
                    for (String category : expensesByCategories.keySet())
                        for (double expense : expensesByCategories.get(category))
                            if (maxExpense < expense) {
                                maxExpense = expense;
                                categoryOfMaxExpense = category;
                            }
                    System.out.println("The largest amount spent was in " + categoryOfMaxExpense + " category: " + maxExpense + " UAH.");

                }
                case 0 -> {
                    System.out.println("Exit");
                    isRunning = false;
                }
                default -> System.out.println("Sorry, there is no such command yet.");
            }
        }
        scanner.close();
    }
}