package jp.nobu.util;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import jp.nobu.NobuAppConfigureException;

public class DbUtil {

	private static InitialContext ctx;
	private static DataSource ds;

	private static Logger LOG = Logger.getLogger(DbUtil.class);

	public static Connection getConnection() {
		try {
			if (ctx == null) {
				LOG.info("初回の為、DataSource設定。");
				ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/nobuweb");
			}
			LOG.info("コネクション生成");
			return ds.getConnection();
		} catch (Exception ex) {
			throw NobuAppConfigureException.wrap("db connection error.", ex);
		}
	}

}
