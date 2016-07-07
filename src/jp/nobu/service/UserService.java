package jp.nobu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.nobu.NobuSystemException;
import jp.nobu.domain.User;
import jp.nobu.util.DbUtil;

/**
 * ユーザーに関するサービスを行う。
 * 
 * @author yosuke
 *
 */
public class UserService extends GenericSearvice {

	public static final UserService INSTANCE = new UserService();

	private UserService() {}

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
				if (rs != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}
	
	public List<User> getUsers() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("SELECT user_id,name FROM user_info ORDER BY user_id");

			rs = ps.executeQuery();
			
			List<User> ret = new ArrayList<User>();
			
			while(rs.next()) {
				ret.add(new User(rs.getString("user_id"),rs.getString("name")));
			}
			
			return ret;

		} catch (Exception e) {
			throw NobuSystemException.wrap("認証実行エラー", e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (rs != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException ex) {
				// inogre
			}
		}
	}

}
