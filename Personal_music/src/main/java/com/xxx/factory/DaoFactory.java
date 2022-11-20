package com.xxx.factory;

import com.xxx.dao.IMusicDao;
import com.xxx.dao.IUserDao;
import com.xxx.dao.impl.DbUtilsMusicDaoImpl;
import com.xxx.dao.impl.DbUtilsUserDaoImpl;

public class DaoFactory {
    public static IUserDao getUserDaoInstance() {
        return new DbUtilsUserDaoImpl();
    }

    // Music
    public static IMusicDao getMusicDaoInstance() {
        return new DbUtilsMusicDaoImpl();
    }
}
