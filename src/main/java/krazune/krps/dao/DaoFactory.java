package krazune.krps.dao;

import krazune.krps.game.dao.GameDao;
import krazune.krps.user.dao.UserDao;

public interface DaoFactory
{
	UserDao createUserDao();
	GameDao createGameDao();
}
