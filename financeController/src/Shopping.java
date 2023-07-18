import java.util.ArrayList;
import java.util.HashMap;

public class Shopping {
    ArrayList<String> categories;
    //Create hash table to store expenses
    HashMap<String, ArrayList<Double>> expensesByCategories;
    Shopping(){
        //Create list of shopping categories
        categories = new ArrayList<>();
        categories.add("Grocery");
        categories.add("Clothes");
        categories.add("Beauty&Care");
        categories.add("Health");
        categories.add("Else");

        expensesByCategories = new HashMap<>();
        for (String category : categories) expensesByCategories.put(category, new ArrayList<>());
    }

    void showMenu(){
        int counter = 0;
        for (String category : expensesByCategories.keySet())
            System.out.println( (++counter) + " - " + category);
    }

    void addExpense(int categoryNum, double expense)
    {
        int counter = 0;
        for (String category : expensesByCategories.keySet()) {
            if (++counter == categoryNum) {
                ArrayList<Double> exp = expensesByCategories.get(category);
                exp.add(expense);
                expensesByCategories.put(category, exp);
                break;
            }
        } if (counter==expensesByCategories.size()) {
        String category = "Else";
        ArrayList<Double> exp = expensesByCategories.get(category);
        exp.add(expense);
        expensesByCategories.put(category, exp);
        }
    }

    void showExpenditures()
    {
        for (String i : expensesByCategories.keySet()) {
            System.out.println(i + ":");
            for (double j : expensesByCategories.get(i))
                System.out.println("\t" + j + " UAH spent");
        }
    }

    void maxSpent(){
        double maxExpense = 0;
        String categoryOfMaxExpense = "";
        //iterating through shopping categories
        for (String category : expensesByCategories.keySet())
            //iterating through list of expenses in current category
            for (double expense : expensesByCategories.get(category))
                if (maxExpense < expense) {
                    maxExpense = expense;
                    categoryOfMaxExpense = category;
                }
        System.out.println("The largest amount spent was in " + categoryOfMaxExpense + " category: " + maxExpense + " UAH.");
    }
}
