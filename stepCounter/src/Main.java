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
                case 1 -> //Enter day's step amount
                        user.setStepAmount();
                case 2 -> //month statistics
                        user.showStatistic();
                case 3 -> { //Change daily steps goal
                    System.out.print("Enter new daily goal: ");
                    int goal = scanner.nextInt();
                    user.setGoal(goal);
                }
                case 0 -> //Exit
                        isRunning = false;
                default -> System.out.println("Sorry, there is no such command yet.");
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

