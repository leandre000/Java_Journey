import java.sql.*;
import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private int age;
    private String position;
    private int skillLevel;
    private int fitnessLevel;


    public Player(String name, int age, String position, int skillLevel, int fitnessLevel) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.skillLevel = skillLevel;
        this.fitnessLevel = fitnessLevel;
    }

    public Player(int id, String name, int age, String position, int skillLevel, int fitnessLevel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.skillLevel = skillLevel;
        this.fitnessLevel = fitnessLevel;
    }


    public void save() {
        String query = "INSERT INTO players (name, age, position, skill_level, fitness_level) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.name);
            stmt.setInt(2, this.age);
            stmt.setString(3, this.position);
            stmt.setInt(4, this.skillLevel);
            stmt.setInt(5, this.fitnessLevel);
            stmt.executeUpdate();
            System.out.println("Player saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void listAll() {
        String query = "SELECT * FROM players";
        try (Connection conn = DatabaseUtility.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Age: %d, Position: %s, Skill: %d, Fitness: %d%n",
                        rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
                        rs.getString("position"), rs.getInt("skill_level"), rs.getInt("fitness_level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateById(int id, String name, int age, String position, int skillLevel, int fitnessLevel) {
        String query = "UPDATE players SET name = ?, age = ?, position = ?, skill_level = ?, fitness_level = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, position);
            stmt.setInt(4, skillLevel);
            stmt.setInt(5, fitnessLevel);
            stmt.setInt(6, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player updated successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteById(int id) {
        String query = "DELETE FROM players WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player deleted successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("\nPlayer Management:");
            System.out.println("1. Create Player");
            System.out.println("2. List All Players");
            System.out.println("3. Update Player");
            System.out.println("4. Delete Player");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter skill level: ");
                    int skillLevel = scanner.nextInt();
                    System.out.print("Enter fitness level: ");
                    int fitnessLevel = scanner.nextInt();
                    Player newPlayer = new Player(name, age, position, skillLevel, fitnessLevel);
                    newPlayer.save();
                    break;

                case 2:
                    Player.listAll();
                    break;

                case 3:
                    System.out.print("Enter player ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new age: ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new position: ");
                    position = scanner.nextLine();
                    System.out.print("Enter new skill level: ");
                    skillLevel = scanner.nextInt();
                    System.out.print("Enter new fitness level: ");
                    fitnessLevel = scanner.nextInt();
                    Player.updateById(updateId, name, age, position, skillLevel, fitnessLevel);
                    break;

                case 4:
                    System.out.print("Enter player ID to delete: ");
                    int deleteId = scanner.nextInt();
                    Player.deleteById(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting Player Management.");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
