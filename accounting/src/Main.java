import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] monthReports = new String[12];
        String[][][] linesMonthReports = new String[12][][];
        String yearReport;
        String[][][] linesYearReport = new String[1][][];
        boolean isYear = false, isMonth = false;

        int command;
         do {
            printMenu();
            command = scanner.nextInt();

            switch (command) {
                case 1 -> {

                    for (int i = 1; i < 10; i++) monthReports[i - 1] = readFileContentsOrNull("m.20220" + i);
                    for (int i = 10; i < 13; i++) monthReports[i - 1] = readFileContentsOrNull("m.2022" + i);

                    for (int i = 0; i < 12; i++) {
                        linesMonthReports[i] = readReport(monthReports[i]);
                    } isMonth = true;
                }
                case 2 -> {
                    yearReport = readFileContentsOrNull("y.2022");
                    linesYearReport[0] = readReport(yearReport);

                    isYear = true;
                }
                case 3 -> {
                    float[] sumM = new float[12];
                    float[] sumY = new float[12];

                    if (!isMonth) {
                        System.out.println("You have not read monthly reports");
                        break;
                    }
                    else {
                        for (int i = 0; i < 12; i++) {
                            if (linesMonthReports[i] != null) {
                                for (int j = 1; j < linesMonthReports[i].length; j++) {
                                    if (linesMonthReports[i][j] != null) {
                                        if (linesMonthReports[i][j][1].equals("TRUE")) {
                                            sumM[i] -= (Float.parseFloat(linesMonthReports[i][j][2]) * Float.parseFloat(linesMonthReports[i][j][3]));
                                        } else if (linesMonthReports[i][j][1].equals("FALSE"))
                                            sumM[i] += (Float.parseFloat(linesMonthReports[i][j][2]) * Float.parseFloat(linesMonthReports[i][j][3]));
                                    }
                                }
                            }
                        }
                    }
                    if (!isYear) {
                        System.out.println("You have not read annual report");
                        break;
                    }
                    else {
                        if (linesYearReport[0] != null) {
                            for (int i = 1; i < linesYearReport[0].length; i++) {
                                if (linesYearReport[0][i] != null) {
                                    if (linesYearReport[0][i][2].equals("TRUE\r")) {
                                        sumY[Integer.parseInt(linesYearReport[0][i][0]) - 1] -= Float.parseFloat(linesYearReport[0][i][1]);
                                    } else if (linesYearReport[0][i][2].equals("FALSE\r")) {
                                        sumY[Integer.parseInt(linesYearReport[0][i][0]) - 1] += Float.parseFloat(linesYearReport[0][i][1]);
                                    }
                                }
                            }
                        }
                    }

                    if (sumM == sumY) System.out.println("Everything is correct.");
                    else {
                        System.out.println("Something is incorrect.");
                        for (int i = 0; i < 12; i++) {
                            if (sumM[i] != sumY[i]) System.out.println("Month " + (i+1) + ":\n\tMonth report: " + sumM[i]+ "\n\tYear report: " + sumY[i]);
                        }
                    }
                }
                case 4 ->
                {
                    float maxT = 0, maxF = 0;
                    String nameT = "", nameF = "";
                    if (!isMonth) {
                        System.out.println("You have not read monthly reports");
                        break;
                    } else {
                        for (int i = 0; i < 12; i++) {
                            if (linesMonthReports[i] != null) {
                                for (int j = 1; j < linesMonthReports[i].length; j++) {
                                    if (linesMonthReports[i][j] != null) {
                                        if (linesMonthReports[i][j][1].equals("TRUE")) {
                                            if ((Float.parseFloat(linesMonthReports[i][j][2]) * Float.parseFloat(linesMonthReports[i][j][3])) > maxT) {
                                                maxT = (Float.parseFloat(linesMonthReports[i][j][2]) * Float.parseFloat(linesMonthReports[i][j][3]));
                                                nameT = linesMonthReports[i][j][0];
                                            }
                                        } else if (linesMonthReports[i][j][1].equals("FALSE"))
                                            if ((Float.parseFloat(linesMonthReports[i][j][2]) * Float.parseFloat(linesMonthReports[i][j][3])) > maxF) {
                                                maxF = (Float.parseFloat(linesMonthReports[i][j][2]) * Float.parseFloat(linesMonthReports[i][j][3]));
                                                nameF = linesMonthReports[i][j][0];
                                            }
                                    }
                                }
                            }
                            System.out.println("Month " + (i+1) + ":");
                            System.out.println("\tMax income: " + nameF + " - " + maxF);
                            System.out.println("\tMax expense: " + nameT + " - " + maxT);
                            maxF = 0; maxT = 0;
                            nameF = ""; nameT = "";
                        }
                    }
                }
                case 5 ->
                {
                    float avgExpense = 0, avgIncome = 0;
                    float[] income = new float[12];
                    Arrays.fill(income, 0);

                    if (!isYear) {
                        System.out.println("You have not read annual report");
                        break;
                    } else {
                            if (linesYearReport[0] != null) {
                                for (int i = 1; i < linesYearReport[0].length; i++) {
                                    if (linesYearReport[0][i] != null) {
                                        if (linesYearReport[0][i][2].equals("TRUE\r")) {
                                            income[Integer.parseInt(linesYearReport[0][i][0]) - 1] -= Float.parseFloat(linesYearReport[0][i][1]);
                                            avgExpense += Float.parseFloat(linesYearReport[0][i][1]);
                                        } else if (linesYearReport[0][i][2].equals("FALSE\r")) {
                                            income[Integer.parseInt(linesYearReport[0][i][0]) - 1] += Float.parseFloat(linesYearReport[0][i][1]);
                                            avgIncome += Float.parseFloat(linesYearReport[0][i][1]);
                                        }
                                    }
                                }
                            }

                        for (int i = 0; i < 12; i++)
                        {
                            System.out.println("Month " + (i+1) + " income: " + income[i]);
                        }
                        System.out.println("\nAverage income: " + avgIncome/12);
                        System.out.println("Average expanse: " + avgExpense/12);
                    }
                }
                case 0 -> System.out.println("Exit");
                default -> System.out.println("Sorry, this command is not available");
            }
        } while (command!=0);
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1 - Read all monthly reports");
        System.out.println("2 - Read annual report");
        System.out.println("3 - Compare reports");
        System.out.println("4 - Show monthly reports info");
        System.out.println("5 - Show yearly report info");
        System.out.println("0 - Exit");
    }

    private static String readFileContentsOrNull(String path)
    {
        Path filePath = Paths.get("e:/Git/Java/accounting/src/reports/" + path + ".csv");
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("Can not read the file: " + filePath);
            return null;
        }
    }

    private static String[][] readReport(String report) {
        if (report == null) return null;
        String[] arr = report.split("\\n");
        String[][] linesReport = new String[arr.length][];
        int count = 0;
        for (String line : arr) {
            linesReport[count++] = line.split(";");
        }
        return linesReport;
    }
}