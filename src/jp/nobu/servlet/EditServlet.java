package jp.nobu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.domain.User;
import jp.nobu.service.UserService;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("delete") !=null) {
			UserService.INSTANCE.deleteUserByUserId(request.getParameter("user_id"));
			List<User> users = UserService.INSTANCE.getUsers();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
			// 存在する場合の処理 成功画面に遷移
		} else {
			UserService.INSTANCE.updateUserName(request.getParameter("user_id"),request.getParameter("name") );
			List<User> users = UserService.INSTANCE.getUsers();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
		}
		

		// inogre
	}
}
