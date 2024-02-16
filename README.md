
>>01>>
Query query = session.createQuery("FROM Book WHERE publicationYear > 2010", Book.class);
List<Book> books = query.list();

>>02>>
Query query = session.createQuery("UPDATE Book SET price = price + 10 WHERE author = :author");
query.setParameter("author", specificAuthor);
int rowsUpdated = query.executeUpdate();

>>03>>
@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Book> books;

session.delete(author);

>>04>>
Query query = session.createQuery("SELECT Avg(price) FROM Book");
Double averagePrice = (Double) query.uniqueResult();

>>05>>
Query query = session.createQuery("SELECT a.name, COUNT(b) FROM Author a JOIN a.books b GROUP BY a");
List<Object[]> results = query.list();

>>06>>
Query query = session.createQuery("FROM Book WHERE author.country = :country");
query.setParameter("country", specificCountry);
List<Book> books = query.list();

>>07>>
@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Book> books;

@ManyToOne
@JoinColumn(name = "author_id")
private Author author;

>>08>>
Query query = session.createQuery("SELECT a FROM Author a JOIN a.books b GROUP BY a HAVING COUNT(b) > (SELECT AVG(bCount) FROM (SELECT COUNT(*) as bCount FROM Book GROUP BY author_id) as subquery)");
List<Author> authors = query.list();
