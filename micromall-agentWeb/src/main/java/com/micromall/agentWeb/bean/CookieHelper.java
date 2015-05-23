package com.micromall.agentWeb.bean;

import com.micromall.agentWeb.controller.BaseController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/5/21.
 */
public class CookieHelper {
    public static String getCookieVal(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static int getCookieValInteger(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                return Integer.parseInt(cookie.getValue());
            }
        }
        return 0;
    }

    public static void setCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(1209600);
        response.addCookie(cookie);
    }
}
