package jp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class Testt
 */
@WebServlet("/Testt")
public class Testt extends HttpServlet {
	static Logger logger = LogManager.getLogger(Testt.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		 logger.info("Hello!!");
		 logger.error("aaaaa");
	}
}
