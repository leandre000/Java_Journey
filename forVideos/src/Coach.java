import java.sql.*;
import java.util.Scanner;

public class Coach {
    private int id;
    private String name;
    private int age;
    private String specialization;


    public Coach(String name, int age, String specialization) {
        this.name = name;
        this.age = age;
        this.specialization = specialization;
    }

    public Coach(int id, String name, int age, String specialization) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.specialization = specialization;
    }


    public void save() {
        String query = "INSERT INTO coaches (name, age, specialization) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.name);
            stmt.setInt(2, this.age);
            stmt.setString(3, this.specialization);
            stmt.executeUpdate();
            System.out.println("Coach saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all coaches
    public static void listAll() {
        String query = "SELECT * FROM coaches";
        try (Connection conn = DatabaseUtility.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Age: %d, Specialization: %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("specialization"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update coach by ID
    public static void updateById(int id, String name, int age, String specialization) {
        String query = "UPDATE coaches SET name = ?, age = ?, specialization = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, specialization);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Coach updated successfully.");
            } else {
                System.out.println("Coach not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete coach by ID
    public static void deleteById(int id) {
        String query = "DELETE FROM coaches WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Coach deleted successfully.");
            } else {
                System.out.println("Coach not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menu for CRUD operations
    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("\nCoach Management:");
            System.out.println("1. Create Coach");
            System.out.println("2. List All Coaches");
            System.out.println("3. Update Coach");
            System.out.println("4. Delete Coach");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter specialization: ");
                    String specialization = scanner.nextLine();
                    Coach newCoach = new Coach(name, age, specialization);
                    newCoach.save();
                    break;

                case 2:
                    Coach.listAll();
                    break;

                case 3:
                    System.out.print("Enter coach ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new age: ");
                    age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new specialization: ");
                    specialization = scanner.nextLine();
                    Coach.updateById(updateId, name, age, specialization);
                    break;

                case 4:
                    System.out.print("Enter coach ID to delete: ");
                    int deleteId = scanner.nextInt();
                    Coach.deleteById(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting Coach Management.");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
