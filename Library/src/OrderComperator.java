import java.util.*;
class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {

        int dateComparison = o1.getOrderDate().compareTo(o2.getOrderDate());
        if (dateComparison != 0) {
            return dateComparison;
        }


        return Double.compare(o2.getTotalAmount(), o1.getTotalAmount());
    }
}