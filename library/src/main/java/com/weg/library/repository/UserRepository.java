package com.weg.library.repository;

import com.weg.library.config.ConectDatabase;
import com.weg.library.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public User save(User user) throws SQLException {
        String sql = """
                INSERT INTO user
                (name, email)
                VALUES
                (?,?, ?)
                """;
        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                user.setId(rs.getLong(1));
                return user;
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();

        String sql = """
                SELECT id
                       ,name
                       ,email
                FROM user
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                users.add(new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }
        }
        return users;
    }

    public boolean update(User user, Long id) throws SQLException {
        String sql = """
                UPDATE user
                set name = ?,
                email = ?
                WHERE
                id  = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setLong(3,id);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows > 0){
                return true;
            }
        }
        return false;
    }

    public User findById(Long id) throws SQLException {
        String sql = """
                 SELECT id
                       ,name
                       ,email
                FROM user
                WHERE id = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new User(
                        id,
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        }
        return null;
    }

    public void deleteById(Long id) throws SQLException{
        String sql = """
                DELETE 
                FROM user
                WHERE id = ?
                """;

        try(Connection conn = ConectDatabase.conect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
        }
    }
}
