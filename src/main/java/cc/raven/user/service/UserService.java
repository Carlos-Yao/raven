package cc.raven.user.service;

import cc.raven.user.model.User;
import cc.raven.user.pojo.UserLogin;
import cc.raven.user.pojo.UserPOJO;

/**
 * Created by fenghou on 2017/11/29.
 */
public interface UserService {

    void insert (UserPOJO userPOJO);

    User selectByUserName(String userName);

    int login(UserLogin userLogin);

    int checkUserExist (String userName);

}
