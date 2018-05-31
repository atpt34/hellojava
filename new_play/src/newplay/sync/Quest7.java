package newplay.sync;

class Book {
    private String book;
    public String getBook() {
        return book;
    }
    public void setBook(String book) {
        this.book = book;
    }
    
}
public class Quest7 {

    public static void main(String[] args) {

        Book b1 = new Book();
                b1.setBook("java 10");
          Book b2 = new Book();
                b2.setBook("java 10");

         if (b1.equals(b2)) {
             System.out.println(" equals() is not ==");
         }
    }

}
