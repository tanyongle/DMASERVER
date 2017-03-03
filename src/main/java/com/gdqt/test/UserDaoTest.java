package com.gdqt.test;

import com.gdqt.dao.UserDao;
import com.gdqt.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findAllUserByEmail() throws Exception {
//        User user = userDao.findUserByEmail("andy.lu@vgcs.me");
//        System.out.println(user.getUsername() + "," + user.getUsername());
    }

    @Test
    public void testGetUser() throws Exception {
        System.out.println(userDao.getUser().get(0));
    }
}
