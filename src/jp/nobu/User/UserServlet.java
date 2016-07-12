package jp.nobu.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.domain.User;
import jp.nobu.service.UserService;
import jp.nobu.util.Validation;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("id") != null) {
			User user = UserService.INSTANCE.getUser(request.getParameter("id"));
			String id = user.getUserId();
			String name = user.getUserName();

			request.setAttribute("id", id);
			request.setAttribute("name", name);
			getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
		} else {
			List<User> users = UserService.INSTANCE.getUsers();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);

		}

	}

	private boolean putErrorMessage(HttpServletRequest request, String key, String message) {
		request.setAttribute(key + "ErrorMsg", message);
		return true;
	}

	private void forwardUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = UserService.INSTANCE.getUsers();
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("register") != null) {
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");

			boolean hasError = false;
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "id", "入力してください。");
			else if (Validation.isEmail(id))
				hasError = putErrorMessage(request, "id", "正しいメールアドレスを入力してください");

			if (Validation.isBlank(pass))
				hasError = putErrorMessage(request, "password", "入力してください。");
			if (Validation.isBlank(name))
				hasError = putErrorMessage(request, "name", "入力してください。");

			if (hasError) {
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("name", request.getParameter("name"));
				getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			} 
				if(UserService.INSTANCE.authUser(id)) {
					putErrorMessage(request,"auth","既に登録されているユーザーです。");
					getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
				} else {
					UserService.INSTANCE.registerUserInfo(id, pass, name);
					response.sendRedirect("UserServlet");			
			}

				
		} else if (request.getParameter("delete") != null) {
			String id = request.getParameter("user_id");

			boolean hasError = false;
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "delete", "削除するユーザを指定してください。");
			else if (Validation.isEmail(id))
				hasError = putErrorMessage(request, "delete", "指定されたユーザIDが正しくありません。");

			if (hasError) {
				forwardUserList(request, response);
				return;
			}
			UserService.INSTANCE.deleteUserByUserId(request.getParameter("user_id"));
			response.sendRedirect("UserServlet");
		} else if (request.getParameter("update") != null) {
			String id = request.getParameter("user_id");
			String name = request.getParameter("name");
			boolean hasError = false;
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "id", "対象のユーザーIDを入力してください。");
			else if (Validation.isEmail(id))
				hasError = putErrorMessage(request, "id", "指定されたユーザIDが正しくありません。");

			if (Validation.isBlank(name))
				hasError = putErrorMessage(request, "name", "対象のユーザーネームを入力してください。");
			if (hasError) {
				request.setAttribute("id", id);
				request.setAttribute("name", name);
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
				return;
			}
			UserService.INSTANCE.updateUserName(request.getParameter("user_id"), name);
			response.sendRedirect("UserServlet");
		}

	}

}
