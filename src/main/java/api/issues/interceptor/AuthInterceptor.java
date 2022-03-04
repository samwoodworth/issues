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

//        System.out.println("Interceptor");
        String responseBody;
        String username = request.getParameter("user");

        URLConnection con = new URL("http://localhost:8080/getAuth").openConnection();
        InputStream inputStream = con.getInputStream();

        try (Scanner scanner = new Scanner(inputStream)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }

        //Checking for JSESSIONID cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Cookies are: " + cookie.getName());
        }

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);


        //If JESSIONID exists then true, if not then false
        //Check if logout works with it
        String headerName;
        for (int i = 1; (headerName = con.getHeaderFieldKey(i)) != null; i++) {
            System.out.println("Header name is: " + headerName);
            if (headerName.equals("Set-Cookie")) {
                String cookie = con.getHeaderField(i);
//                System.out.println("Cookie is: " + cookie);
                String cookieName = cookie.substring(0, cookie.indexOf("="));
                String cookieValue = cookie.substring(cookie.indexOf("=") + 1);
//                System.out.println("Name is: " + cookieName + " and value is: " + cookieValue);

                if (cookieName.equals("username") && !cookieValue.equals(null)) {
                    return true;
                }
            }
        }

        return false;
    }
}
