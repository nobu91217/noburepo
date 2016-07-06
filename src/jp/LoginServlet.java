package jp;

import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;





/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection db = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		try{
			
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/nobuweb");
			db = (Connection) ds.getConnection();
			ps = db.prepareStatement("SELECT * FROM user_info where id = ? and password = ? ");
			ps.setString(1, id);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				getServletContext().getRequestDispatcher("/loginok.jsp").forward(request, response);
				//‘¶İ‚·‚éê‡‚Ìˆ—@¬Œ÷‰æ–Ê‚É‘JˆÚ
			} else {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				//‘¶İ‚µ‚È‚¢ê‡‚Ìˆ— ƒƒOƒCƒ“‰æ–Ê‚É‘JˆÚ
			}	

		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				rs.close();
				ps.close();
			}catch(SQLException ex) {
				//inogre
			}
		}
	}
}