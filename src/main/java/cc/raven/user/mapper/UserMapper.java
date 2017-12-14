package cc.raven.user.mapper;

import cc.raven.user.model.User;
import cc.raven.user.pojo.UserLogin;
import cc.raven.user.pojo.UserPOJO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by fenghou on 2017/11/28.
 */
public interface UserMapper {

    void insert(UserPOJO userPOJO);

    int login(UserLogin userLogin);

    User selectByUserName(@Param("userName") String userName);

    int checkUserExist(@Param("userName") String userName);

}
