package com.study.tinyspringmvc;

import com.study.tinyspringmvc.annotation.Reposity;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
@Reposity("userDaoImpl")
public class UserDaoImpl implements UserDao {
    public void insert() {
        System.out.println("userDaoImpl insert start...");
        System.out.println("userDaoImpl insert end...");
    }
}
