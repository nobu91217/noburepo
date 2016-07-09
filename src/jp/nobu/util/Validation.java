package jp.nobu.util;

/**
 * 入力チェック用ユーティリティ
 * @author yosuke
 *
 */
public class Validation {
	
	/**
	 * 指定文字列が空（null or '' or '    '）であるかを確認する。
	 * @param val 対象文字列
	 * @return 空（null or '' or '    '）であればtrueを返す。
	 */
	public static boolean isBlank(String val) {
		return val == null || val.trim().length() ==0;
	}
	/**
	 * 指定文字列が空（null or '' or '    '）でないことを確認する。
	 * @param val 対象文字列
	 * @return 空（null or '' or '    '）でなければtrueを返す。
	 */
	public static boolean isNotBlank(String val) {
		return isBlank(val) == false;
	}
	
	/**
	 * 指定文字列がEmail形式であるかを確認する。
	 * @param val 対象文字列
	 * @return Emailアドレスとして正しければtrueを返す。
	 */
	public static boolean isEmail(String val) {
		if(isBlank(val))return false;
		
		int atIndex = val.indexOf("@");
		
		//「@」の前後に1文字以上あればOKとする（仮）
		return 1 < atIndex  && atIndex  < val.length() -1 && underMaxLength(val,255);
		
	}
	
	/**
	 * 指定文字列が指定値以下の長さであることを確認する。
	 * @param val 対象文字列
	 * @param max 最大文字列長
	 * @return 指定文字列が指定値以下の長さであればtrueを返す。
	 */
	public static boolean underMaxLength(String val,int max) {
		int length = val == null ? 0 : val.length();
		return  length <=max;
	}
	
}
