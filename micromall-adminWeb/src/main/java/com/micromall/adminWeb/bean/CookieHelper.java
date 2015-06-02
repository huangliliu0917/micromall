package com.micromall.adminWeb.bean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����Cookie
 * Created by Administrator on 2015/5/21.
 */
public class CookieHelper {
    /**
     * �õ�cookie��ֵ������String
     *
     * @param request
     * @param key
     * @return
     */
    public static String getCookieVal(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * �õ�cookie��ֵ������int
     *
     * @param request
     * @param key
     * @return
     */
    public static int getCookieValInteger(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return Integer.parseInt(cookie.getValue());
                }
            }
        }
        return 0;
    }

    /**
     * ����cookie
     *
     * @param response
     * @param key
     * @param value
     */
    public static void setCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(1209600);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * ɾ��cookie
     *
     * @param response
     * @param key
     */
    public static void removeCookie(HttpServletResponse response, String key) {
        Cookie cookie = new Cookie(key, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
