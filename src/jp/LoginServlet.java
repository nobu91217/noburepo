package jp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.util.DbUtil;





/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8118821188723764546L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection db = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		try{
			
		
			db = DbUtil.getConnection();
			ps = db.prepareStatement("SELECT * FROM user_info where id = ? and password = ? ");
			ps.setString(1, id);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				getServletContext().getRequestDispatcher("/loginok.jsp").forward(request, response);
				//���݂���ꍇ�̏����@������ʂɑJ��
			} else {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				//���݂��Ȃ��ꍇ�̏��� ���O�C����ʂɑJ��
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