package jp.nobu.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.util.DbUtil;

/**
 * Servlet implementation class SendServlet
 */
@WebServlet("/SendServlet")
public class SendInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = DbUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM user_info where user_id=?");
			ps.setString(1, request.getParameter("id"));
			rs = ps.executeQuery();

			if (rs.next()) {
				request.setAttribute("id", rs.getString("user_id"));
				request.setAttribute("name", rs.getString("name"));
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {

			}
		}
	}
}
