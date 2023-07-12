import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StepTracker user = new StepTracker();
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.print("Enter first three letters of month(example: Jan): ");
                    String month = scanner.next();
                    System.out.print("Enter number of day: ");
                    int day = scanner.nextInt();
                    System.out.print("Enter amount of steps: ");
                    int steps = scanner.nextInt();
                    user.setStepAmount(month, day, steps);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.print("Enter new daily goal: ");
                    int goal = scanner.nextInt();
                    user.setGoal(goal);
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Sorry, there is no such command yet.");
                    break;
            }
        }
        scanner.close();
    }
    public static void printMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1 - Enter day's step amount");
        System.out.println("2 - Show month statistics");
        System.out.println("3 - Change daily steps goal");
        System.out.println("0 - Exit");
    }
}

