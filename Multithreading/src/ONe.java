import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}

class Request {
    private String department;
    private String filePath;

    public Request(String department, String filePath) {
        this.department = department;
        this.filePath = filePath;
    }

    public String getDepartment() {
        return department;
    }

    public String getFilePath() {
        return filePath;
    }
}

class ExcelExporter {
    private final DataSource dataSource;

    public ExcelExporter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void exportToExcel(Request request) {
        synchronized (this) {
            try (Connection connection = dataSource.getConnection()) {
                List<Employee> employees = fetchEmployeeData(connection, request);

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Employees");

                writeDataToExcel(sheet, employees);

                saveExcelFile(workbook, request.getFilePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Employee> fetchEmployeeData(Connection connection, Request request) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, name, department FROM employees WHERE department = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, request.getDepartment());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department")));
            }
        }
        return employees;
    }

    private void writeDataToExcel(Sheet sheet, List<Employee> employees) {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Department");

        int rowNum = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getDepartment());
        }
    }

    private void saveExcelFile(Workbook workbook, String filePath) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}

class ExportService {
    private final ExecutorService executorService;

    public ExportService(int numberOfThreads) {
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    public void exportData(Request request, ExcelExporter exporter) {
        executorService.submit(() -> {
            exporter.exportToExcel(request);
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}

public class EmployeeExportApp {
    public static void main(String[] args) {
        // Configure HikariCP for database connection pooling
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/your_database");
        config.setUsername("your_username");
        config.setPassword("your_password");
        config.setMaximumPoolSize(10);
        DataSource dataSource = new HikariDataSource(config);

        ExcelExporter exporter = new ExcelExporter(dataSource);
        ExportService exportService = new ExportService(5); // 5 threads in the pool

        // Example requests
        Request request1 = new Request("Sales", "Sales_Employees.xlsx");
        Request request2 = new Request("Engineering", "Engineering_Employees.xlsx");

        // Export data concurrently
        exportService.exportData(request1, exporter);
        exportService.exportData(request2, exporter);

        // Shutdown the export service after some time to allow exports to complete
        try {
            Thread.sleep(5000); // Wait for exports to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exportService.shutdown();
    }
}