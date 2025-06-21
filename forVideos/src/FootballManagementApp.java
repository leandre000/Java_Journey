import java.util.Scanner;

public class FootballManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFootball Management System:");
            System.out.println("1. Manage Players");
            System.out.println("2. Manage Coaches");
            System.out.println("3. Manage Teams");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Player.menu(scanner);
                    break;

                case 2:
                    Coach.menu(scanner);
                    break;

                case 3:
                    Team.menu(scanner);
                    break;

                case 4:
                    System.out.println("Exiting Football Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
