import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Author author =new Author();
        author.setName("Antoine de Saint-Exupéry");
        author.setCountry("France");
        author.setBirthDay(LocalDate.ofEpochDay(7-31-1944));
        entityManager.persist(author);

        Author authortwo =new Author();
        authortwo.setName("Robert Cecil Martin");
        authortwo.setCountry("USA");
        authortwo.setBirthDay(LocalDate.ofEpochDay(1-1-1952));
        entityManager.persist(authortwo);

        Publisher publisher = new Publisher();
        publisher.setName("Penguin Random House");
        publisher.setAdress("England");
        publisher.setEstablishmentYear(2000);
        entityManager.persist(publisher);

        Category classics = new Category();
        classics.setName("classics");
        classics.setDescription("Description Test");
        entityManager.persist(classics);
        Category education = new Category();
        education.setName("education");
        education.setDescription("Description Test");
        entityManager.persist(education);

        Category fiction = new Category();
        fiction.setName("science fiction");
        fiction.setDescription("Description Test2");
        entityManager.persist(fiction);

        Book bookone = new Book();
        bookone.setName("Little Prince");
        bookone.setStock(100);
        bookone.setPublicationYear(2005);
        bookone.setAuthor(author);
        bookone.setPublisher(publisher);

        Book booktwo = new Book();
        booktwo.setName("Clean Code");
        booktwo.setStock(10);
        booktwo.setPublicationYear(2010);
        booktwo.setAuthor(authortwo);
        booktwo.setPublisher(publisher);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(classics);
        bookone.setCategoryList(categoryList);

        List<Category> categoryListTwo = new ArrayList<>();
        categoryListTwo.add(classics);
        categoryListTwo.add(education);
        booktwo.setCategoryList((categoryListTwo));

        entityManager.persist(bookone);
        entityManager.persist(booktwo);

        BookBorrowing bookBorrowingOne = new BookBorrowing();
        bookBorrowingOne.setBorrowerName("Ali Yilmaz");
        bookBorrowingOne.setBorrowingDate(LocalDate.ofEpochDay(2-1-2024));
        bookBorrowingOne.setReturnDate(LocalDate.ofEpochDay(2-6-2024));
        BookBorrowing bookBorrowingTwo = new BookBorrowing();
        bookBorrowingTwo.setBorrowerName("Mehmet Kılıç");
        bookBorrowingTwo.setBorrowingDate(LocalDate.ofEpochDay(5-2-2024));
        bookBorrowingTwo.setReturnDate(LocalDate.ofEpochDay(6-6-2024));
        bookBorrowingOne.setBook(bookone);
        bookBorrowingTwo.setBook(bookone);
        entityManager.persist(bookBorrowingOne);
        entityManager.persist(bookBorrowingTwo);

        transaction.commit();
        entityManager.close();
    }
}