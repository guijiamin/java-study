package com.study.tinyspringmvc;

import com.study.tinyspringmvc.annotation.Qualifier;
import com.study.tinyspringmvc.annotation.Service;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public void insert() {
        System.out.println("userServiceImpl insert start...");
        userDao.insert();
        System.out.println("userServiceImpl insert end...");
    }
}
