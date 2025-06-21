import java.util.*;


class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        int titleComparison = b1.getTitle().compareTo(b2.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        }

        int authorComparison = b1.getAuthor().compareTo(b2.getAuthor());
        if (authorComparison != 0) {
            return authorComparison;
        }

        return Integer.compare(b1.getYear(), b2.getYear());
    }
}
