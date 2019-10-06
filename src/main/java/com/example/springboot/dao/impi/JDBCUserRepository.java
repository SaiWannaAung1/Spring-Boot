package com.example.springboot.dao.impi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springboot.dao.UserRepository;
import com.example.springboot.dto.User;
@Repository
public class JDBCUserRepository implements UserRepository{
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<User> findAll() {
		return jdbc.query("SELECT id, name, email FROM User",
				this::mapRowToUser);
	}

	@Override
	public User findOne(Long id) {
		return jdbc.queryForObject(
				"SELECT id, name, email FROM User where id=?",
				this::mapRowToUser, id);
	}

	@Override
	public User save(User user) {
		jdbc.update(
				"SELECT into User ( name, email) VALUES (?, ?)",
				user.getName(),
				user.getEmail());
		return user;
	}
	private User mapRowToUser(ResultSet rs, int rowNum)
			throws SQLException {
		return new User(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("email"));
	}

	@Override
	public User update(User user) {
		jdbc.update(
				"UPDATE User SET name=?, email=? WHERE id=?",
				user.getName(),
				user.getEmail(),
				user.getId());
		return user;
	}

	@Override
	public void delete(Long id) {
		jdbc.update(
				"DELETE FROM User WHERE id=?",
				id);
	}


}
