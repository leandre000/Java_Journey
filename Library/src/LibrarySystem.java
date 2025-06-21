import java.util.*;

public class LibrarySystem {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Inzozi z’Abakiri bato", "Nziza Glad", 2020));
        books.add(new Book("Isoko y'Ubwenge", "Mutabazi Jean Pierre", 2021));
        books.add(new Book("Inzozi z’Abakiri bato", "Nziza Glad", 2022));
        books.add(new Book("Ubumuntu Bwacu", "Uwimana Aline", 2020));
        books.add(new Book("Isoko y'Ubwenge", "Mutabazi Jean Pierre", 2023));

        books.sort(new BookComparator());

        for (Book book : books) {
            System.out.println(book);
        }
    }
}