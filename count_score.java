import java.util.Scanner;

public class score {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of subjects and students
        int totalSubjects = getIntInput(scanner, "Enter total number of subjects: ");
        int totalStudents = getIntInput(scanner, "Enter total number of students: ");

        // Store subject names
        String[] subjectNames = new String[totalSubjects];
        for (int i = 0; i < totalSubjects; i++) {
            subjectNames[i] = getAlphabeticInput(scanner, "Enter subject name: ");
        }

        // Prepare data storage
        String[][] studentData = new String[totalStudents][totalSubjects * 2 + 4]; // ID, Name, Scores+Categories, Total, Rank

        // Collect student data
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nEntering data for student " + (i + 1));
            studentData[i][0] = String.valueOf(getIntInput(scanner, "Enter student ID: "));
            studentData[i][1] = getAlphabeticInput(scanner, "Enter student name: ");
            int totalScore = 0;

            for (int j = 0; j < totalSubjects; j++) {
                int score = getIntInput(scanner, "Enter score for " + subjectNames[j] + ": ");
                studentData[i][j * 2 + 2] = String.valueOf(score);
                studentData[i][j * 2 + 3] = getCategory(score);
                totalScore += score;
            }

            studentData[i][totalSubjects * 2 + 2] = String.valueOf(totalScore);
        }

        // Calculate ranks
        for (int i = 0; i < totalStudents; i++) {
            int rank = 1;
            for (int j = 0; j < totalStudents; j++) {
                if (Integer.parseInt(studentData[j][totalSubjects * 2 + 2]) > Integer.parseInt(studentData[i][totalSubjects * 2 + 2])) {
                    rank++;
                }
            }
            studentData[i][totalSubjects * 2 + 3] = String.valueOf(rank);
        }

        // Display results
        System.out.println("\nResults:");
        System.out.printf("%-10s %-15s", "ID", "Name");
        for (String subjectName : subjectNames) {
            System.out.printf("%-15s %-10s", subjectName, "Category");
        }
        System.out.printf("%-15s %-10s%n", "Total Score", "Rank");

        for (String[] student : studentData) {
            for (String data : student) {
                System.out.printf("%-15s", data);
            }
            System.out.println();
        }

        scanner.close();
    }

    // Ensure valid integer input
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Ensure alphabetic input for names
    private static String getAlphabeticInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("[a-zA-Z ]+")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter alphabetic characters only.");
            }
        }
    }

    // Categorize scores
    private static String getCategory(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else {
            return "F";
        }
    }
}
