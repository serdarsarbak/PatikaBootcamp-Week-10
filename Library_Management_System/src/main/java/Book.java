import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name= "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "serial")
    private int id;

    @Column(name = "book_name", nullable = false)
    private String name;

    @Column(name = "publicationYear", nullable = false)
    private int publicationYear;

    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookBorrowing> bookBorrowingList;

    @ManyToMany (cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book2category",
            joinColumns = {
                    @JoinColumn(name="book2category_book_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "book2category_category_id")
            }
    )
    private List<Category> categoryList;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
