import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] monthReports = new String[12];
        String[][][] linesMonthReports = new String[12][][];
        String yearReport;
        String[][][] linesYearReport = new String[1][][];

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
                        /*if (linesMonthReports[i] != null)
                            for (String[] line : linesMonthReports[i]) {
                                for (String l : line)
                                    System.out.println(l);
                            }*/
                    }
                }
                case 2 -> {
                    yearReport = readFileContentsOrNull("y.2022");
                    linesYearReport[0] = readReport(yearReport);

                    /*if (linesYearReport[0] != null)
                        for (String[] line : linesYearReport[0]) {
                            for (String l : line) {
                                System.out.print(l);
                            }
                            System.out.println();
                        }*/

                }
                case 3 -> {
                    float[] sumM = new float[12];
                    float[] sumY = new float[12];

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

                    if (linesYearReport[0] != null) {
                        for (int i = 1; i < linesYearReport[0].length; i++) {
                            if (linesYearReport[0][i] != null) {
                                if (linesYearReport[0][i][2].equals("TRUE\r")) {
                                    sumY[Integer.parseInt(linesYearReport[0][i][0])-1] -= Float.parseFloat(linesYearReport[0][i][1]);
                                } else if (linesYearReport[0][i][2].equals("FALSE\r")) {
                                    sumY[Integer.parseInt(linesYearReport[0][i][0])-1] += Float.parseFloat(linesYearReport[0][i][1]);
                                }
                            }
                        }
                    }

                    if (sumM == sumY) System.out.println("Everything is correct.");
                    else {
                        System.out.println("Something is incorrect.");
                        for (int i = 0; i < 12; i++) {
                            if (sumM[i] != sumY[i]) System.out.println("Month " + (i+1) + ":\n Month report: " + sumM[i]+ "\n Year report: " + sumY[i]);
                        }
                    }
                }
                case 0 -> System.out.println("Exit");
            }
        } while (command!=0);
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1 - Read all monthly reports");
        System.out.println("2 - Read yearly report");
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