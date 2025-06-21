import java.sql.*;
import java.util.Scanner;

public class Team {
    private int id;
    private String name;


    public Team(String name) {
        this.name = name;
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public void save() {
        String query = "INSERT INTO teams (name) VALUES (?)";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.name);
            stmt.executeUpdate();
            System.out.println("Team saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void listAll() {
        String query = "SELECT * FROM teams";
        try (Connection conn = DatabaseUtility.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s%n", rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateById(int id, String name) {
        String query = "UPDATE teams SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Team updated successfully.");
            } else {
                System.out.println("Team not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteById(int id) {
        String query = "DELETE FROM teams WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Team deleted successfully.");
            } else {
                System.out.println("Team not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("\nTeam Management:");
            System.out.println("1. Create Team");
            System.out.println("2. List All Teams");
            System.out.println("3. Update Team");
            System.out.println("4. Delete Team");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter team name: ");
                    String name = scanner.nextLine();
                    Team newTeam = new Team(name);
                    newTeam.save();
                    break;

                case 2:
                    Team.listAll();
                    break;

                case 3:
                    System.out.print("Enter team ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new team name: ");
                    name = scanner.nextLine();
                    Team.updateById(updateId, name);
                    break;

                case 4:
                    System.out.print("Enter team ID to delete: ");
                    int deleteId = scanner.nextInt();
                    Team.deleteById(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting Team Management.");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
