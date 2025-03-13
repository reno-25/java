import java.util.Scanner;

public class score {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalSubjects = getIntInput(scanner, "Enter total number of subjects: ");
        int totalStudents = getIntInput(scanner, "Enter total number of students: ");

        String[] subjectNames = new String[totalSubjects];
        for (int i = 0; i < totalSubjects; i++) {
            subjectNames[i] = getAlphabeticInput(scanner, "Enter subject name: ");
        }

        String[][] studentData = new String[totalStudents][totalSubjects + 3]; // ID, Name, Scores for each subject, Average

        for (int i = 0; i < totalStudents; i++) {
            studentData[i][0] = String.valueOf(getIntInput(scanner, "Enter student " + (i + 1) + " ID: "));
            studentData[i][1] = getAlphabeticInput(scanner, "Enter student " + (i + 1) + " name: ");
            int totalScore = 0;

            for (int j = 0; j < totalSubjects; j++) {
                int score = getIntInput(scanner, "Enter student " + (i + 1) + " score for " + subjectNames[j] + ": ");
                studentData[i][j + 2] = String.valueOf(score);
                totalScore += score;
            }

            studentData[i][totalSubjects + 2] = String.format("%.2f", (double) totalScore / totalSubjects);
        }

        System.out.println("\nEntered Data:");
        System.out.printf("%-10s %-15s", "ID", "Name");
        for (String subjectName : subjectNames) {
            System.out.printf("%-15s", subjectName);
        }
        System.out.printf("%-10s%n", "Average");

        for (String[] student : studentData) {
            for (String data : student) {
                System.out.printf("%-15s", data);
            }
            System.out.println();
        }

        scanner.close();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
        return input;
    }

    private static String getAlphabeticInput(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter alphabetic characters only.");
            }
        }
        return input;
    }
}