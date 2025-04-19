package com.myjdbc.myjdbcapp.dao;

import com.myjdbc.myjdbcapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class UserDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JdbcTemplate template;

    public int addUser(int id, String name, String email){
        User user = new User(id, name, email);
        String stmt = "insert into users (id, name, email) values (?, ?, ?)";
        return template.update(stmt, new Object[]{user.getId(), user.getName(), user.getEmail()});
    }

    public List<User> getAllUsers(){
        String sql = "select * from users";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    public User getUserById(int id) {
//        String sql = "SELECT * FROM users WHERE id = ?";
        String sql = "select * from users where id = ?";
        try{
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        }
        catch (EmptyResultDataAccessException e){
            logger.info("No user found with id - {}", id);
            return null;
        }
    }

    public int updateUserEmailById(int id, String newMail){
        String sql = "update users set email = ? where id = ?";
        try{
            int updated = template.update(sql, newMail, id);
            return updated;
        }
        catch (EmptyResultDataAccessException e)
        {
            logger.info("Something went wrong while updating! - " + e.getMessage());
        }
        return -1;
    }

    public String findUsernameByEmail(String email){
        String sql = "select * from users where email = ?";
        try{
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), email);
            return user.getName();
        }
        catch (EmptyResultDataAccessException e){
            logger.info("No user with email - " + email + " found!");
            return null;
        }
    }

    public int deleteUserWithId(int id){
        String sql = "delete from users where id = ?";
        try{
            int updated = template.update(sql, id);
            return updated;
        }
        catch (EmptyResultDataAccessException e)
        {
            logger.info("Something went wrong while deleting user with id {}", id);

        }
        return -1;
    }

    public int [][] addBulkUsers(List<User> users){
        String sql = "insert into users (id, name, email) values (?, ?, ?)";
        try{
            int[][] ints = template.batchUpdate(sql, users, users.size(), (ps, user) -> {
                ps.setInt(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getEmail());
            });
            return ints;
        }
        catch (Exception e)
        {
            logger.info("Error while doing bulk addition of users - {}", e.getMessage());
        }
        return new int[][]{};
    }

    public List<User> getUsersByEmailDomain(String domain){
        String sql = "select * from users where email like ?";
        domain = "%"+domain;
        try{
            List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), domain);
            return users;
        }
        catch (EmptyResultDataAccessException e){
            logger.info(e.getMessage());
        }
        return null;
    }

    public boolean emailExists(String email){
        String sql = "select count(*) from users where email = ?";
        try{
            Integer i = template.queryForObject(sql, Integer.class, email);
            return i >= 1;
        }
        catch (EmptyResultDataAccessException e){
            logger.info("empty result..");
        }
        return false;
    }

    public int deleteAllUsers(){
        String sql = "delete from users";
        try{
            int updated = template.update(sql);
            return updated;
        }
        catch (EmptyResultDataAccessException e){
            logger.info(e.getMessage());
            return -1;
        }
    }

    public List<User> getUsersPaged(int offset, int limit){
        String sql = "select * from users order by id limit ? offset ?";
        try{
            List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), limit, offset);
            return users;
        }
        catch (EmptyResultDataAccessException e)
        {
            logger.info("Unable to fetch users - {}", e.getMessage());
        }
        return null;
    }

    public User getUserByEmail(String email){
        String sql = "select * from users where email = ?";
        try{
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), email);
            return user;
        }
        catch (Exception e){
            logger.info(e.getMessage());
            return null;
        }
    }

    public boolean addUserIfNotExists(User user){
        String sql = "insert into users (id, name, email) values (?, ?,  ?)";
        if(!this.emailExists(user.getEmail())){
            try{
                int updated = template.update(sql, user.getId(), user.getName(), user.getEmail());
                return true;
            }
            catch (Exception e){
                logger.error("Error while inserting record with email - {}", user.getEmail());
                return false;
            }
        }
        else{
            logger.info("User with email {} already exists!", user.getEmail());
            return false;
        }
    }
}