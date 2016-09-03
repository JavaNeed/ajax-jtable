package com.programmingfree.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.programmingfree.model.User;

public class UserDAOImpl implements UserDAO{
	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] args = new Object[] {user.getUserid(), user.getFirstName(), user.getLastName(), user.getEmail()};
		
		String INSERT_QUERY = "insert into tbluser (userid, firstname, lastname, email) values (?,?,?,?)";
		
		int out = jdbcTemplate.update(INSERT_QUERY, args);

		if(out != 0)
			LOGGER.debug("User saved with id="+user.getUserid());
		else 
			LOGGER.debug("User save failed with id="+user.getUserid());
	}

	@Override
	public User getById(int id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String GET_BY_ID_QUERY = "select userid, firstname, lastname, email from tblUser where userid = ?";
		
		User user = jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{id}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setUserid(rs.getInt("userid"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setEmail(rs.getString("email"));
				return u;
			}});

		return user;
	}

	@Override
	public void update(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String UPDATE_QUERY = "update tblUser set firstname=?, lastname=?, email=? where userid=?";
		
		Object[] args = new Object[] {user.getFirstName(), user.getLastName(), user.getEmail()};

		int out = jdbcTemplate.update(UPDATE_QUERY, args);
		if(out != 0)
			LOGGER.debug("Employee updated with id="+user.getUserid());
		else 
			LOGGER.debug("No Employee found with id="+user.getUserid());
	}

	@Override
	public void deleteById(int id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String DELETE_BY_ID = "delete from tblUser where userid=?";
		
		int out = jdbcTemplate.update(DELETE_BY_ID, id);
		if(out != 0)
			LOGGER.debug("Employee deleted with id="+id);
		else 
			LOGGER.debug("No Employee found with id="+id);
	}

	@Override
	public List<User> getAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<User> userList = new ArrayList<User>();

		String GET_ALL = "select * from tblUser";
		
		List<Map<String,Object>> userRows = jdbcTemplate.queryForList(GET_ALL);

		for(Map<String, Object> userRow : userRows){
			User u = new User();
			u.setUserid(Integer.parseInt(String.valueOf(userRow.get("userid"))));
			u.setFirstName(String.valueOf(userRow.get("firstname")));
			u.setLastName(String.valueOf(userRow.get("lastname")));
			u.setEmail(String.valueOf(userRow.get("email")));
			userList.add(u);
		}
		return userList;
	}

	@Override
	public int getUserCount() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.getMaxRows();
		return count;
	}
}
