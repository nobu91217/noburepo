package jp.nobu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.service.UserService;
import jp.nobu.util.Validation;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8118821188723764546L;

	private boolean putErrorMessage(HttpServletRequest request, String key, String message) {
		request.setAttribute(key + "ErrorMsg", message);
		return true;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// TODO Auto-generated method stub
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			
			
			// 入力チェック
			boolean hasError = false;
			if (Validation.isBlank(id))
				hasError = putErrorMessage(request, "id", "入力してください。");
			else if (Validation.isEmail(id))
				hasError = putErrorMessage(request, "id", "正しくユーザーIDを入力してください");

			if (Validation.isBlank(pass))
				hasError = putErrorMessage(request, "pass", "入力してください。");

			if (hasError) {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}

			if (UserService.INSTANCE.login(request.getParameter("id"), request.getParameter("pass"))) {
				getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
				// 存在する場合の処理 成功画面に遷移
			} else {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				// 存在しない場合の処理 ログイン画面に遷移
			}

	
		}
	}

