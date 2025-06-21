import java.time.LocalDate;
import java.util.*;

class Order {
    private String orderId;
    private LocalDate orderDate;
    private double totalAmount;


    public Order(String orderId, LocalDate orderDate, double totalAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }


    public String getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }


    @Override
    public String toString() {
        return String.format("Order{orderId='%s', orderDate=%s, totalAmount=%.2f}",
                orderId, orderDate, totalAmount);
    }
}