package odbg.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import odbg.org.libs.OLogs;

/**
 * Servlet implementation class Logs
 */
@WebServlet("/logs")
public class Logs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PrintWriter out = null;
	private OLogs logs = OLogs.getInstance(100, true);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logs.log("Se realizo una peticion GET a /logs");
		
		out = response.getWriter();
		
		out.append(logs.toJson().getPrettyJson());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logs.log("Se realizo una peticion POST a /logs");
		
		doGet(request, response);
	}

}
