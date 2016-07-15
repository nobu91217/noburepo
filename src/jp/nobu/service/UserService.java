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
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
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
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
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

		try {

			con = getConnection();
			ps = con.prepareStatement("UPDATE user_info SET name=? where user_id=?");
			ps.setString(1, userName);
			ps.setString(2, userId);

			int count = ps.executeUpdate();
			return count == 1;
		} catch (Exception e) {
			throw NobuSystemException.wrap("ユーザーネーム更新エラー", e);

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
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

		try {

			con = getConnection();
			ps = con.prepareStatement("DELETE FROM user_info where user_id = ?");
			ps.setString(1, userId);

			int count = ps.executeUpdate();
			return count == 1;

		} catch (Exception e) {
			throw NobuSystemException.wrap("ユーザー情報削除エラー", e);

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				// inogre
			}

		}

	}

	/**
	 * ユーザ情報を登録する。
	 * 
	 * @param userId
	 *            データベースのuser_idカラムへ登録するID
	 * @param userPass
	 *            データベースのpasswordカラムへ登録するパスワード
	 * @param userName
	 *            データベースのnameカラムへ登録する名前
	 * @return データベースへの登録に成功すればtrueを返す。失敗した場合はfalseを返す。
	 * @throws SQLException
	 * 
	 */
	public boolean registerUserAndLog(String userId, String userPass, String userName, String dateTime,
			String process) {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		
		LOG.debug("#registerUserAndLog登録開始*************************");

		try {
			
			LOG.debug("ユーザ登録開始");

			con = getConnection();
			con.setAutoCommit(false);
			
			ps1 = con.prepareStatement("INSERT INTO user_info(user_id,password,name) VALUES(?,?,?)");
			ps1.setString(1, userName);
			ps1.setString(2, userPass);
			ps1.setString(3, userName);
			
			if(ps1.executeUpdate() !=1) {
				LOG.debug("ユーザ登録失敗");
				return false;
			}
			
			LOG.debug("ユーザ登録成功");
			
			
			if(true) throw new SQLException();

			LOG.debug("ログ登録開始");
			ps2 = con.prepareStatement("INSERT INTO log(date,process) VALUES(?,?)");
			ps2.setString(1, dateTime);
			ps2.setString(2, process);

			if(ps2.executeUpdate() !=1) {
				LOG.debug("ログ登録失敗");
				LOG.debug("○ロールバック");
				con.rollback();
				return false;
			}
			
			LOG.debug("ログ登録成功");
			
			con.commit();
			
			LOG.debug("○コミット");
			
			return true;

		} catch (SQLException e) {
			try {
				LOG.debug("○エラー発生の為ロールバック");
				if(con!=null)con.rollback();
			} catch (SQLException e1) {

			}
			throw NobuSystemException.wrap("ユーザ情報登録エラー", e);

		} finally {
			LOG.debug("#registerUserAndLog登録終了*************************");
			try {
				if (ps1 != null)
					ps1.close();
				if (ps2 != null)
					ps2.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}

	/**
	 * 指定のユーザーを取得する。
	 * 
	 * @param userId
	 *            取得対象となるユーザのID
	 * @return 該当ユーザが存在すればユーザを返す。存在しなければnullを返す。
	 */
	public User getUser(String userId) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("SELECT * FROM user_info where user_id=?");
			ps.setString(1, userId);
			rs = ps.executeQuery();

			if (!rs.next())
				return null;

			return new User(rs.getString("user_id"), rs.getString("name"));

			// return rs.next();

		} catch (Exception e) {
			throw NobuSystemException.wrap("ユーザー情報取得エラー", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}

	/**
	 * 処理のログを登録する。
	 * 
	 * @param dateTime
	 *            データベースのdateカラムへ登録する日時
	 * @param process
	 *            データベースのprocessカラムへ登録する処理内容
	 * @return データベースへの登録に成功すればtrueを返す。失敗した場合はfalseを返す。
	 */
	public boolean registerLog(String dateTime, String process) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("INSERT INTO log(date,process) VALUES(?,?)");
			ps.setString(1, dateTime);
			ps.setString(2, process);

			int count = ps.executeUpdate();
			return count == 1;

		} catch (Exception e) {
			throw NobuSystemException.wrap("ログ登録エラー", e);

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}

}
