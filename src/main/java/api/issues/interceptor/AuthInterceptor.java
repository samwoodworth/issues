package api.issues.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.InputStream;
import java.net.*;
import java.util.Scanner;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        URLConnection con = new URL("http://localhost:8080/getAuth").openConnection();
        InputStream inputStream = con.getInputStream();

        //Checking for JSESSIONID cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID"))
                return true;
        }
        return false;
    }
}
