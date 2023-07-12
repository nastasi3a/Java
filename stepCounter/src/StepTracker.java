import java.util.Arrays;
import java.util.HashMap;

public class StepTracker {
    private int goal;
    private HashMap<String, int[]> year;
    //private int[] month;

    StepTracker() {
        goal = 10_000;
        year = new HashMap<>();
        year.put("Jan", new int[31]);
        year.put("Feb", new int[28]);
        year.put("Mar", new int[31]);
        year.put("Apr", new int[30]);
        year.put("May", new int[31]);
        year.put("Jun", new int[30]);
        year.put("Jul", new int[31]);
        year.put("Aug", new int[31]);
        year.put("Sep", new int[30]);
        year.put("Oct", new int[31]);
        year.put("Nov", new int[30]);
        year.put("Dec", new int[31]);

        for (String month : year.keySet()) {
            Arrays.fill(year.get(month), 0);
        }
    }
    public void setGoal(int newGoal)
    {
        goal = newGoal;
    }
    public void setStepAmount(String month, int day, int steps)
    {
        if (day>0 && year.containsKey(month) && year.get(month).length>=day)
            year.get(month)[day-1] = steps;
        else if (day<1) {
            System.out.println("Day value is too low.");
        } else if (!year.containsKey(month)) {
            System.out.println("Month value is incorrect.");
        } else if (year.get(month).length<day) {
            System.out.println("Day value is too high.");
        }
    }
}
