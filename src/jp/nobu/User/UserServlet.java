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
		List<User> users = UserService.INSTANCE.getUsers();
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
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

		if (request.getParameter("register") != null) {
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");

			// 入力チェック
			boolean hasError = false;
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "id", "入力してください。");
			if (Validation.isBlank(pass))
				hasError = putErrorMessage(request, "password", "入力してください。");
			if (Validation.isBlank(name))
				hasError = putErrorMessage(request, "name", "入力してください。");

			if (hasError) {
				getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			}
			UserService.INSTANCE.registerUserInfo(id, pass, name);
			forwardUserList(request, response);

		} else if (request.getParameter("delete") != null) {
			String id = request.getParameter("user_id");
			String name = request.getParameter("name");

			// 入力チェック
			boolean hasError = false;
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "id", "ユーザーIDを選択してください。");
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "name", "ユーザーネームを選択してください。");

			if (hasError) {
				request.setAttribute("user_id", id);
				request.setAttribute("name", name);
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
				return;
			}

			UserService.INSTANCE.deleteUserByUserId(request.getParameter("user_id"));
			forwardUserList(request, response);
			// 存在する場合の処理 成功画面に遷移
		} else if (request.getParameter("update") != null) {
			String id = request.getParameter("user_id");
			String name = request.getParameter("name");

			// 入力チェック
			boolean hasError = false;
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "id", "ユーザーIDを選択してください。");
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "name", "ユーザーネームを選択してください。");

			if (hasError) {
				request.setAttribute("user_id", id);
				request.setAttribute("name", name);
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
				return;
			}

			UserService.INSTANCE.updateUserName(request.getParameter("user_id"), name);
			forwardUserList(request, response);
		}
	}
}
