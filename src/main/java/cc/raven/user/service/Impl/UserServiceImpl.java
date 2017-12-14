package cc.raven.user.service.Impl;

import cc.raven.user.mapper.UserMapper;
import cc.raven.user.model.User;
import cc.raven.user.pojo.UserLogin;
import cc.raven.user.pojo.UserPOJO;
import cc.raven.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenghou on 2017/11/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public void insert(UserPOJO userPOJO) {
        userMapper.insert(userPOJO);
    }

    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    public int login(UserLogin userLogin) {
        return userMapper.login(userLogin);
    }

    public int checkUserExist(String userName) {
        return userMapper.checkUserExist(userName);
    }
}
