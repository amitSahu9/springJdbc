package com.myjdbc.myjdbcapp.dao;

import com.myjdbc.myjdbcapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class UserDao {

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

    public User getUserById(int id){
        String sql = "select * from user where id = ?";
        List<User> resUsers = template.query(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
        return resUsers.get(0);
    }
}
