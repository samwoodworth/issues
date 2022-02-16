package api.issues.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String responseBody;
        String username = request.getParameter("user");

        if (username != null){
            //Look at HttpServletRequest documentation, specifically getUserPrincipal
            URLConnection con = new URL("http://localhost:8080/getAuth?user=" + username).openConnection();
            InputStream inputStream = con.getInputStream();

            try (Scanner scanner = new Scanner(inputStream)) {
                responseBody = scanner.useDelimiter("\\A").next();
            }

            if (responseBody.equals("true")) {
                return true;
            } else {
                System.out.println("Not signed in.\n");
                response.setStatus(401);

                //response.sendRedirect("http://localhost:8080/login");  //Doesn't give 401 status
                return true;  //ONLY FOR TESTING. CHANGE TO FALSE WHEN DONE
            }
        } else {
            System.out.println("No parameter passed.\n");
            return true; //ONLY FOR TESTING. CHANGE TO FALSE WHEN DONE
        }

    }
}
