import java.util.*;
import java.time.LocalDate;

public class EcommerceSorting {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("ORD001", LocalDate.of(2023, 12, 15), 250.75));
        orders.add(new Order("ORD002", LocalDate.of(2023, 12, 15), 300.50));
        orders.add(new Order("ORD003", LocalDate.of(2023, 12, 14), 150.00));
        orders.add(new Order("ORD004", LocalDate.of(2023, 12, 15), 200.25));
        orders.add(new Order("ORD005", LocalDate.of(2023, 12, 13), 500.00));

        orders.sort(new OrderComparator());


        for (Order order : orders) {
            System.out.println(order);
        }
    }
}