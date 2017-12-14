package cc.raven.util;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fenghou on 2017/12/1.
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        String userName = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userName".equals(cookie.getName())) {
                    userName = cookie.getValue();
                    StaticInfo.userName = userName;
                    continue;
                }
            }
        }
        HttpServletResponse resp = (HttpServletResponse) response;
        if (!"".equals(userName)) {
            Cookie cookie = new Cookie("userName", userName);
            cookie.setMaxAge(60 * 15);
            cookie.setPath("/");
            resp.addCookie(cookie);
            chain.doFilter(request, response);
            return;
        }
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        resp.getWriter().write("<h3>401:检测到您未登录，请登录后进行操作！</h3>");
    }

    public void destroy() {

    }
}
