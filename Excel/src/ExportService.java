import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class ExportService {

    public void exportData(String department) {
        // Generate a unique filename for each export
        String fileName = "EmployeeData_" + department + "_" + UUID.randomUUID() + ".xlsx";

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Fetch employee data for the given department
            String query = "SELECT id, name, position, salary FROM employees WHERE department = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, department);
                ResultSet resultSet = statement.executeQuery();

                // Generate Excel file
                try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet("Employee Data");

                    // Create headers
                    Row headerRow = sheet.createRow(0);
                    headerRow.createCell(0).setCellValue("ID");
                    headerRow.createCell(1).setCellValue("Name");
                    headerRow.createCell(2).setCellValue("Position");
                    headerRow.createCell(3).setCellValue("Salary");

                    // Populate data rows
                    int rowIndex = 1;
                    while (resultSet.next()) {
                        Row row = sheet.createRow(rowIndex++);
                        row.createCell(0).setCellValue(resultSet.getInt("id"));
                        row.createCell(1).setCellValue(resultSet.getString("name"));
                        row.createCell(2).setCellValue(resultSet.getString("position"));
                        row.createCell(3).setCellValue(resultSet.getDouble("salary"));
                    }

                    // Write to file
                    try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                        workbook.write(fileOut);
                    }

                    System.out.println("Data exported successfully to " + fileName);
                }
            }
        } catch (Exception e) {
            System.err.println("Error exporting data for department " + department + ": " + e.getMessage());
        }
    }
}
