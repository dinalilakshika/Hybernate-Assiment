package org.example;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.entity.Author;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            //Q:1
            Query query1 = session.createQuery("SELECT id, price, pubYear, title FROM Book WHERE pubYear > ?1");
            query1.setParameter(1, 2010);
            List<Object[]> bookList =  query1.getResultList();
            for (Object[] book : bookList) {
                System.out.println("id : "+book[0] + ", price : " + book[1] + ", pubYear : " + book[2] + ", title : " + book[3]);
            }

            //Q:2
            Query query2 = session.createQuery("UPDATE Book b SET b.price = b.price * 1.1 WHERE b.author.id = ?1");
            query2.setParameter(1,2);
            query2.executeUpdate();

            //Q:3
            deleteAuthorAndBooks(4);

            //Q:4
            Query query4 = session.createQuery("SELECT AVG(b.price) FROM Book b");
            Double average = (Double) query4.getSingleResult();
            System.out.println("LKR: " + average);

            //Q:5
            Query query5 = session.createQuery("SELECT a, COUNT(b) FROM Author a JOIN a.books b GROUP BY a.id");
            List<Objects[]> authors = query5.getResultList();
            for (Object[] author : authors) {
                Author authorObj = (Author) author[0];
                Long count = (Long) author[1];
                System.out.println("Author id : "+authorObj.getId()+", Count : "+ count);
            }

            //Q:6
            Query query6 = session.createQuery("SELECT b FROM Book b JOIN b.author a WHERE a.country = : country");
            query6.setParameter("country", "America");
            List<Book> books = query6.getResultList();
            for (Book book : books) {
                System.out.println("book id : "+book.getId()+" book title : "+book.getTitle());
            }

            //Q:7
        /*@JoinColumn(name = "author_id"): This annotation specifies the foreign key column
        that maps the association between the Book and Author entities. The name attribute
        specifies the name of the foreign key column in the Book table.*/

            //Q:10
            String hql = "SELECT a FROM Author a WHERE size(a.books) > (SELECT AVG(size(b.books)) FROM Author b)";
            List<Author> specAuthors = session.createQuery(hql, Author.class).list();
            for (Author author : specAuthors) {
                System.out.println("Author id : " + author.getId()+", Author name : " + author.getName());
            }

            transaction.commit();
            session.close();

        }

        //Q:3
        public static void deleteAuthorAndBooks(int authorId) {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Author author = session.get(Author.class, authorId);
            if (author != null) {
                session.delete(author);
            }
            transaction.commit();
            session.close();
        }
    }