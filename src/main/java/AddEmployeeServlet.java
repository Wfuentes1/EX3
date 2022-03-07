import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

public class AddEmployeeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("navbar.html").include(request,response);

        Employee employee = new Employee();
        employee.setName(request.getParameter("name"));
        employee.setEmail(request.getParameter("email"));
        employee.setGender(request.getParameter("gender"));
        employee.setCountry(request.getParameter("country"));

        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");

        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;

       session.persist(employee);
       t.commit();
       session.close();
       request.getRequestDispatcher("employee.html").include(request, response);
       out.println("<h2> "+ employee.getName()+ " "+ employee.getEmail()+" added</h2>");





    }
}
