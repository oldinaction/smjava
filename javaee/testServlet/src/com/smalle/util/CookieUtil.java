package com.smalle.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class CookieUtil {
 
    public static String getCookieValue(Cookie[] cookies, String cookieName,
    		String defaultValue) {
        if (null == cookies)
            return "";
        for (int i = 0, size = cookies.length; i < size; i++) {
            Cookie cookie = cookies[i];
            if (cookieName.equals(cookie.getName()))
                return cookie.getValue();
        }
        return defaultValue;
    }
 
    public static void setCookie(HttpServletResponse response,
    		String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
 
    public static void setCookie(HttpServletResponse response,
    		String cookieName, String cookieValue, int cookieMaxage) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(cookieMaxage);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
 
    public static String getCookieValue(HttpServletRequest request,
    		String cookieName, String defaultValue) {
        return getCookieValue(request.getCookies(), cookieName, defaultValue);
    }
 
    public static void delCookie(HttpServletResponse response,
    		String cookieName) {
        Cookie delCookie = new Cookie(cookieName, null);
        delCookie.setMaxAge(0);
        delCookie.setPath("/");
        response.addCookie(delCookie);
    }
}
