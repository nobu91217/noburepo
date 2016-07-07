package jp.nobu.service;

import java.sql.Connection;

import jp.nobu.util.DbUtil;

public abstract class GenericSearvice {

	protected Connection getConnection() {
		return DbUtil.getConnection();
	}
}
