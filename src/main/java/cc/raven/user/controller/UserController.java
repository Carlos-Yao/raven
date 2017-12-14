package cc.raven.user.controller;

import cc.raven.user.mapper.UserMapper;
import cc.raven.user.model.User;
import cc.raven.user.pojo.UserLogin;
import cc.raven.user.pojo.UserPOJO;
import cc.raven.user.service.UserService;
import cc.raven.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fenghou on 2017/11/28.
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody UserLogin userLogin, HttpServletResponse response) {
        try {
            int count = userService.login(userLogin);
            if (count == 0) {
                return new Result(200, "用户名或密码错误！", false);
            }
            Cookie cookie = new Cookie("userName", userLogin.getUserName());
            cookie.setMaxAge(60 * 15);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new Result(200, "登录成功！", true);
        } catch (Throwable e) {
            throw new RuntimeException("check user error", e);
        }
    }

    @RequestMapping(value = "/getInfo.lg", method = RequestMethod.GET)
    @ResponseBody
    public Result getInfo(HttpServletRequest request) {
        try {
            if (request.getCookies() == null) {
                return new Result(200, "获取用户信息失败", "");
            }
            Cookie[] cookies = request.getCookies();
            String userName = "";
            for (Cookie cookie : cookies) {
                if ("userName".equals(cookie.getName())) {
                    userName = cookie.getValue();
                    continue;
                }
            }
            if (!"".equals(userName)) {
                User user = userService.selectByUserName(userName);
                return new Result(200, "获取用户信息成功", user);
            }
            return new Result(200, "获取用户信息失败", "");
        } catch (Throwable e) {
            throw new RuntimeException("select userInfo error", e);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(@RequestBody UserPOJO userPOJO) {
        try {
            userService.insert(userPOJO);
            return new Result(200, "注册成功", "");
        } catch (Throwable e) {
            throw new RuntimeException("add user error", e);
        }
    }

    @RequestMapping(value = "/checkUser/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public Result checkUser(@PathVariable("userName") String userName) {
        try {
            int count = userService.checkUserExist(userName);
            if (count == 0) {
                return new Result(200, "用户名可用", true);
            }
            return new Result(200, "用户名已存在", false);
        } catch (Throwable e) {
            throw new RuntimeException("check user error", e);
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "success";
    }

}
