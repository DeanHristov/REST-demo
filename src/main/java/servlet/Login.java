package servlet;


import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.*;

@WebServlet("/register")

public class Login extends HttpServlet{

    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        PrintWriter output = res.getWriter();
        BufferedWriter buff = null;
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        String egn = req.getParameter("egn");

        buff = new BufferedWriter(new FileWriter(System.getProperty("user.dir") +"/users.txt", true));
        buff.write("{ firstName : "+firstName+", lastName: "+lastName+", age: "+age+", egn: "+egn+" }");
        buff.newLine();
        buff.close();

        res.setStatus(res.SC_MOVED_TEMPORARILY);
        res.setHeader("Location", "/home.html");
    }
}
