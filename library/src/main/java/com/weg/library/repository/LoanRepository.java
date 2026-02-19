package com.weg.library.repository;

import com.weg.library.config.ConectDatabase;
import com.weg.library.model.Loan;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LoanRepository {

    public Loan save(Loan loan) throws SQLException {
        String sql = """
                INSERT INTO loan
                (user_id, book_id, loan_date)
                VALUES
                (?,?, ?)
                """;
        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1,loan.getUser_id());
            stmt.setLong(2,loan.getBook_id());
            stmt.setDate(3, Date.valueOf(loan.getLoan_date()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                loan.setId(rs.getLong(1));
                return loan;
            }
        }
        return null;
    }

    public List<Loan> findAll() throws SQLException {
        List<Loan> loans = new ArrayList<>();

        String sql = """
                SELECT id
                       ,user_id
                       ,book_id
                       ,loan_date
                       ,return_date
                FROM loan
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                loans.add(new Loan(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate()
                ));
            }
        }
        return loans;
    }

    public boolean update(Loan loan, Long id) throws SQLException {
        String sql = """
                UPDATE loan
                set user_id = ?,
                book_id = ?,
                loan_date = ?
                WHERE
                id  = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, loan.getUser_id());
            stmt.setLong(2, loan.getBook_id());
            stmt.setDate(3, Date.valueOf(loan.getLoan_date()));
            stmt.setLong(4, id);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows > 0){
                return true;
            }
        }
        return false;
    }

    public Loan findById(Long id) throws SQLException {
        String sql = """
                 SELECT id
                       ,user_id
                       ,book_id
                       ,loan_date
                       ,return_date
                FROM loan
                WHERE id = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Loan(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate()
                );
            }
        }
        return null;
    }

    public void deleteById(Long id) throws SQLException{
        String sql = """
                DELETE 
                FROM loan
                WHERE id = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Loan> findAllByUserId(Long user_id) throws SQLException {
        List<Loan> loans = new ArrayList<>();

        String sql = """
                SELECT id
                       ,user_id
                       ,book_id
                       ,loan_date
                       ,return_date
                FROM loan
                WHERE user_id = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, user_id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                loans.add(new Loan(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate()
                ));
            }
        }
        return loans;
    }

    public boolean registerReturnDate(Long id, LocalDate return_date) throws SQLException {
        String sql = """
                UPDATE loan
                set return_date = ?,
                WHERE
                id  = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDate(1, Date.valueOf(return_date));
            stmt.setLong(2, id);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows > 0){
                return true;
            }
        }
        return false;
    }
}
