package jp.nobu.service;

import java.sql.Connection;

import org.apache.log4j.Logger;

import jp.nobu.util.DbUtil;

public abstract class GenericSearvice {
	
	
	protected final Logger LOG = Logger.getLogger(this.getClass());

	protected Connection getConnection() {
		return DbUtil.getConnection();
	}
}
