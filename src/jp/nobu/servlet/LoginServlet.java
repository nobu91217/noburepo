package jp.nobu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8118821188723764546L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

			if(UserService.INSTANCE.login(request.getParameter("id"),request.getParameter("pass"))) {
				getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
				//存在する場合の処理　成功画面に遷移
			} else {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				//存在しない場合の処理 ログイン画面に遷移
			}	

		
		
				//inogre
			}
	
}
