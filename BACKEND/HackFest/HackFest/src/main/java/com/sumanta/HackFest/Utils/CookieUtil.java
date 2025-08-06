package com.sumanta.HackFest.Utils;

import org.springframework.http.ResponseCookie;

public class CookieUtil {
    public static ResponseCookie generateCookie(String jwtToken) {
        return ResponseCookie.from("jwt", jwtToken)
                                .httpOnly(true)
                                .secure(false)
                                .path("/")
                                .maxAge(60*60*10)
                                .build();
    }
}
