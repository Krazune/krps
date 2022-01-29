package krazune.krps.dao;

import javax.sql.DataSource;
import krazune.krps.game.dao.GameDao;
import krazune.krps.game.dao.PostgreSqlGameDao;
import krazune.krps.user.dao.PostgreSqlUserDao;
import krazune.krps.user.dao.UserDao;

public class PostgreSqlDaoFactory implements DaoFactory
{
	private DataSource dataSource;

	public PostgreSqlDaoFactory(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public UserDao createUserDao()
	{
		return new PostgreSqlUserDao(dataSource);
	}

	@Override
	public GameDao createGameDao()
	{
		return new PostgreSqlGameDao(dataSource);
	}
}
