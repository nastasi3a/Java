//import class
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Shopping shopping = new Shopping();

        System.out.println("How much money in UAH do you have left before payday?");
        double moneyBeforeSalary = scanner.nextDouble();

        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            int command = scanner.nextInt();

            switch (command) {
                case 1 -> {
                    Converter converter = new Converter();
                    System.out.println("Your savings: " + moneyBeforeSalary + " UAH");

                    converter.showMenu(); //show available currencies
                    int currency = scanner.nextInt(); //select currency
                    converter.convert(currency, moneyBeforeSalary); //convert
                }
                case 2 -> {
                    System.out.println("How many days left before payday?");
                    int daysBeforeSalary = scanner.nextInt();
                    advice(moneyBeforeSalary, daysBeforeSalary);
                }
                case 3 -> {
                    System.out.println("Enter the amount of spending:");
                    double expense = scanner.nextDouble();
                    moneyBeforeSalary -= expense;
                    System.out.println("Choose the category:");

                    shopping.showMenu(); //show available categories
                    int categoryNum = scanner.nextInt(); //choose category
                    shopping.addExpense(categoryNum, expense);

                    System.out.println("Spending saved! Your current balance in UAH: " + moneyBeforeSalary);
                    if (moneyBeforeSalary < 400)
                        System.out.println("There is very little left in your account. It's time to start saving!");
                }
                case 4 -> shopping.showExpenditures();
                case 5 -> shopping.maxSpent();
                case 0 -> {
                    System.out.println("Exit");
                    isRunning = false;
                }
                default -> System.out.println("Sorry, there is no such command yet.");
            }
        }
        scanner.close();
    }
    static void advice(double moneyBeforeSalary, int daysBeforeSalary){

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
    static void showMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1 - Convert currency");
        System.out.println("2 - Get advice");
        System.out.println("3 - Enter spending");
        System.out.println("4 - Show expenditures");
        System.out.println("5 — Show the largest amount spent");
        System.out.println("0 - Exit");
    }
}