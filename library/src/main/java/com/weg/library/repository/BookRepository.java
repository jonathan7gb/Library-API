package com.weg.library.repository;

import com.weg.library.config.ConectDatabase;
import com.weg.library.model.Book;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    public Book save(Book book) throws SQLException {
        String sql = """
                INSERT INTO book
                (title, author, publication_year)
                VALUES
                (?,?, ?)
                """;
        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1,book.getTitle());
            stmt.setString(2,book.getAuthor());
            stmt.setInt(3, book.getPublication_year());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                book.setId(rs.getLong(1));
                return book;
            }
        }
        return null;
    }

    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();

        String sql = """
                SELECT id
                       ,title
                       ,author,
                        publication_year
                FROM book
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                books.add(new Book(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
        }
        return books;
    }

    public boolean update(Book book, Long id) throws SQLException {
        String sql = """
                UPDATE book
                set title = ?,
                author = ?,
                publication_year = ?
                WHERE
                id  = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getPublication_year());
            stmt.setLong(4,id);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows > 0){
                return true;
            }
        }
        return false;
    }

    public Book findById(Long id) throws SQLException {
        String sql = """
                 SELECT id
                       ,title
                       ,author
                       ,publication_year
                FROM book
                WHERE id = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Book(
                        id,
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
            }
        }
        return null;
    }

    public void deleteById(Long id) throws SQLException{
        String sql = """
                DELETE 
                FROM book
                WHERE id = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
        }
    }

}
