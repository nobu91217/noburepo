package jp.nobu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.nobu.NobuSystemException;
import jp.nobu.domain.User;

/**
 * ユーザーに関するサービスを行う。
 * 
 * @author yosuke
 *
 */
public class UserService extends GenericSearvice {

	public static final UserService INSTANCE = new UserService();

	private UserService() {
	}

	/**
	 * ログインする。
	 * 
	 * @param userId
	 *            ユーザID
	 * @param password
	 *            パスワード
	 * @return 成功時trueを返す。
	 */
	public boolean login(String userId, String password) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("SELECT * FROM user_info where user_id = ? and password = ? ");
			ps.setString(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return rs.next();

		} catch (Exception e) {
			throw NobuSystemException.wrap("認証実行エラー", e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}

	/**
	 * パスワードがnotnullなユーザーの一覧を取得する。
	 * 
	 * @return ユーザー一覧
	 */
	public List<User> getUsers() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("SELECT user_id,name FROM user_info where password is not null ORDER BY user_id");

			rs = ps.executeQuery();

			List<User> ret = new ArrayList<User>();

			while (rs.next()) {
				ret.add(new User(rs.getString("user_id"), rs.getString("name")));
			}

			return ret;

		} catch (Exception e) {
			throw NobuSystemException.wrap("ユーザー一覧取得エラー", e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}

	/**
	 * ユーザー名を変更する。
	 * 
	 * @param userId
	 *            変更対象のユーザを特定するユーザID
	 * @param userName
	 *            変更する新しい名前
	 * @return 該当するユーザが存在し、正しく変更できればtrueを返す。存在しない場合falseを返す。
	 */
	public boolean updateUserName(String userId, String userName) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("UPDATE user_info SET user_id=?, name=?");
			ps.setString(1, userId);
			ps.setString(2, userName);

			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw NobuSystemException.wrap("ユーザーネーム更新エラー", e);

		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null)con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}

	/**
	 * ユーザを削除する。
	 * 
	 * @param userId
	 *            削除対象のユーザを特定するユーザID
	 * @return 該当するユーザが存在し、正しく削除できればtrueを返す。存在しない場合falseを返す。
	 */
	public boolean deleteUserByUserId(String userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("DELETE FROM user_info where user_id = ?");
			ps.setString(1, userId);

			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw NobuSystemException.wrap("ユーザー情報削除エラー", e);

		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null)con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}
}
