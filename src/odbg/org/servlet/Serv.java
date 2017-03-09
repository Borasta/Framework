package odbg.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import odbg.org.libs.JFlex;
import odbg.org.libs.JSonG;
import odbg.org.libs.OLogs;

/**
 * Servlet implementation class JFlex
 */
@WebServlet("/serv")
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PrintWriter out = null;
	private OLogs logs = OLogs.getInstance(100, true);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serv() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logs.log("Se realizo una peticion GET a /serv");
		
		out = response.getWriter();
		response.setHeader("Content-Type", "application/x-www-form-urlencoded");
		JFlex jflex = new JFlex();
		
		String[] arr = request.getParameterValues("array");
		
		System.out.println(arr[0]);

		
		String className = request.getParameter("class");
		String methodName = request.getParameter("method");
		
		String arguments = request.getParameter("arguments");
		
		JSonG json = new JSonG();
		
		if( arguments != null)
			jflex.useMethod(className, methodName, arguments);
//			json.add("resp", jflex.useMethod(className, methodName, arguments));
		else
			json.add("resp", jflex.useMethod(className, methodName));
		
		out.print(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logs.log("Se realizo una peticion POST a /serv");
		
		out = response.getWriter();
		response.setHeader("Content-Type", "application/x-www-form-urlencoded");
	}

}
