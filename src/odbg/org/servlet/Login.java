package odbg.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import odbg.org.libs.OLogs;
import odbg.org.libs.OSession;

/**
 * Servlet implementation class Logs
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PrintWriter out = null;
	private OLogs logs = OLogs.getInstance(100, true);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logs.log("Se realizo una peticion GET a /logs");
		out = response.getWriter();
		
		
		OSession s = new OSession();
		
		
		
		out.append("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logs.log("Se realizo una peticion POST a /logs");
		out = response.getWriter();
		
		OSession s = new OSession();
		
		if( !s.hasSession() ) {
			s.setSession(request.getSession());
		}
		
		
		out.append("");
		
		
	}

}
