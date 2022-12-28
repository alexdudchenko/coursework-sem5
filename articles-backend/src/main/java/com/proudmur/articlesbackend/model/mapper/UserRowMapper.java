package com.proudmur.articlesbackend.model.mapper;

import com.proudmur.articlesbackend.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setHashedPassword(rs.getString("hashpassword"));
        user.setRole(rs.getString("role"));

        return user;
    }
}
