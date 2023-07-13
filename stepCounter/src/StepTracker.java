import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class StepTracker {
    private int goal;
    private LinkedHashMap<String, int[]> year;
    private Scanner scanner = new Scanner(System.in);

    StepTracker() { //constructor
        goal = 10_000;
        year = new LinkedHashMap<>();
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

    public void setStepAmount()
    {
        System.out.print("Enter first three letters of month (example: Jan): ");
        String month = scanner.next();
        if (!year.containsKey(month)) {
            System.out.println("Month value can be only " + year.keySet() + ".");
            return;
        }
        System.out.print("Enter number of day: ");
        int day = scanner.nextInt();
        if (day<1) {
            System.out.println("Day value should be more then 0.");
            return;
        } else if (year.get(month).length<day) {
            System.out.println("Day value should not be more than " + year.get(month).length + ".");
            return;
        }
        System.out.print("Enter amount of steps: ");
        int steps = scanner.nextInt();
        if (steps<0) {
            System.out.println("Steps value should not be less than 0.");
        }
        if (year.containsKey(month) && year.get(month).length>=day)
            year.get(month)[day-1] = steps;
    }

    public void showStatistic(){
        System.out.print("Enter the month which statistic you want to see: ");
        System.out.println(year.keySet());
        String month = scanner.next();
        int monthStepsSum = 0;
        int monthStepsMax = 0;
        int bestStepsSeries = 0;
        int prevBestStepsSeries = 0;
        if (year.containsKey(month)) {
            for (int i = 0; i < year.get(month).length; i++) {
                System.out.println("Day " + (i+1) + ": " + year.get(month)[i] + " steps.");
                monthStepsSum += year.get(month)[i];
                if (monthStepsMax < year.get(month)[i]) {
                    monthStepsMax = year.get(month)[i];
                }
                if (year.get(month)[i]>=goal) bestStepsSeries++;
                else {
                    if (prevBestStepsSeries < bestStepsSeries)
                        prevBestStepsSeries = bestStepsSeries;
                    bestStepsSeries = 0;
                }
            }
            int monthStepsAvg = monthStepsSum / year.get(month).length;

            System.out.println("Total steps amount: " + monthStepsSum);
            System.out.println("Max steps amount: " + monthStepsMax);
            System.out.println("Average steps amount: " + monthStepsAvg);
            System.out.println("The best series of steps: " + (Math.max(prevBestStepsSeries, bestStepsSeries)));
            System.out.println("Total distance: " + (float)monthStepsSum*0.75*0.001 + " km.");
            System.out.println((float)monthStepsSum*50*0.001 + " kcal burnt.");

        }
    }
}
