package com.api.mygym.infra.security;

import jakarta.servlet.http.Cookie;
import java.time.Duration;

public class CookieUtils {

    public static Cookie createRefreshToken(String refreshtoken){
        Cookie cookie = new Cookie("refresh_token", refreshtoken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setAttribute("SameSite", "None");
        cookie.setPath("/");
        cookie.setMaxAge((int) Duration.ofDays(30).getSeconds());
        return cookie;
    }

    public static Cookie deleteRefreshToken() {
        Cookie cookie = new Cookie("refresh_token", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setAttribute("SameSite", "None");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        return cookie;
    }
}
