package krazune.krps.user.dao;

import java.util.List;
import krazune.krps.dao.DaoException;
import krazune.krps.user.User;

public interface UserDao
{
	boolean insert(User user) throws DaoException;

	User get(int id) throws DaoException;
	User get(String username) throws DaoException;
	List<User> getUsers() throws DaoException;
	List<User> getUsers(int start, int size) throws DaoException;

	boolean update(User user) throws DaoException;

	boolean delete(int id) throws DaoException;
	boolean delete(User user) throws DaoException;

	int getUserCount() throws DaoException;
}
