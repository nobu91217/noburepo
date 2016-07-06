package jp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection db = null;
		PreparedStatement ps = null;

		try {
				Context context = new InitialContext();
				DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/nobuweb");
				db = (Connection) ds.getConnection();
				ps = db.prepareStatement("DELETE FROM user_info where user_id = ? and password = ?");
				ps.setString(1, request.getParameter("id"));
				ps.setString(2, request.getParameter("password"));

				ps.executeUpdate();

				getServletContext().getRequestDispatcher("/View.jsp").forward(request, response);
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				db.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}
}
