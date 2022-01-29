package krazune.krps.user.dao;

import java.sql.SQLException;
import java.util.List;
import krazune.krps.user.User;

public interface UserDao
{
	boolean insert(User user) throws SQLException;

	User get(int id) throws SQLException;
	User get(String username) throws SQLException;
	List<User> getUsers() throws SQLException;
	List<User> getUsers(int start, int size) throws SQLException;

	boolean update(User user) throws SQLException;

	boolean delete(int id) throws SQLException;
	boolean delete(User user) throws SQLException;

	int getUserCount() throws SQLException;
}
