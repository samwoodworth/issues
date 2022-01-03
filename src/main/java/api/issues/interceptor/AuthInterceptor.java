package api.issues.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Prehandle method");

        URLConnection con = new URL("http://localhost:8080/getAuth").openConnection();
        InputStream inputStream = con.getInputStream();

        try (Scanner scanner = new Scanner(inputStream)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println("Output is: " + responseBody);
        }

        return true;
    }


}
